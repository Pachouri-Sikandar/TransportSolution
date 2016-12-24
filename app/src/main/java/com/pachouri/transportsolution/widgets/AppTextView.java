package com.pachouri.transportsolution.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.pachouri.transportsolution.Constants;
import com.pachouri.transportsolution.R;

/**
 * Created by ankit on 12/24/16.
 */

public class AppTextView extends AppCompatTextView {


    public AppTextView(Context context) {
        super(context);
    }

    public AppTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.customView, 0, 0);
        try {
            int fontIndex = typedArray.getInt(R.styleable.customView_textStyle, 0);
            setTextFont(context, fontIndex);
        } finally {
            typedArray.recycle();
        }
    }

    public AppTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTextFont(Context context, int fontIndex) {
        Typeface typeface = new FontHelper(Constants.APP_FONTS).getFont(context, fontIndex);
        setTypeface(typeface);
    }
}
