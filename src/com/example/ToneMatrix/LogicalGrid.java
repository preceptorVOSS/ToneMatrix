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

    public LogicalGrid(int splitWidth, int splitHeight) {
        _splitWidth = splitWidth;
        _splitHeight = splitHeight;
        SetupPositions();
    }

    public RectF getPositionToFill(float x, float y) {
        for(int row = 0; row <= 7; row++) {
            for(int col = 0; col <= 15; col++) {
                if(_positions[row][col].contains(x, y) && !_positions[row][col].filled) {
                    RectF toReturn = new RectF(_positions[row][col]);
                    _positions[row][col].filled = true;
                    return toReturn;
                }
            }
        }
        return null;
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
    }
}
