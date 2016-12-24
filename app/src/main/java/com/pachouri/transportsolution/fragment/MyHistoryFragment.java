package com.pachouri.transportsolution.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pachouri.transportsolution.R;
import com.pachouri.transportsolution.activity.HomeActivity;
import com.pachouri.transportsolution.interfaces.FragmentCommunicator;

/**
 * Created by ankit on 12/25/16.
 */

public class MyHistoryFragment extends Fragment {

    public MyHistoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_my_history_fragment, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

    }

    private void setToolbarTitle() {
        ((HomeActivity) getActivity()).setMainToolbarTitle(getString(R.string.bottom_menu_my_history));
        if (getActivity() instanceof FragmentCommunicator) {
            ((FragmentCommunicator) getActivity()).fragmentAttached(FragmentCommunicator.BottomBarTab
                    .HISTORY);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setToolbarTitle();
    }
}
