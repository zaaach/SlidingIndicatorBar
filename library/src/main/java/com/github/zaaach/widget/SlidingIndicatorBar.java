package com.github.zaaach.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * @Author: Zaaach
 * @Date: 2023/5/6
 * @Description: 可以弯曲的滑动指示条
 */
public class SlidingIndicatorBar extends View {
    private int barHeight;
    private int barColor;
    private int bendingHeight;
    private float bendingRatio;
    private Paint barPaint;
    private Paint circlePaint;
    private final RectF rectF = new RectF();
    private final Path path = new Path();

    public SlidingIndicatorBar(Context context) {
        this(context, null);
    }

    public SlidingIndicatorBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingIndicatorBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SlidingIndicatorBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SlidingIndicatorBar);
        barHeight = array.getDimensionPixelSize(R.styleable.SlidingIndicatorBar_sib_bar_height, 12);
        barColor = array.getColor(R.styleable.SlidingIndicatorBar_sib_bar_color, Color.LTGRAY);
        bendingHeight = array.getDimensionPixelSize(R.styleable.SlidingIndicatorBar_sib_bending_height, 60);
        bendingRatio = array.getFloat(R.styleable.SlidingIndicatorBar_sib_bending_ratio, 0);
        array.recycle();

        barPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        barPaint.setColor(barColor);
        barPaint.setStyle(Paint.Style.STROKE);
        barPaint.setStrokeWidth(barHeight);
        barPaint.setStrokeJoin(Paint.Join.ROUND);

        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor(barColor);
        circlePaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        int hSize = MeasureSpec.getSize(heightMeasureSpec);
        int height;

        if (hMode == MeasureSpec.EXACTLY) {
            height = hSize;
        } else {
            if (bendingRatio <= 0) {
                height = barHeight + getPaddingTop() + getPaddingBottom();
            } else {
                int h = (int) (barHeight + bendingHeight * bendingRatio);
                height = Math.min(h + getPaddingTop() + getPaddingBottom(), hSize);
            }
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float radius = barHeight / 2f;
        if (bendingRatio <= 0) {
            rectF.set(getPaddingLeft(), getPaddingTop(),
                    getWidth() - getPaddingRight(), getPaddingTop() + barHeight);
            barPaint.setStyle(Paint.Style.FILL);
            canvas.drawRoundRect(rectF, radius, radius, barPaint);
        } else {
            path.reset();
            path.moveTo(getPaddingLeft() + radius, getPaddingTop() + radius);
            float x = getPaddingLeft() + (getWidth() - getPaddingLeft() - getPaddingRight()) / 2f;
            float y = getPaddingTop() + barHeight / 2f + bendingHeight * bendingRatio;
            path.lineTo(x, y);
            path.lineTo(getWidth() - getPaddingRight() - radius, getPaddingTop() + radius);
            barPaint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(path, barPaint);
            canvas.drawCircle(getPaddingLeft() + radius, getPaddingTop() + radius,
                    radius, circlePaint);
            canvas.drawCircle(getWidth() - getPaddingRight() - radius, getPaddingTop() + radius,
                    radius, circlePaint);
        }
    }
}
