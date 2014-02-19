package com.example.ToneMatrix;

import android.content.Context;
import android.graphics.*;
import android.view.View;

/**
 * Created by Dan Voss on 2/17/14.
 * This code is taken and adapted from the following tutorial:
 * http://www.barebonescoder.com/2010/06/android-development-simple-2d-graphics-part-1/
 */
public class Grid extends View {

    int _height;
    int _width;

    Bitmap _bitmap;
    Canvas _canvas;
    Paint _paint;

    Point[][] _horizontalLines;
    Point[][] _verticalLines;

    private LogicalGrid _logicalGrid;

    public Grid(Context context) {
        super(context);
        _paint = new Paint();
        _paint.setColor(Color.BLACK);
        _paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        _height = View.MeasureSpec.getSize(heightMeasureSpec);
        _width = View.MeasureSpec.getSize(widthMeasureSpec);

        setMeasuredDimension(_width, _height);

        _bitmap = Bitmap.createBitmap(_width, _height, Bitmap.Config.ARGB_8888);
        _canvas = new Canvas(_bitmap);
        setBackgroundColor(0xffffffff);

        calculateLinePlacements();
        drawGrid();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(_bitmap, 0, 0, _paint);
    }

    private void calculateLinePlacements() {
        int splitHeight = _height / 8;
        int splitWidth = _width / 16;
        _horizontalLines = new Point[7][2];
        _verticalLines = new Point[15][2];

        for(int lines = 0; lines <= 6; lines++) {
            _horizontalLines[lines][0] = new Point(0, (lines + 1) * splitHeight);
            _horizontalLines[lines][1] = new Point(_width, (lines + 1) * splitHeight);
        }

        for(int lines = 0; lines <= 14; lines++) {
            _verticalLines[lines][0] = new Point((lines + 1) * splitWidth, 0);
            _verticalLines[lines][1] = new Point((lines + 1) * splitWidth, _height);
        }

        _logicalGrid = new LogicalGrid(splitWidth, splitHeight);

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
}
