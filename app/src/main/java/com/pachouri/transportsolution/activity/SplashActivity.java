package com.pachouri.transportsolution.activity;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.animation.Interpolator;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.PhoneNumber;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;
import com.pachouri.transportsolution.BaseActivity;
import com.pachouri.transportsolution.Constants;
import com.pachouri.transportsolution.R;
import com.pachouri.transportsolution.models.UserLifecycleModel;
import com.pachouri.transportsolution.models.UserModel;
import com.pachouri.transportsolution.util.CommonUtil;
import com.pachouri.transportsolution.widgets.AppButton;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ankit on 12/24/16.
 */
public class SplashActivity extends BaseActivity {

    private static final int APP_REQUEST_CODE = 12112;

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

    private static final long SCREEN_TIME = 1000;

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

                UserLifecycleModel.UserStatus userLifecycleModel = UserLifecycleModel.getUserCurrentState(getApplicationContext());
                if(userLifecycleModel == UserLifecycleModel.UserStatus.LoggedIn)
                    openHomeScreen();
                else if(userLifecycleModel == UserLifecycleModel.UserStatus.NotLoggedIn) {

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

                }else if(userLifecycleModel == UserLifecycleModel.UserStatus.PersonalProfileNotComplete){
                    UserModel userModel = UserModel.getInstance(getApplicationContext());
                    redirectToHomeActivity(userModel.getMobileNumber());
                }
            }
        },INITIAL_DELAY);
    }

    private void openHomeScreen(){
        finish();
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_login)
    protected void onClickLogin(){
        initiateAccountKitLogin();
    }

    private void initViews(){
        txtTitle.setTranslationY(250);
        txtContent.setAlpha(0);
        appButton.setAlpha(0);
        loginContent.setAlpha(0);

        txtContent.setTranslationY(-70f);
        loginContent.setTranslationY(70f);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        handleAccountKitResult(requestCode,resultCode,data);
    }

    private void redirectToHomeActivity(String phoneNumber) {
        finish();
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(Constants.PREF_KEY_PHONE_NUMBER,phoneNumber);
        startActivity(intent);
        finish();
    }

    private void initiateAccountKitLogin() {
        final Intent intent = new Intent(this, AccountKitActivity.class);
        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(
                        LoginType.PHONE,
                        AccountKitActivity.ResponseType.TOKEN);
        configurationBuilder.setSMSWhitelist(new String[]{"IN", "US", "AE", "GB"});
        configurationBuilder.setDefaultCountryCode("IN");
        configurationBuilder.setReceiveSMS(true);
        configurationBuilder.setTitleType(AccountKitActivity.TitleType.LOGIN);

        // ... perform additional configuration ...
        intent.putExtra(
                AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                configurationBuilder.build());
        startActivityForResult(intent, APP_REQUEST_CODE);
    }

    private void handleAccountKitResult(final int requestCode, final int resultCode, final Intent data) {
        AccountKitLoginResult loginResult = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
        if (loginResult.getError() == null && loginResult.getAccessToken() != null) {

            AccountKit.getCurrentAccount(new AccountKitCallback<com.facebook.accountkit.Account>() {
                @Override
                public void onSuccess(com.facebook.accountkit.Account account) {
                    PhoneNumber phoneNumber = account.getPhoneNumber();
                    if (phoneNumber != null) {
                        UserModel userModel = new UserModel();
                        userModel.setMobileNumber(phoneNumber.getPhoneNumber());
                        UserModel.setUpInstance(getApplicationContext(),userModel);
                        redirectToHomeActivity(phoneNumber.getPhoneNumber());
                    } else {
                        MessageUtils.showToast(getApplicationContext(), "Error #SSA01");
                    }
                }
                @Override
                public void onError(AccountKitError accountKitError) {
                    MessageUtils.showToast(getApplicationContext(), "Error #SSA01");
                }
            });
        } else {
            Toast.makeText(this, "Sorry, Something went wrong.", Toast.LENGTH_LONG).show();
        }
    }
}
