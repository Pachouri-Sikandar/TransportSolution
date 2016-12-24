package com.pachouri.transportsolution.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.View;

import com.pachouri.transportsolution.Constants;
import com.pachouri.transportsolution.R;

/**
 * Created by ankit on 12/24/16.
 */
public class AppEditText extends AppCompatEditText implements View.OnFocusChangeListener {

    public AppEditText(Context context) {
        super(context);
    }

    public AppEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.customView, 0, 0);
        try {
            int fontIndex = typedArray.getInt(R.styleable.customView_textStyle, 0);
            setTextFont(context, fontIndex);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            typedArray.recycle();
        }
    }

    public AppEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTextFont(Context context, int fontIndex) throws NoSuchFieldException, IllegalAccessException {
        Typeface typeface = new FontHelper(Constants.APP_FONTS).getFont(context, fontIndex);
        setTypeface(typeface);
    }

    @Override
    public void onFocusChange(View view, boolean b) {

    }
}
