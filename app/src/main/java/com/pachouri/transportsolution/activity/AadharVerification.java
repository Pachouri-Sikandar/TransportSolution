package com.pachouri.transportsolution.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import com.pachouri.transportsolution.R;
import com.pachouri.transportsolution.models.UserLifecycleModel;
import com.pachouri.transportsolution.widgets.AppButton;
import com.pachouri.transportsolution.widgets.AppTextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AadharVerification extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.textViewToolbarTitle)
    AppTextView appTextTitle;

    @Bind(R.id.edt_aadhar_number)
    EditText editText;

    @Bind(R.id.btn_verify)
    AppButton appButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aadhar_verification);
        ButterKnife.bind(this);

        appTextTitle.setText("Aadhar Verification");
        appTextTitle.setTextColor(ContextCompat.getColor(getApplicationContext(), android.R.color.white));
        UserLifecycleModel.setUserCurrentState(getApplicationContext(), UserLifecycleModel.UserStatus.AdharRegistrationNotComplete);
    }


    @OnClick(R.id.btn_verify)
    protected void onClickVerify(){
        String aadhar = editText.getText().toString();


    }
}
