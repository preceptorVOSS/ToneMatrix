package com.example.ToneMatrix;

import android.graphics.RectF;

/**
 * Created by Dan Voss on 2/17/14.
 * This code is also adapted from the following tutorial:
 * http://www.barebonescoder.com/2010/06/android-development-simple-2d-graphics-part-2/
 */
public class LogicalGrid {

    private int _splitWidth;
    private int _splitHeight;
    GridPosition[][] _positions;
    private String[] topRows;

    public LogicalGrid(int splitWidth, int splitHeight) {
        _splitWidth = splitWidth;
        _splitHeight = splitHeight;
        topRows = new String[16];
        SetupPositions();
    }

    public void getPositionToFill(float x, float y) {
        for(int row = 0; row <= 7; row++) {
            for(int col = 0; col <= 15; col++) {
                if(_positions[row][col].contains(x, y) && !_positions[row][col].isFilled()) {
                    //RectF toReturn = new RectF(_positions[row][col]);
                    _positions[row][col].setFilled(true);
                    //return toReturn;
                    if(row < 4) {
                        switch(row) {
                            case 0:
                                topRows[col] += "A";
                                break;
                            case 1:
                                topRows[col] += "G";
                                break;
                            case 2:
                                topRows[col] += "E";
                                break;
                            default:
                                topRows[col] += "C";
                                break;
                        }
                    }
                }
            }
        }
        //return null;
    }

    private class GridPosition extends RectF {

        private boolean filled;

        public GridPosition(float left, float top, float right, float bottom) {
            super(left, top, right, bottom);
            filled = false;
        }

        public void setFilled(boolean filled) {
            this.filled = filled;
        }

        public boolean isFilled() {
            return filled;
        }
    }

    private void SetupPositions() {
        _positions = new GridPosition[8][16];

        // set up matrix
        for(int row = 0; row <= 7; row++) {
            for(int col = 0; col <=15; col++) {
                _positions[row][col] = new GridPosition(col * _splitWidth, row * _splitHeight,
                        (col + 1) * _splitWidth, (row + 1) * _splitHeight);
            }
        }

        // set up topRows
        for (int col = 0; col < 16; col++){
            topRows[col] = "";
        }
    }

    public boolean[][] returnPositions() {
        boolean[][] booleanGrid = new boolean[_positions.length][_positions[0].length];
        for (int r = 0; r < _positions.length; r++) {
            for (int c = 0; c < _positions[r].length; c++) {
                booleanGrid[r][c] = _positions[r][c].isFilled();
            }
        }
        return booleanGrid;
    }

    public String[] getTopRows() {
        return topRows;
    }
}
