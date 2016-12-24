package com.pachouri.transportsolution.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

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


    public static boolean isUserAlreadyRegistered(String phoneNumber){
        UserModel userModel = new Select().from(UserModel.class).where(COLUMN_PHONE_NUMBER+" = ?",phoneNumber).executeSingle();

        if(userModel!=null)
            return true;
        else
            return false;
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
}
