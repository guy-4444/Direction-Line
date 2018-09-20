package directionlineapp.guy4444.com.directionline;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

class DirectionLineView extends View {

    private Drawable mMarker;
    private int mMarkerSize;
    private int direction;

    private int mainColor = Color.argb(255, 0, 255, 0);
    private int secondColor = Color.argb(255, 0, 255, 0);

    private Rect mBounds;
    private Context mContext;

    private int position = -1;
    private int padding = 0;
    Drawable a1;

    public DirectionLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs,R.styleable.timeline_style);
        mMarker = typedArray.getDrawable(R.styleable.timeline_style_marker);
        mMarkerSize = typedArray.getDimensionPixelSize(R.styleable.timeline_style_markerSize, dpToPx(20, mContext));
        typedArray.recycle();

        if(mMarker == null) {
            mMarker = mContext.getResources().getDrawable(R.drawable.ic_arrow);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //Width measurements of the width and height and the inside view of child controls
        int w = mMarkerSize + getPaddingLeft() + getPaddingRight();
        int h = mMarkerSize + getPaddingTop() + getPaddingBottom();

        // Width and height to determine the final view through a systematic approach to decision-making
        int widthSize = resolveSizeAndState(w, widthMeasureSpec, 0);
        int heightSize = resolveSizeAndState(h, heightMeasureSpec, 0);

        setMeasuredDimension(widthSize, heightSize);
        initDrawable();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // When the view is displayed when the callback
        // Positioning Drawable coordinates, then draw
        initDrawable();
    }

    private void initDrawable() {
        int pLeft = getPaddingLeft();
        int pRight = getPaddingRight();
        int pTop = getPaddingTop();
        int pBottom = getPaddingBottom();

        int width = getWidth();// Width of current custom view
        int height = getHeight();

        int cWidth = width - pLeft - pRight;// Circle width
        int cHeight = height - pTop - pBottom;

        int markSize = Math.min(mMarkerSize, Math.min(cWidth, cHeight));

        if(mMarker != null) {
            mMarker.setBounds((width/2) - (markSize/2) + padding,(height/2) - (markSize/2) + padding, (width/2) + (markSize/2) - padding,(height/2) + (markSize/2) - padding);
            mBounds = mMarker.getBounds();
        }

//        { //Marker in center is false
//            if(mMarker != null) {
//                mMarker.setBounds(pLeft,pTop,pLeft + markSize,pTop + markSize);
//                mBounds = mMarker.getBounds();
//            }
//        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mMarker != null) {
            mMarker.draw(canvas);
        }
    }

    public void setMarker(Drawable marker) {
        mMarker = marker;
        initDrawable();
    }

    public void setMarker(Drawable marker, int color) {
        mMarker = marker;
        mMarker.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        initDrawable();
    }

    public void setMarkerColor(int color) {
        mMarker.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        initDrawable();
    }

    public void setMarkerSize(int markerSize) {
        mMarkerSize = markerSize;
        initDrawable();
    }

    public int dpToPx(float dp, Context context) {
        return dpToPx(dp, context.getResources());
    }

    public int dpToPx(float dp, Resources resources) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
        return (int) px;
    }

    public Drawable getDrawable(Context context, int drawableResId) {
        return VectorDrawableCompat.create(context.getResources(), drawableResId, context.getTheme());
    }

    public Drawable getDrawable(Context context, Drawable drawable, int colorFilter) {
        try {
            drawable.setColorFilter(ContextCompat.getColor(context, colorFilter), PorterDuff.Mode.SRC_IN);
        }
        catch (Exception ex) {
            drawable.setColorFilter(colorFilter, PorterDuff.Mode.SRC_IN);
        }
        return drawable;
    }

    public void setDrawables() {
        a1 = getDrawable(mContext, R.drawable.ic_arrow).mutate();

        a1.setColorFilter(mainColor, PorterDuff.Mode.SRC_IN);

        this.setMarker(a1);
    }

    public void setDrawables(int customMarker) {
        if (customMarker!=0) {
            a1 = getDrawable(mContext, customMarker).mutate();

            a1.setColorFilter(mainColor, PorterDuff.Mode.SRC_IN);

            this.setMarker(a1);
        }
        else {
            setDrawables();
        }

    }

    public void setMainColor(int _mainColor) {
        this.mainColor = _mainColor;
    }

    public void setSecondColor(int _secondColor) {
        this.secondColor = _secondColor;
    }

    public void setUnitDirection(int _direction) {
        this.direction = _direction;

        this.setRotation(direction);
        invalidate();
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getMainColor() {
        return mainColor;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setUnitColor(int _mainColor) {
        this.mainColor = _mainColor;
        setDrawables();
    }
}