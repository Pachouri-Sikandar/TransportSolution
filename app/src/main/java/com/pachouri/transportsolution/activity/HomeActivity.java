package com.pachouri.transportsolution.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.pachouri.transportsolution.BaseActivity;
import com.pachouri.transportsolution.Constants;
import com.pachouri.transportsolution.R;
import com.pachouri.transportsolution.util.CommonUtil;
import com.pachouri.transportsolution.fragment.MyHistoryFragment;
import com.pachouri.transportsolution.fragment.MyProfileFragment;
import com.pachouri.transportsolution.fragment.ReceiverFragment;
import com.pachouri.transportsolution.interfaces.FragmentCommunicator;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ankit on 12/24/16.
 */
public class HomeActivity extends BaseActivity implements FragmentCommunicator {

    private static String BOTTOM_BAR_FONT = "fonts/OpenSans/OpenSans-Regular.ttf";
    @Bind(R.id.toolbar)
    protected Toolbar toolbar;
    @Bind(R.id.textViewToolbarTitle)
    protected TextView textViewToolbarTitle;
    private BottomBar bottomBar;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        fragmentManager = getSupportFragmentManager();
        setBottomBar(savedInstanceState);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        bottomBar.onSaveInstanceState(outState);
    }

    private void setBottomBar(Bundle savedInstanceState) {
        bottomBar = BottomBar.attach(this, savedInstanceState);
        bottomBar.useFixedMode();
        bottomBar.setTypeFace(BOTTOM_BAR_FONT);
        bottomBar.setActiveTabColor(ContextCompat.getColor(this, R.color.colorAccent));
        bottomBar.setItemsFromMenu(R.menu.menu_bottom_bar, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                onBottomMenuClick(menuItemId);
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            bottomBar.setElevation(15f);
        }
    }

    private void onBottomMenuClick(int menuItemId) {
        Fragment fragment = getFragmentFromPosition(menuItemId);
        attachFragment(fragment);
    }

    private Fragment getFragmentFromPosition(int position) {
        Fragment fragment = null;
        switch (position) {
            case R.id.bottom_menu_receivers:
                fragment = new ReceiverFragment();
                break;
            case R.id.bottom_menu_my_history:
                fragment = new MyHistoryFragment();
                break;
            case R.id.bottom_menu_my_profile:
                fragment = new MyProfileFragment();
                break;
        }
        return fragment;
    }

    public void attachFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    public void setMainToolbarTitle(String toolbarTitle) {
        if (textViewToolbarTitle != null) {
            textViewToolbarTitle.setText(toolbarTitle);
        }
    }

    @Override
    public void fragmentAttached(BottomBarTab bottomBarTab) {

    }
}
