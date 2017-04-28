package com.b2cloud.paulin.codingchallenge.utilities;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Custom textview to use an external font
 * @author Anthony Paulin
 * @since 27-04-2017
 */
public class CustomTextView extends AppCompatTextView {

    /**
     * @param context The app context
     */
    public CustomTextView(Context context) {
        super(context);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "baskvl.ttf");
        this.setTypeface(face);
    }

    /**
     * @param context The app context
     * @param attrs attributes for the textview
     */
    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "baskvl.ttf");
        this.setTypeface(face);
    }

    /**
     *
     * @param context The app context
     * @param attrs attributes for the textview
     * @param defStyleAttr the style definition
     */
    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "baskvl.ttf");
        this.setTypeface(face);
    }

    /**
     * Draw the canva on Draw
     * @param canvas
     */
    protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);
    }
}
