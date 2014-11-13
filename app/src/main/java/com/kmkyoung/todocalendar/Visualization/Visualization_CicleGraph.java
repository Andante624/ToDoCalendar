package com.kmkyoung.todocalendar.Visualization;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.kmkyoung.todocalendar.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MinchaeiMac on 14. 11. 6..
 */
public class Visualization_CicleGraph extends View {
    private int width, height;
    private final float radius = (float)3/10;
    private int completed_count, uncompleted_count;

    private int completedColor = R.color.default_green;
    private int uncompletedColor = R.color.default_orange;

    public Visualization_CicleGraph(Context context) {
        super(context);
    }

    public Visualization_CicleGraph(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Visualization_CicleGraph(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setCompleted_count(int completed_count)
    {
        this.completed_count = completed_count;
    }

    public void setUncompleted_count(int uncompleted_count)
    {
        this.uncompleted_count = uncompleted_count;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(uncompletedColor));
        canvas.drawCircle(width/2, height/2, width*radius, paint);

        RectF rf = new RectF(width/2-width*radius,height/2-width*radius,width/2+width*radius,height/2+width*radius);
        paint.setColor(getResources().getColor(completedColor));
        canvas.drawArc(rf,-90,360*((float)completed_count/(completed_count+uncompleted_count)),true,paint);
    }

    @Override
    protected void onMeasure(int widthSpec,int heightSpec)
    {
        width = MeasureSpec.getSize(widthSpec);
        height = MeasureSpec.getSize(heightSpec);

        setMeasuredDimension(width,height);
    }
}
