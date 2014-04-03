package com.example.ToneMatrix;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

/**
 * Created by Dan Voss on 4/2/14.
 */
public class ToneMatrixActivity extends Activity {

    Grid grid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        grid = new Grid(this);
        setContentView(grid);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    @Override
    public void onStop() {
        super.onStop();

        grid.stopGrid();
    }

}