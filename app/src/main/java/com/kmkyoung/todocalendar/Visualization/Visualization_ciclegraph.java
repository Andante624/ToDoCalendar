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
    List<CicleGraphItem> items = new ArrayList<CicleGraphItem>();
    private int width, height;
    private final float radius = (float)1/5;

    public Visualization_CicleGraph(Context context) {
        super(context);
    }

    public Visualization_CicleGraph(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Visualization_CicleGraph(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color.default_blue));
        RectF rf = new RectF();

    }

    @Override
    protected void onMeasure(int widthSpec,int heightSpec)
    {
        width = widthSpec;
        height = heightSpec;

        setMeasuredDimension(width,height);
    }

    class CicleGraphItem
    {
        private String name;
        private int number;

        CicleGraphItem(String name, int number)
        {
            this.name = name;
            this.number = number;
        }

        public void setName(String name)
        {
            this.name = name;
        }
        public void setNumber(int number)
        {
            this.number = number;
        }
        public String getName()
        {
            return name;
        }
        public int getNumber()
        {
            return number;
        }
    }
}
