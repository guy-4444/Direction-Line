package com.guy4444.directionlineapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import directionlineapp.guy4444.com.directionline.DirectionLineLayout;

public class MainActivity extends AppCompatActivity {

    DirectionLineLayout directionLineLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final int SIZE = 30;

        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            nums.add((int) (i*(360.0 / SIZE)));
        }

        directionLineLayout = (DirectionLineLayout) findViewById(R.id.directionLineLayout);

        directionLineLayout.setStepLines(this, DirectionLineLayout.LayoutOrientation.HORIZONTAL, 2, SIZE, R.color.skv_arrow_color, 80, R.drawable.ic_arrow);
        for (int i = 0; i < directionLineLayout.getSize(); i++) {
            directionLineLayout.setUnitDirection(i, nums.get(i));
            int color = Color.HSVToColor(255, new float[]{(float) nums.get(i), 1.0f, 1.0f});
            directionLineLayout.setUnitColor(i, (int) color);
        }

    }
}
