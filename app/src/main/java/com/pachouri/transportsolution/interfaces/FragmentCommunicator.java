package com.pachouri.transportsolution.interfaces;

/**
 * Created by ankit on 12/25/16.
 */
public interface FragmentCommunicator {
    enum BottomBarTab {
        RECEIVER, HISTORY, PROFILE
    }

    void fragmentAttached(BottomBarTab bottomBarTab);
}
