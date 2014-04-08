package com.example.ToneMatrix;

import android.content.Context;
import android.graphics.*;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dan Voss on 2/17/14.
 * This code is taken and adapted from the following tutorial:
 * http://www.barebonescoder.com/2010/06/android-development-simple-2d-graphics-part-1/
 *
 * Additional code has been adapted from the android API fingerpaint tutorial:
 * https://gitorious.org/freebroid/development/source/62e92d7a2a3fd2798901ec2e7c452ff0e4067163:samples/ApiDemos/src/com/example/android/apis/graphics/FingerPaint.java#L77
 *
 * More code (for threading) was inspired by code from here:
 * http://www.martinhoeller.net/2012/01/13/developing-a-musical-instrument-app-for-android/
 */
public class Grid extends View {

    int _height;
    int _width;

    private static final float TOUCH_TOLERANCE = 4;

    Bitmap _bitmap;
    Canvas _canvas;
    Paint _paint;

    Paint _line;
    Path _path;
    MaskFilter _emboss;
    MaskFilter _blur;
    float _x;
    float _y;

    Point[][] _horizontalLines;
    Point[][] _verticalLines;

    private LogicalGrid _logicalGrid;

    static String[] topRows;
    static Notes notes;
    //TrackOrgTask task;

    PlayThread upperThread;
    PlayThread lowerThread;

    private boolean drawMode = true;
    //private boolean isCreated = false;

    public Grid(Context context) {
        super(context);
        this.setKeepScreenOn(true);

        _paint = new Paint();
        _paint.setColor(Color.BLACK);
        _paint.setStyle(Paint.Style.STROKE);

        _line = new Paint();
        _line.setColor(Color.BLUE);
        _line.setAntiAlias(true);
        _line.setStyle(Paint.Style.STROKE);
        _line.setStrokeJoin(Paint.Join.ROUND);
        _line.setStrokeCap(Paint.Cap.ROUND);
        _line.setStrokeWidth(12);

        _path = new Path();
        _emboss = new EmbossMaskFilter(new float[] {1, 1, 1 },
                                        0.4f, 6, 3.5f);
        _blur = new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL);
    }

    public void stopGrid() {
//        if (task != null) {
//            task.stop();
//        }
//        task = new TrackOrgTask();
        //drawMode = true;
        if (upperThread != null)
            upperThread.requestStop();
        if (lowerThread != null)
            lowerThread.requestStop();
    }

    private void playGrid() {
//        task = new TrackOrgTask();
//        task.execute();
        //drawMode = false;

        upperThread = new PlayThread(parseTopRows());
        lowerThread = new PlayThread(parseBottomRows());
        upperThread.start();
        lowerThread.start();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        _height = View.MeasureSpec.getSize(heightMeasureSpec);
        _width = View.MeasureSpec.getSize(widthMeasureSpec);

        setMeasuredDimension(_width, _height);

        /*if (isCreated)
            return;*/

        _bitmap = Bitmap.createBitmap(_width, _height, Bitmap.Config.ARGB_8888);
        _canvas = new Canvas(_bitmap);
        setBackgroundColor(0xffffffff);

        calculateLinePlacements();
        drawGrid();

        //isCreated = true;
//        task = new TrackOrgTask();
//        task.execute();
        upperThread = new PlayThread(parseTopRows());
        lowerThread = new PlayThread(parseBottomRows());
        upperThread.start();
        lowerThread.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(_bitmap, 0, 0, _paint);
        canvas.drawPath(_path, _line);
    }

    private void calculateLinePlacements() {
        int splitHeight = _height / 8;
        int splitWidth = _width / 16;
        _horizontalLines = new Point[7][2];
        _verticalLines = new Point[15][2];
        _logicalGrid = new LogicalGrid(splitWidth, splitHeight);

        for(int lines = 0; lines <= 6; lines++) {
            _horizontalLines[lines][0] = new Point(0, (lines + 1) * splitHeight);
            _horizontalLines[lines][1] = new Point(_width, (lines + 1) * splitHeight);
        }

        for(int lines = 0; lines <= 14; lines++) {
            _verticalLines[lines][0] = new Point((lines + 1) * splitWidth, 0);
            _verticalLines[lines][1] = new Point((lines + 1) * splitWidth, _height);
        }

        _logicalGrid = new LogicalGrid(splitWidth, splitHeight);

        notes = new Notes(8000);
        topRows = new String[16];
        for (int x = 0; x < topRows.length; x++)
            topRows[x] = "";
    }

    private void drawGrid() {

        for(int row = 0; row <= 6; row++) {
            _canvas.drawLine(_horizontalLines[row][0].x, _horizontalLines[row][0].y,
                    _horizontalLines[row][1].x, _horizontalLines[row][1].y, _paint);
        }

        for(int col = 0; col <= 14; col++) {
            _canvas.drawLine(_verticalLines[col][0].x, _verticalLines[col][0].y,
                    _verticalLines[col][1].x, _verticalLines[col][1].y, _paint);
        }

        invalidate();
    }

    private void touch_start(float x, float y) {
        _path.reset();
        _path.moveTo(x, y);
        _x = x;
        _y = y;
    }

    private void touch_move(float x, float y) {
        float dx = Math.abs(x - _x);
        float dy = Math.abs(y - _y);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            _path.quadTo(_x, _y, (x + _x) / 2, (y + _y) / 2);
            _x = x;
            _y = y;
        }
    }

    private void touch_up() {
        _path.lineTo(_x, _y);
        _canvas.drawPath(_path, _line);
        _path.reset();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!drawMode) {
            return true;
        }

        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touch_start(x, y);
                _logicalGrid.getPositionToFill(x, y);
                stopGrid();
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(x, y);
                _logicalGrid.getPositionToFill(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touch_up();
                playGrid();
                invalidate();
                break;
        }
        return true;
    }

   /* public class TrackOrgTask extends AsyncTask<Void, Void, Void> {

        MusicPlayer musicPlayer;

        @Override
        protected void onPreExecute() {
            musicPlayer = new MusicPlayer();
            musicPlayer.setTracks(parseBottomRows(), parseTopRows());
        }

        @Override
        protected Void doInBackground(Void... params) {

            musicPlayer.playTop();

            return null;
        }

        public void stop() {
            if (musicPlayer != null) {
                musicPlayer.stop();
                musicPlayer = null;
            }
        }

    }*/

    private class PlayThread extends Thread {

        List<double[]> track;
        boolean play = true;
        AudioGenerator audio;

        public PlayThread(List<double[]> track) {
            super();
            this.track = track;
        }

        public void run() {

            audio = new AudioGenerator(8000);
            audio.createPlayer();

            while (play) {
                for (double[] phrase : track) {
                    audio.writeSound(phrase);
                    if (!play)
                        break;
                }
            }
            audio.destroyAudioTrack();
        }

        public synchronized void requestStop() {
            play = false;
        }

    }

    private List<double[]> parseTopRows() {
        // boolean[][] currentBoolean = _logicalGrid.returnPositions();
        String[] topRows = _logicalGrid.getTopRows();

        List<double[]> allColumns = new ArrayList<double[]>();

        for (int c = 0; c < topRows.length; c++)
            allColumns.add(notes.getUpperPhrase(topRows[c]));

        return allColumns;
    }

    private List<double[]> parseBottomRows() {

        String[] bottomRows = _logicalGrid.getBottomRows();

        List<double[]> allColumns = new ArrayList<double[]>();

        for (int c = 0; c < bottomRows.length; c++)
            allColumns.add(notes.getLowerPhrase(bottomRows[c]));

        return allColumns;
    }
}
