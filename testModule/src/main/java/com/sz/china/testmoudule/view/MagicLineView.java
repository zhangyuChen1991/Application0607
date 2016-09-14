package com.sz.china.testmoudule.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * 简单的规律绘制直线构造神奇的视觉效果
 * Created by zhangyu on 2016/9/7.
 */
public class MagicLineView extends View {
    private static final String TAG = "MagicLineView";
    //起点在x、y移动范围
    private float p1XLength = 400, p1YLength = 20, speedP1 = 0.15f;
    private float p2XLength = 20, p2YLength = 400, speedP2 = 0.05f;
    private double angleP1 = 0, angleP2 = 0;
    private int viewWidth, viewHeight;
    private Paint paint;
    private ValueAnimator valueAnimator;
    private List<CorrdinateData> corrDatas;
    private DrawingListener drawingListener;
    private int animDuration = 15 * 1000;

    public MagicLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MagicLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MagicLineView(Context context) {
        super(context);
        init();
    }

    private void init() {
        setBackgroundColor(Color.BLACK);
        valueAnimator = ValueAnimator.ofFloat(0f, 1f);
        valueAnimator.setDuration(animDuration);
        valueAnimator.addUpdateListener(animatorUpdateListener);
        valueAnimator.addListener(animatorListener);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);

        corrDatas = new ArrayList<>();
    }

//    private int[] colors = new int[]{Color.RED, Color.WHITE, Color.BLUE};

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < corrDatas.size(); i++) {
            CorrdinateData cd = corrDatas.get(i);
//            Shader shader = new LinearGradient(cd.p1X, cd.p1Y, cd.p2X, cd.p2Y, colors, null, Shader.TileMode.MIRROR);
//            paint.setShader(shader);
            canvas.drawLine(cd.p1X, cd.p1Y, cd.p2X, cd.p2Y, paint);
        }
    }

    private ValueAnimator.AnimatorUpdateListener animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            calculate();
            invalidate();
        }
    };

    Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {
            if (null != drawingListener)
                drawingListener.drawStart();
            corrDatas.clear();
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            if (null != drawingListener)
                drawingListener.drawOver();
        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    };

    /**
     * 开始绘制
     */
    public void startDraw() {
        valueAnimator.start();
    }

    /**
     * 计算坐标值
     */
    private void calculate() {

        angleP1 = angleP1 + speedP1;
        angleP2 = angleP2 + speedP2;

        float nowP1X = (float) (p1XLength * Math.cos(angleP1) + viewWidth / 2f);
        float nowP1Y = (float) (p1YLength * Math.sin(angleP1) + viewHeight / 2f);
        float nowP2X = (float) (p2XLength * Math.cos(angleP2) + viewWidth / 2f);
        float nowP2Y = (float) (p2YLength * Math.sin(angleP2) + viewHeight / 2f);

        CorrdinateData corrdinataData = new CorrdinateData(nowP1X, nowP1Y, nowP2X, nowP2Y);
        corrDatas.add(corrdinataData);
    }

    class CorrdinateData {
        float p1X, p1Y, p2X, p2Y;

        CorrdinateData(float p1X, float p1Y, float p2X, float p2Y) {
            this.p1X = p1X;
            this.p1Y = p1Y;
            this.p2X = p2X;
            this.p2Y = p2Y;
        }
    }

    /**
     * 设置参数 * @param p1X
     */
    public void setParam(float p1XLength, float p1YLength, float p2XLength, float p2YLength, float speedP1, float speedP2) {
        this.p1XLength = p1XLength;
        this.p1YLength = p1YLength;
        this.p2XLength = p2XLength;
        this.p2YLength = p2YLength;
        this.speedP1 = speedP1;
        this.speedP2 = speedP2;
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus) {
            viewWidth = getWidth();
            viewHeight = getHeight();
        }
    }


    public void setDrawingListener(DrawingListener drawingListener) {
        this.drawingListener = drawingListener;
    }

    public interface DrawingListener {
        public void drawStart();
        public void drawOver();
    }
}
