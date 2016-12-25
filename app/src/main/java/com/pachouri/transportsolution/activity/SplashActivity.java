package com.pachouri.transportsolution.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.animation.Interpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;
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
import com.pachouri.transportsolution.models.HistoryModel;
import com.pachouri.transportsolution.models.ReceiverDetailsModel;
import com.pachouri.transportsolution.util.CommonUtil;
import com.pachouri.transportsolution.widgets.AppButton;

import java.util.List;

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

    private static final int INITIAL_DELAY = 1500;
    private static final int BACKGROUND_ANIMATION_DURATION = 1000;
    Interpolator interpolation;

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
                if (userLifecycleModel == UserLifecycleModel.UserStatus.LoggedIn)
                    openHomeScreen();
                else if (userLifecycleModel == UserLifecycleModel.UserStatus.AdharRegistrationNotComplete) {
                    finish();
                    Intent intent = new Intent(SplashActivity.this, AadharVerification.class);
                    startActivity(intent);
                } else if (userLifecycleModel == UserLifecycleModel.UserStatus.NotLoggedIn) {

                    CommonUtil.animateViewColor(rlContainer, CommonUtil.BACKGROUND, ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary), android.R.color.white, BACKGROUND_ANIMATION_DURATION);
                    CommonUtil.animateViewColor(txtTitle, CommonUtil.TEXT_COLOR, android.R.color.white, ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary), BACKGROUND_ANIMATION_DURATION);
                    txtTitle.animate().translationY(0).setInterpolator(interpolation).setDuration(800);
                    appButton.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            appButton.animate().alpha(1).setInterpolator(interpolation).setDuration(700);
                            loginContent.animate().alpha(1).translationY(0).setInterpolator(interpolation).setDuration(700);
                            txtContent.animate().alpha(1).translationY(0).setInterpolator(interpolation).setDuration(700);
                        }
                    }, 300);

                } else if (userLifecycleModel == UserLifecycleModel.UserStatus.PersonalProfileNotComplete) {
                    UserModel userModel = UserModel.getInstance(getApplicationContext());
                    redirectToHomeActivity(userModel.getMobileNumber());
                }

            }
        }, INITIAL_DELAY);
    }

    private void openHomeScreen() {
        finish();
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_login)
    protected void onClickLogin() {
        List<ReceiverDetailsModel> list = getReceivers();
        if (list != null) {
            if (list.size() == 0) {
                saveReceiver1();
                saveReceiver2();
                saveReceiver3();
                saveReceiver4();
                saveReceiver5();
            }
        }

        List<HistoryModel> historyList = getHistory();
        if (historyList != null) {
            if (list.size() == 0) {
                saveSomeHistories();
            }
        }

        initiateAccountKitLogin();
//        openHome();
    }

    private void initViews() {
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
        handleAccountKitResult(requestCode, resultCode, data);
    }

    private void redirectToHomeActivity(String phoneNumber) {
        finish();
        Intent intent = new Intent(this, PersonalProfile.class);
        intent.putExtra(Constants.PREF_KEY_PHONE_NUMBER, phoneNumber);

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

    private void openHome() {


        Intent open = new Intent(this, HomeActivity.class);
        startActivity(open);
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
                        UserModel.setUpInstance(getApplicationContext(), userModel);

                        if (UserModel.isUserAlreadyRegistered(phoneNumber.getPhoneNumber())) {
                            openHomeScreen();
                        } else
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

    private void saveReceiver1() {
        ReceiverDetailsModel model = new ReceiverDetailsModel();
        model.setImageUrl("https://lh3.googleusercontent.com/-B7ObxLWBsRU/AAAAAAAAAAI/AAAAAAAAW_w/VGt2B9ZL1k4/s46-c-k-no/photo.jpg");
        model.setFirstName("Ankit");
        model.setLastName("Pachouri ");
        model.setEmail("ankit@gmail.com");
        model.setMobileNumber("9623435159");
        model.setPlaceFrom("Pune");
        model.setPlaceTo("Mumbai");
        model.setLeavingTime("12:40 am");
        model.setDeliveryCharges("100");
        model.save();
    }

    private void saveReceiver2() {
        ReceiverDetailsModel model = new ReceiverDetailsModel();
        model.setImageUrl("http://wallpaper-gallery.net/image.php?pic=/images/profile-pics/profile-pics-20.jpg");
        model.setFirstName("Riyaz");
        model.setLastName("Ahmed");
        model.setEmail("riyaz@gmail.com");
        model.setMobileNumber("9078904567");
        model.setPlaceFrom("Pune");
        model.setPlaceTo("Bangalore");
        model.setLeavingTime("02:40 pm");
        model.setDeliveryCharges("500");
        model.save();
    }

    private void saveReceiver3() {
        ReceiverDetailsModel model = new ReceiverDetailsModel();
        model.setImageUrl("https://organicthemes.com/demo/profile/files/2012/12/profile_img.png");
        model.setFirstName("Sagar");
        model.setLastName("Yadav");
        model.setEmail("sagar@gmail.com");
        model.setMobileNumber("9009000772");
        model.setPlaceFrom("Indore");
        model.setPlaceTo("Pune");
        model.setLeavingTime("05:30 pm");
        model.setDeliveryCharges("300");
        model.save();
    }

    private void saveReceiver4() {
        ReceiverDetailsModel model = new ReceiverDetailsModel();
        model.setImageUrl("http://wallpaper-gallery.net/image.php?pic=/images/profile-pics/profile-pics-11.jpg");
        model.setFirstName("Jagdish");
        model.setLastName("Pachouri ");
        model.setEmail("jagdish@gmail.com");
        model.setMobileNumber("9827244543");
        model.setPlaceFrom("Indore");
        model.setPlaceTo("Mumbai");
        model.setLeavingTime("07:00 pm");
        model.setDeliveryCharges("50");
        model.save();
    }

    private void saveReceiver5() {
        ReceiverDetailsModel model = new ReceiverDetailsModel();
        model.setImageUrl("http://wallpaper-gallery.net/image.php?pic=/images/profile-pics/profile-pics-18.jpg");
        model.setFirstName("Utkarsh");
        model.setLastName("Parashar");
        model.setEmail("utkarsh@gmail.com");
        model.setMobileNumber("9623435159");
        model.setPlaceFrom("Pune");
        model.setPlaceTo("Mumbai");
        model.setLeavingTime("12:40 am");
        model.setDeliveryCharges("100");
        model.save();
    }

    public static List<ReceiverDetailsModel> getReceivers() {
        return new Select()
                .from(ReceiverDetailsModel.class)
                .execute();
    }

    private void saveSomeHistories() {
        for (int i = 0; i < 5; i++) {
            HistoryModel model = new HistoryModel();
            model.setImageUrl("https://lh3.googleusercontent.com/-B7ObxLWBsRU/AAAAAAAAAAI/AAAAAAAAW_w/VGt2B9ZL1k4/s46-c-k-no/photo.jpg");
            model.setFirstName("Ankit");
            model.setLastName("Pachouri " + i);
            model.setEmail("email" + i);
            model.setMobileNumber("000000000" + i);
            model.setItem("Item: " + i);
            model.setDeliveredAddress("Mumbai");
            model.setDeliveredTime("00 : 0" + i);
            model.setDeliveryCharges(i + "");
            model.save();
        }
    }

    public static List<HistoryModel> getHistory() {
        return new Select()
                .from(HistoryModel.class)
                .execute();
    }
}
