package com.pachouri.transportsolution.util;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Created by ankit on 12/24/16.
 */
public class CommonUtil {

    public static final String BACKGROUND="backgroundColor";
    public static final String TEXT_COLOR="textColor";

    public static void animateViewColor(View view, String animationProperty, int previousColor, int nextColor, int duration){
        ObjectAnimator animator = ObjectAnimator.ofInt(view, animationProperty, previousColor, nextColor).setDuration(duration);
        animator.setEvaluator(new ArgbEvaluator());
        animator.start();
        /*"BackgroundColor"*/
    }
}
