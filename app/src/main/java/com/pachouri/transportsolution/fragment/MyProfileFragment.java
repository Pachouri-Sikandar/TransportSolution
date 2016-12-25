package com.pachouri.transportsolution.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pachouri.transportsolution.R;
import com.pachouri.transportsolution.activity.HomeActivity;
import com.pachouri.transportsolution.interfaces.FragmentCommunicator;
import com.pachouri.transportsolution.models.UserModel;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ankit on 12/25/16.
 */

public class MyProfileFragment extends Fragment {

    @Bind(R.id.imageViewUser)
    protected ImageView imageViewUser;
    @Bind(R.id.textViewEmail)
    protected TextView textViewEmail;
    @Bind(R.id.textViewPhoneNumber)
    protected TextView textViewPhoneNumber;
    @Bind(R.id.textViewFirstName)
    protected TextView textViewFirstName;
    @Bind(R.id.textViewLastName)
    protected TextView textViewLastName;
    @Bind(R.id.textViewAadhar)
    protected TextView textViewAadhar;
    @Bind(R.id.textViewVehicleRC)
    protected TextView textViewVehicleRC;
    private Context attachContext;

    public MyProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_my_profile_fragment, container, false);
        ButterKnife.bind(this, view);
        setProfileDetails();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        attachContext = context;
    }

    @Override
    public void onDestroyView() {
        attachContext = null;
        super.onDestroyView();
    }

    private void setToolbarTitle() {
        ((HomeActivity) getActivity()).setMainToolbarTitle(getString(R.string.bottom_menu_my_profile));
        if (getActivity() instanceof FragmentCommunicator) {
            ((FragmentCommunicator) getActivity()).fragmentAttached(FragmentCommunicator.BottomBarTab
                    .PROFILE);
        }
    }

    private void setProfileDetails() {
        UserModel userModel = getUserDetails();
        if (userModel != null) {
            textViewEmail.setText(userModel.getEmail());
            textViewPhoneNumber.setText(userModel.getMobileNumber());
            textViewFirstName.setText(userModel.getFirstName());
            textViewLastName.setText(userModel.getLastName());
            Picasso.with(attachContext).load(userModel.getImageUrl());
            textViewAadhar.setText(userModel.getAadharNumber());
            if (userModel.getVeichle() != null) {
                textViewVehicleRC.setText(userModel.getVeichle());
            }
        }
    }

    private UserModel getUserDetails() {
        UserModel userModel = UserModel.getInstance(attachContext);
        return userModel;
    }

    @Override
    public void onResume() {
        super.onResume();
        setToolbarTitle();
    }
}
