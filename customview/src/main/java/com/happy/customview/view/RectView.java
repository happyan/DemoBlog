package com.happy.customview.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.happy.customview.R;

/**
 * Created by showhome002 on 2017/11/28.
 */

public class RectView extends View {

    private Paint mPaint,backgroundPaint;
    Bitmap mBitmap;
    int mColor=Color.RED;

    public RectView(Context context) {
        super(context);init();
    }

    public RectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs , R.styleable.RectView);
        //提取RectView属性集合的rect_color属性，如果没设置默认值为Color.RED
        mColor = typedArray.getColor(R.styleable.RectView_mbackgroud , Color.RED);
        //获取资源后要及时回收
        typedArray.recycle();
        init();
    }

    public RectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setColor(mColor);
        mPaint.setStrokeWidth(5);
        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.GRAY);

        Resources res=getResources();
        BitmapDrawable bmpDraw=(BitmapDrawable)res.getDrawable(R.mipmap.fre);
        mBitmap=bmpDraw.getBitmap();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST ) {
            setMeasuredDimension(400,400);
        }else if (widthSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(400,heightSpecSize);
        }else if (heightSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSpecSize ,400);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width =getWidth();
        int height = getHeight();
        canvas.drawRect(0,0,width,height,mPaint);
//        canvas.drawBitmap(mBitmap ,0 ,0 , mPaint);

    }
}
