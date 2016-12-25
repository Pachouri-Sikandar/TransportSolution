package com.pachouri.transportsolution.util;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.view.View;

import com.pachouri.transportsolution.VerhoeffAlgorithm;

import java.util.regex.Pattern;

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

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null)
            return false;

        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public static boolean validateAadharNumber(String aadharNumber){
        Pattern aadharPattern = Pattern.compile("\\d{12}");
        boolean isValidAadhar = aadharPattern.matcher(aadharNumber).matches();
        if(isValidAadhar){
            isValidAadhar = VerhoeffAlgorithm.validateVerhoeff(aadharNumber);
        }
        return isValidAadhar;
    }
}
