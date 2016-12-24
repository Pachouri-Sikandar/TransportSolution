package com.pachouri.transportsolution.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.pachouri.transportsolution.BaseActivity;
import com.pachouri.transportsolution.R;

/**
 * Created by ankit on 12/24/16.
 */
public class SplashActivity extends BaseActivity {

    private static final long SCREEN_TIME = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        startSplashTimer();
    }

    private void startSplashTimer() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                redirectToHomeActivity();
            }
        }, SCREEN_TIME);
    }

    private void redirectToHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}
