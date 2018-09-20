package directionlineapp.guy4444.com.directionline;


import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class DirectionLineLayout extends LinearLayout {

    public int getSize() {
        return directionLineViews.size();
    }

    public enum LayoutOrientation {
        VERTICAL,
        HORIZONTAL;
    }

    private ArrayList<DirectionLineView> directionLineViews;

    private int mainColor   = Color.argb(255, 0, 0, 200);
    private int markerRadius = 20;
    private int lineSize = 3;
    private int linePadding = 0;
    private LayoutOrientation layoutOrientation = LayoutOrientation.HORIZONTAL;
    private int marker = 0;
    private int padding = 0;

    public DirectionLineLayout(Context context) {
        this(context, null, 0);
    }

    public DirectionLineLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DirectionLineLayout(Context context, AttributeSet attrs, int defStyleAttr) {
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
        directionLineViews = new ArrayList<>();

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
                DirectionLineView directionLineView1 = getStepLineUnit(context, layoutOrientation, 100, directionLineViews.size());
                directionLineViews.add(directionLineView1);
            }
        }

        attachListToView();
    }

    private void attachListToView() {
        for (int i = 0; i < directionLineViews.size(); i++) {
            this.addView(directionLineViews.get(i));
        }
    }

    public void setUnitDirection(int unitIndex, int direction) {
        if (unitIndex >= 0 &&  unitIndex < directionLineViews.size())
            directionLineViews.get(unitIndex).setUnitDirection(direction);
    }

    public void setUnitColor(int unitIndex, int color) {
        if (unitIndex >= 0 &&  unitIndex < directionLineViews.size())
            directionLineViews.get(unitIndex).setUnitColor(color);
    }

    private DirectionLineView getStepLineUnit(Context context, LayoutOrientation layoutOrientation, int direction, int _position) {
        // markerSize - default=20
        LinearLayout.LayoutParams linearParams;
        if (layoutOrientation == LayoutOrientation.HORIZONTAL)
            linearParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
        else
            linearParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1);

        float density = context.getResources().getDisplayMetrics().density;

        DirectionLineView directionLineView = new DirectionLineView(context, null);
        directionLineView.setMarkerSize(markerRadius);
        directionLineView.setMainColor(mainColor);
        directionLineView.setLayoutParams(linearParams);
        directionLineView.setMarkerSize((int) (markerRadius * density));
        directionLineView.setPadding(padding, padding, padding, padding);
        directionLineView.setDrawables(marker);
        directionLineView.setDirection(direction);
        directionLineView.setPosition(_position);

        return directionLineView;
    }
}