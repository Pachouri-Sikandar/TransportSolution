package com.pachouri.transportsolution.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.bumptech.glide.Glide;
import com.pachouri.transportsolution.BaseActivity;
import com.pachouri.transportsolution.BaseApplication;
import com.pachouri.transportsolution.Constants;
import com.pachouri.transportsolution.R;
import com.pachouri.transportsolution.models.UserLifecycleModel;
import com.pachouri.transportsolution.models.UserModel;
import com.pachouri.transportsolution.util.CommonUtil;
import com.pachouri.transportsolution.widgets.AppTextView;

import java.io.File;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class PersonalProfile extends BaseActivity {

    String mobilePhone;

    @Bind(R.id.edt_first_name)
    EditText editTextFirstName;

    @Bind(R.id.edt_last_name)
    EditText editTextLastName;

    @Bind(R.id.edt_email)
    EditText editTextEmail;

    @Bind(R.id.img_user_profile)
    CircleImageView circleImageView;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.textViewToolbarTitle)
    AppTextView appTextTitle;

    private File profilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_profile);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        appTextTitle.setTextColor(ContextCompat.getColor(getApplicationContext(),android.R.color.white));
        appTextTitle.setText("Profile");

        mobilePhone = getIntent().getStringExtra(Constants.PREF_KEY_PHONE_NUMBER);
        UserLifecycleModel.setUserCurrentState(getApplicationContext(), UserLifecycleModel.UserStatus.PersonalProfileNotComplete);
    }


    @OnClick(R.id.btn_save)
    protected void onClickSave(){
        if(editTextFirstName.getText().toString().isEmpty())
            MessageUtils.showToast(getApplicationContext(),"Invalid name");
        else if(!editTextEmail.getText().toString().isEmpty()&&!CommonUtil.isValidEmail(editTextEmail.getText()))
            MessageUtils.showToast(getApplicationContext(),"Invalid email");
        else{
            UserModel userModel = new UserModel();
            userModel.setFirstName(editTextFirstName.getText().toString());
            userModel.setLastName(editTextLastName.getText().toString());
            userModel.setEmail(editTextEmail.getText().toString());
            if(profilePic!=null)
                userModel.setImageUrl(profilePic.getAbsolutePath());
            userModel.setMobileNumber(mobilePhone);
            userModel.save();

            UserLifecycleModel.setUserCurrentState(getApplicationContext(), UserLifecycleModel.UserStatus.LoggedIn);
        }
    }

    @OnClick(R.id.img_user_profile)
    protected void onClickImageCamera(){

        DialogUtil.showImageSelectionDialog(this, new DialogUtil.IImageSelectionDialog() {
            @Override
            public void onClickCamera() {
                MediaSelector.takePhotoActivity(PersonalProfile.this);
            }

            @Override
            public void onClickGallery() {
                MediaSelector.uploadPhotoActivity(PersonalProfile.this);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MediaSelector.handleActivityResult(requestCode, resultCode, data, true,PersonalProfile.this, new MediaSelector.IMediaSelector() {
            @Override
            public void originalImageFile(File originalImage) {}

            @Override
            public void imagePickerError(String message) {}

            @Override
            public void croppedImageFile(File croppedImage) {
                profilePic = croppedImage;
                Glide.with(PersonalProfile.this).load(croppedImage).into(circleImageView);
            }

            @Override
            public void croppedImageUri(String imagePath) {

            }

            @Override
            public void onMediaSelectionCancel() {}
        });
    }
}
