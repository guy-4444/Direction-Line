package com.guy4444.directionlineapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import directionlineapp.guy4444.com.directionline.StepLineLayout;

public class MainActivity extends AppCompatActivity {

    //        android:fillColor="#D80027"

    StepLineLayout stepLayout_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final int SIZE = 30;

        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            nums.add((int) (i*(360.0 / SIZE)));
        }

        stepLayout_1 = (StepLineLayout) findViewById(R.id.stepLayout_1);

        stepLayout_1.setStepLines(this, StepLineLayout.LayoutOrientation.HORIZONTAL, 2, SIZE, R.color.skv_arrow_color, 80, R.drawable.ic_arrow);
        for (int i = 0; i < stepLayout_1.getSize(); i++) {
            stepLayout_1.setUnitDirection(i, nums.get(i));
        }

    }
}
