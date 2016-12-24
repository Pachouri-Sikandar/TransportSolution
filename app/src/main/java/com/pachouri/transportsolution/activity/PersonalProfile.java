package com.pachouri.transportsolution.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.pachouri.transportsolution.Constants;
import com.pachouri.transportsolution.R;

import butterknife.Bind;
import butterknife.ButterKnife;

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
}
