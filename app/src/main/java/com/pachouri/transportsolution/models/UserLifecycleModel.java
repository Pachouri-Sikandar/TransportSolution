package com.pachouri.transportsolution.models;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by tt-riyaz on 25/12/16.
 */

public class UserLifecycleModel {

    private static final String STATE_KEY="state";
    private static final String USER_PREFERENCES_CONTEXT = "com.informedtech.app.userPreferences";

    public enum UserStatus{
        NotLoggedIn,
        LoggedIn,
        PersonalProfileNotComplete,
        VechileRegistrationNotComplete,
        AdharRegistrationNotComplete,
    }

    public static UserStatus getUserCurrentState(Context context) {
        SharedPreferences preferences = getPreferences(context);
        String stateName = preferences.getString(STATE_KEY, "");
        if (stateName.length() == 0) {
            return UserStatus.NotLoggedIn;
        } else {
            UserStatus state;
            try {
                state = UserStatus.valueOf(stateName);
            } catch (IllegalArgumentException e) {
                state = UserStatus.NotLoggedIn;
            }
            return state;
        }
    }

    public static void setUserCurrentState(Context context, UserStatus state) {
        SharedPreferences preferences = getPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(STATE_KEY, state.name());
        editor.commit();
    }


    public static void clear(Context context) {
        SharedPreferences preferences = getPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
    }

    private static SharedPreferences getPreferences(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(
                USER_PREFERENCES_CONTEXT, Context.MODE_PRIVATE);
        return prefs;
    }
}

