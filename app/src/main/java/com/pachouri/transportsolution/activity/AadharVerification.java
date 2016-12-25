package com.pachouri.transportsolution.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.pachouri.transportsolution.R;
import com.pachouri.transportsolution.models.UserLifecycleModel;
import com.pachouri.transportsolution.widgets.AppTextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AadharVerification extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.textViewToolbarTitle)
    AppTextView appTextTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aadhar_verification);
        ButterKnife.bind(this);

        appTextTitle.setText("Aadhar Verification");
        appTextTitle.setTextColor(ContextCompat.getColor(getApplicationContext(), android.R.color.white));

        UserLifecycleModel.setUserCurrentState(getApplicationContext(), UserLifecycleModel.UserStatus.AdharRegistrationNotComplete);
    }
}
