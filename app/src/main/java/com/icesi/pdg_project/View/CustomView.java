package com.icesi.pdg_project.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomView extends View {

    private Canvas canvas;
    private RectF rootNode;
    private RectF nodeOne;
    private RectF nodeTwo;
    private RectF nodeLOne;
    private RectF nodeLTwo;
    private RectF nodeROne;
    private RectF nodeRTwo;
    private Paint levelOnePaint;
    private Paint levelTwoPaint;

    public CustomView(Context context) {
        super(context);

        init(null);
    }
    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs);
    }

    private void init(@Nullable AttributeSet set) {

        levelOnePaint= new Paint();
        levelOnePaint.setAntiAlias(true);
        levelOnePaint.setColor(Color.WHITE);
        //Root node----------------
        rootNode = new RectF(1500 * 0.39f, 600 * 0.03f, 1500 * 0.59f, 600 * 0.27f);
        //-------------------------

        //Node one-----------------
        nodeOne = new RectF(1500 * 0.17f, 700 * 0.39f, 1500 * 0.37f, 700 * 0.57f);
        //-------------------------

        //Node two-----------------
        nodeTwo = new RectF(1500 * 0.67f, 700 * 0.39f, 1500 * 0.87f, 700 * 0.57f);
        //-------------------------

        levelTwoPaint= new Paint();
        levelTwoPaint.setAntiAlias(true);
        levelTwoPaint.setColor(Color.WHITE);
        //Node one L one -----------------
        nodeLOne = new RectF(1500 * 0.03f, 700 * 0.69f, 1500 * 0.23f, 700 * 0.87f);
        //-------------------------

        //Node one L two -----------------
        nodeLTwo = new RectF(1500 * 0.30f, 700 * 0.69f, 1500 * 0.50f, 700 * 0.87f);
        //-------------------------

        //Node one R one -----------------
        nodeROne = new RectF(1500 * 0.55f, 700 * 0.69f, 1500 * 0.75f, 700 * 0.87f);
        //-------------------------

        //Node one R two -----------------
        nodeRTwo = new RectF(1500 * 0.85f, 700 * 0.69f, 1500 * 1.05f, 700 * 0.87f);
        //-------------------------

    }

    @Override
    protected void onDraw(Canvas cv) {

        canvas= cv;

        canvas.drawColor(Color.WHITE);

        canvas.drawOval(rootNode, levelOnePaint);


        canvas.drawLine(700f,158,400,300,levelOnePaint);
        canvas.drawOval(nodeOne, levelOnePaint);


        canvas.drawLine(700f,158,1150,300,levelOnePaint);
        canvas.drawOval(nodeTwo, levelOnePaint);


        canvas.drawLine(400f,399,250,500,levelTwoPaint);
        canvas.drawOval(nodeLOne, levelTwoPaint);

        canvas.drawLine(400f,399,500,500,levelTwoPaint);
        canvas.drawOval(nodeLTwo, levelTwoPaint);

        canvas.drawLine(1150f,399,1000,500,levelTwoPaint);
        canvas.drawOval(nodeROne, levelTwoPaint);

        canvas.drawLine(1150f,399,1400,500,levelTwoPaint);
        canvas.drawOval(nodeRTwo, levelTwoPaint);

    }

    public void swapColorLevelOne(){
        levelOnePaint.setColor(Color.RED);
        postInvalidate();
    }

    public void swapColorLevelTwo(){
        levelTwoPaint.setColor(Color.RED);
        postInvalidate();
    }


}
