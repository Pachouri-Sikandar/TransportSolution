package com.pachouri.transportsolution.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

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

    @Column(name = COLUMN_PHONE_NUMBER)
    private String mobileNumber;

    @Column(name = COLUMN_FIRST_NAME)
    private String firstName;

    @Column(name = COLUMN_LAST_NAME)
    private String lastName;

    @Column(name = COLUMN_EMAIL)
    private String email;
}
