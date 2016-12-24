package com.pachouri.transportsolution.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.animation.Interpolator;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pachouri.transportsolution.BaseActivity;
import com.pachouri.transportsolution.R;
import com.pachouri.transportsolution.util.CommonUtil;
import com.pachouri.transportsolution.widgets.AppButton;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ankit on 12/24/16.
 */
public class SplashActivity extends BaseActivity {

    private static final long SCREEN_TIME = 1000;

    @Bind(R.id.txt_title)
    TextView txtTitle;

    @Bind(R.id.txt_content)
    TextView txtContent;

    @Bind(R.id.txt_login_content)
    TextView loginContent;

    @Bind(R.id.btn_login)
    AppButton appButton;

    @Bind(R.id.container)
    RelativeLayout rlContainer;

    private static final int INITIAL_DELAY=1500;
    private static final int BACKGROUND_ANIMATION_DURATION=1000;
    Interpolator interpolation ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        interpolation = new FastOutSlowInInterpolator();
        initViews();
        initiateAnimation();
    }

    private void initiateAnimation() {
        rlContainer.postDelayed(new Runnable() {
            @Override
            public void run() {
                CommonUtil.animateViewColor(rlContainer,CommonUtil.BACKGROUND, ContextCompat.getColor(getApplicationContext(),R.color.colorPrimary),android.R.color.white,BACKGROUND_ANIMATION_DURATION);
                CommonUtil.animateViewColor(txtTitle,CommonUtil.TEXT_COLOR,android.R.color.white,ContextCompat.getColor(getApplicationContext(),R.color.colorPrimary),BACKGROUND_ANIMATION_DURATION);
                txtTitle.animate().translationY(0).setInterpolator(interpolation).setDuration(800);
                appButton.postDelayed(new Runnable() {
                    @Override
                    public void run(){
                        appButton.animate().alpha(1).setInterpolator(interpolation).setDuration(700);
                        loginContent.animate().alpha(1).translationY(0).setInterpolator(interpolation).setDuration(700);
                        txtContent.animate().alpha(1).translationY(0).setInterpolator(interpolation).setDuration(700);
                    }
                },300);
            }
        },INITIAL_DELAY);
    }

    private void initViews(){
        txtTitle.setTranslationY(250);
        txtContent.setAlpha(0);
        appButton.setAlpha(0);
        loginContent.setAlpha(0);

        txtContent.setTranslationY(-70f);
        loginContent.setTranslationY(70f);
    }

    private void redirectToHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}
