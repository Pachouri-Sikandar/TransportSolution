package com.pachouri.transportsolution.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import com.pachouri.transportsolution.Constants;
import com.pachouri.transportsolution.R;
import com.pachouri.transportsolution.models.UserLifecycleModel;
import com.pachouri.transportsolution.models.UserModel;
import com.pachouri.transportsolution.util.CommonUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonalProfile extends AppCompatActivity {

    String mobilePhone;

    @Bind(R.id.edt_first_name)
    EditText editTextFirstName;

    @Bind(R.id.edt_last_name)
    EditText editTextLastName;

    @Bind(R.id.edt_email)
    EditText editTextEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_profile);
        ButterKnife.bind(this);
        mobilePhone = getIntent().getStringExtra(Constants.PREF_KEY_PHONE_NUMBER);
    }


    @OnClick(R.id.btn_save)
    protected void onClickSave(){
        if(editTextFirstName.getText().toString().isEmpty())
            MessageUtils.showToast(getApplicationContext(),"Invalid name");
        else if(!CommonUtil.isValidEmail(editTextEmail.getText()))
            MessageUtils.showToast(getApplicationContext(),"Invalid email");
        else{
            UserModel userModel = new UserModel();
            userModel.setFirstName(editTextFirstName.getText().toString());
            userModel.setLastName(editTextLastName.getText().toString());
            userModel.setEmail(editTextEmail.getText().toString());
            userModel.setMobileNumber(mobilePhone);
            userModel.save();

            UserLifecycleModel.setUserCurrentState(getApplicationContext(), UserLifecycleModel.UserStatus.LoggedIn);
        }
    }

}
