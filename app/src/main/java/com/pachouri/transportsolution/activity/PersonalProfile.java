package com.pachouri.transportsolution.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pachouri.transportsolution.Constants;
import com.pachouri.transportsolution.R;

public class PersonalProfile extends AppCompatActivity {

    String mobilePhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_profile);
        mobilePhone = getIntent().getStringExtra(Constants.PREF_KEY_PHONE_NUMBER);
    }
}
