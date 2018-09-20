package directionlineapp.guy4444.com.directionline;


import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class StepLineLayout extends LinearLayout {

    public int getSize() {
        return stepLineViews.size();
    }

    public enum LayoutOrientation {
        VERTICAL,
        HORIZONTAL;
    }

    private ArrayList<StepLineView> stepLineViews;

    private int mainColor   = Color.argb(255, 0, 0, 200);
    private int markerRadius = 20;
    private int lineSize = 3;
    private int linePadding = 0;
    private LayoutOrientation layoutOrientation = LayoutOrientation.HORIZONTAL;
    private int marker = 0;
    private int padding = 0;

    public StepLineLayout(Context context) {
        this(context, null, 0);
    }

    public StepLineLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StepLineLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.setOrientation(LinearLayout.HORIZONTAL);
    }

    public void setStepLines(Context context, LayoutOrientation _layoutOrientation, int _padding, int _numOfItems, int _mainColor, int _markerRadius, int _marker){
        this.markerRadius = _markerRadius;
        this.marker = _marker;
        this.padding = _padding;
        setStepLines(context, _layoutOrientation, _numOfItems, _mainColor);
    }



    public void setStepLines(Context context, LayoutOrientation _layoutOrientation, int _numOfItems, int _mainColor){

        // If the user send resource int instead of Color int..
        try {
            _mainColor = ResourcesCompat.getColor(getResources(), _mainColor, null);
        } catch (Exception ignored) { }

        this.mainColor = _mainColor;
        setStepLines(context, _layoutOrientation, _numOfItems);
    }

    private void setStepLines(Context context, LayoutOrientation _layoutOrientation, int numOfItems) {
        stepLineViews = new ArrayList<>();

        this.layoutOrientation = _layoutOrientation;
        if (layoutOrientation == LayoutOrientation.HORIZONTAL)
            this.setOrientation(LinearLayout.HORIZONTAL);
        else
            this.setOrientation(LinearLayout.VERTICAL);

        if (numOfItems <=0) {
            return;
        }
        else {
            for (int i = 0; i < numOfItems; i++) {
                StepLineView stepLineView1 = getStepLineUnit(context, layoutOrientation, 100, stepLineViews.size());
                stepLineViews.add(stepLineView1);
            }
        }

        attachListToView();
    }

    private void attachListToView() {
        for (int i = 0; i < stepLineViews.size(); i++) {
            this.addView(stepLineViews.get(i));
        }
    }

    public void setUnitDirection(int unitIndex, int direction) {
        if (unitIndex >= 0 &&  unitIndex < stepLineViews.size())
            stepLineViews.get(unitIndex).setUnitDirection(direction);
    }

    private StepLineView getStepLineUnit(Context context, LayoutOrientation layoutOrientation, int direction, int _position) {
        // markerSize - default=20
        LinearLayout.LayoutParams linearParams;
        if (layoutOrientation == LayoutOrientation.HORIZONTAL)
            linearParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
        else
            linearParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1);

        float density = context.getResources().getDisplayMetrics().density;

        StepLineView stepLineView = new StepLineView(context, null);
        stepLineView.setMarkerSize(markerRadius);
        stepLineView.setMainColor(mainColor);
        stepLineView.setLayoutParams(linearParams);
        stepLineView.setMarkerSize((int) (markerRadius * density));
        stepLineView.setPadding(padding, padding, padding, padding);
        stepLineView.setDrawables(marker);
        stepLineView.setDirection(direction);
        stepLineView.setPosition(_position);

        return stepLineView;
    }
}