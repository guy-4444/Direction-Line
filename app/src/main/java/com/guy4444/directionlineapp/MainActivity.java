package com.guy4444.directionlineapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import directionlineapp.guy4444.com.directionline.DirectionLineLayout;

public class MainActivity extends AppCompatActivity {

    DirectionLineLayout directionLineLayout1;
    DirectionLineLayout directionLineLayout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        directionLineLayout1 = (DirectionLineLayout) findViewById(R.id.directionLineLayout1);
        directionLineLayout2 = (DirectionLineLayout) findViewById(R.id.directionLineLayout2);

        initView1();
        initView2();


        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int SIZE = 10;
                ArrayList<Integer> nums = new ArrayList<>();
                for (int i = 0; i < SIZE; i++) {
                    nums.add((int) (i*(180 / SIZE)));
                }

                directionLineLayout1.setStepLines(MainActivity.this, DirectionLineLayout.LayoutOrientation.HORIZONTAL, 2, SIZE, R.color.skv_arrow_color, 80, R.drawable.ic_arrow);
                for (int i = 0; i < directionLineLayout1.getSize(); i++) {
                    directionLineLayout1.setUnitDirection(i, nums.get(i));
                    int color = Color.HSVToColor(255, new float[]{(float) nums.get(i), 1.0f, 1.0f});
                    directionLineLayout1.setUnitColor(i, (int) color);
                }
            }
        });
    }

    private void initView1() {
        final int SIZE = 30;
        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            nums.add((int) (i*(360 / SIZE)));
        }

        directionLineLayout1.setStepLines(this, DirectionLineLayout.LayoutOrientation.HORIZONTAL, 2, SIZE, R.color.skv_arrow_color, 80, R.drawable.ic_arrow);
        for (int i = 0; i < directionLineLayout1.getSize(); i++) {
            directionLineLayout1.setUnitDirection(i, nums.get(i));
            int color = Color.HSVToColor(255, new float[]{(float) nums.get(i), 1.0f, 1.0f});
            directionLineLayout1.setUnitColor(i, (int) color);
        }
    }

    private void initView2() {
        final int SIZE = 60;
        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            nums.add((int) (i*(720.0 / SIZE)));
        }

        directionLineLayout2.setStepLines(this, DirectionLineLayout.LayoutOrientation.HORIZONTAL, 1, 60, R.color.skv_arrow_color, 40, R.drawable.ic_arrow);
        for (int i = 0; i < directionLineLayout2.getSize(); i++) {
            double yaw = nums.get(i);
            directionLineLayout2.setUnitDirection(i, (int) yaw);
            double X = Math.abs(Math.sin(yaw/360.0*Math.PI)) * 255;
            int color = Color.rgb((int) X, 0, (int) (255-X));
            directionLineLayout2.setUnitColor(i, color);
        }
    }
}
