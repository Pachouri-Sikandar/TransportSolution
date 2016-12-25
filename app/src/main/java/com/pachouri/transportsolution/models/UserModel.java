package com.pachouri.transportsolution.models;

import android.content.Context;
import android.content.SharedPreferences;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.google.gson.Gson;
import com.pachouri.transportsolution.BaseActivity;
import com.pachouri.transportsolution.BaseApplication;
import com.pachouri.transportsolution.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;


/**
 * Created by tt-riyaz on 25/12/16.
 */

@Table(name = UserModel.TABLE_NAME)
public class UserModel extends Model implements Serializable {
    public static final String TABLE_NAME="user_model";

    private static final String COLUMN_FIRST_NAME="first_name";
    public static final String COLUMN_LAST_NAME="last_name";
    public static final String COLUMN_EMAIL="email";
    public static final String COLUMN_PHONE_NUMBER="phone_number";
    public static final String COLUMN_VR="";

    @Column(name = COLUMN_PHONE_NUMBER)
    private String mobileNumber;

    @Column(name = COLUMN_FIRST_NAME)
    private String firstName;

    @Column(name = COLUMN_LAST_NAME)
    private String lastName;

    @Column(name = COLUMN_EMAIL)
    private String email;

    @Column(name = COLUMN_VR)
    private String veichle;

    @Column(name = "image_url")
    private String ImageUrl;

    @Column(name = "aadhar")
    private String aadharNumber;

    public static boolean isUserAlreadyRegistered(String phoneNumber){
        UserModel userModel = new Select().from(UserModel.class).where(COLUMN_PHONE_NUMBER+" = ?",phoneNumber).executeSingle();

        if(userModel!=null)
            return true;
        else
            return false;
    }

    public static UserModel getUserModelFromMobile(String mobileNumber){
        return new Select()
                .from(UserModel.class)
                .where(COLUMN_PHONE_NUMBER+" = ?",mobileNumber)
                .executeSingle();
    }

    private static UserModel instance;

    private static String USER_PREFERENCES_STORE = "user_preferences_store";

    public static SharedPreferences getUserSharedPreferences(Context context) {
        return context.getSharedPreferences(USER_PREFERENCES_STORE, Context.MODE_PRIVATE);
    }


    public static UserModel getInstance(Context context) {
        UserModel userObject = UserModel.getInstance(context, null);
        return userObject;
    }

    public static UserModel setUpInstance(Context context, UserModel userData) {
        instance = null;
        return UserModel.getInstance(context, userData);
    }

    private static UserModel getInstance(Context context, UserModel userData) {
        long userId = 0;
        if (instance == null) {
            SharedPreferences preferences = getUserSharedPreferences(context);
            String UserModel = preferences.getString(Constants.PREF_KEY_USER, "");
            try {
                JSONObject object = new JSONObject(UserModel);
                userId = object.optLong("userId", 0);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (userId == 0 && userData == null) {
                return new UserModel();
            }

            instance = new UserModel();
            if (userData == null) {
                instance.pullUserData(context);
            } else {
                instance.setUpUserData(context, userData);
            }
        }
        return instance;
    }

    private void pullUserData(Context context) {
        Gson gson = null;
        try {
            gson = ((BaseApplication) context).getGson();
        } catch (Exception e) {
            gson = ((BaseApplication) context.getApplicationContext()).getGson();
        }
        SharedPreferences preferences = getUserSharedPreferences(context);
        instance = gson.fromJson(preferences.getString(Constants.PREF_KEY_USER, ""), UserModel.class);
    }

    private void setUpUserData(Context context, UserModel userData) {
        Gson gson = null;
        try {
            gson = ((BaseApplication) context).getGson();
        } catch (Exception e) {
            gson = ((BaseApplication) context.getApplicationContext()).getGson();
        }
        String jsonString = gson.toJson(userData);
        SharedPreferences preferences = getUserSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constants.PREF_KEY_USER, jsonString);
        editor.apply();
        pullUserData(context);
    }


    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVeichle() {
        return veichle;
    }

    public void setVeichle(String veichle) {
        this.veichle = veichle;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }
}
