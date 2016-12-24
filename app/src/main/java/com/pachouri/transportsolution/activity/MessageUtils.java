package com.pachouri.transportsolution.activity;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by tt-riyaz on 25/12/16.
 */
public class MessageUtils {
    public static void showToast(Context applicationContext, String s) {
        Toast.makeText(applicationContext, s, Toast.LENGTH_SHORT).show();
    }
}
