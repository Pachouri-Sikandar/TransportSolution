package com.pachouri.transportsolution.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.io.Serializable;

/**
 * Created by ankit on 12/25/16.
 */

@Table(name = HistoryModel.TABLE_NAME)
public class HistoryModel extends Model implements Serializable {
    public static final String TABLE_NAME = "history_list";

    private static final String COLUMN_IMAGE_URL = "image_url";
    private static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_LAST_NAME = "last_name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PHONE_NUMBER = "phone_number";
    public static final String COLUMN_ITEM = "item";
    private static final String COLUMN_DELIVERED_ADDRESS = "delivered_address";
    private static final String COLUMN_DELIVERED_TIME = "delivered_time";
    private static final String COLUMN_DELIVERY_CHARGES = "delivery_charges";

    @Column(name = COLUMN_IMAGE_URL)
    private String imageUrl;

    @Column(name = COLUMN_PHONE_NUMBER)
    private String mobileNumber;

    @Column(name = COLUMN_FIRST_NAME)
    private String firstName;

    @Column(name = COLUMN_LAST_NAME)
    private String lastName;

    @Column(name = COLUMN_EMAIL)
    private String email;

    @Column(name = COLUMN_ITEM)
    private String item;

    @Column(name = COLUMN_DELIVERED_ADDRESS)
    private String deliveredAddress;

    @Column(name = COLUMN_DELIVERED_TIME)
    private String deliveredTime;

    @Column(name = COLUMN_DELIVERY_CHARGES)
    private String deliveryCharges;

    public static String getTableName() {
        return TABLE_NAME;
    }

    public static String getColumnImageUrl() {
        return COLUMN_IMAGE_URL;
    }

    public static String getColumnFirstName() {
        return COLUMN_FIRST_NAME;
    }

    public static String getColumnLastName() {
        return COLUMN_LAST_NAME;
    }

    public static String getColumnEmail() {
        return COLUMN_EMAIL;
    }

    public static String getColumnPhoneNumber() {
        return COLUMN_PHONE_NUMBER;
    }

    public static String getColumnDeliveryCharges() {
        return COLUMN_DELIVERY_CHARGES;
    }

    public static String getColumnItem() {
        return COLUMN_ITEM;
    }

    public static String getColumnDeliveredAddress() {
        return COLUMN_DELIVERED_ADDRESS;
    }

    public static String getColumnDeliveredTime() {
        return COLUMN_DELIVERED_TIME;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getDeliveredAddress() {
        return deliveredAddress;
    }

    public void setDeliveredAddress(String deliveredAddress) {
        this.deliveredAddress = deliveredAddress;
    }

    public String getDeliveredTime() {
        return deliveredTime;
    }

    public void setDeliveredTime(String deliveredTime) {
        this.deliveredTime = deliveredTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDeliveryCharges() {
        return deliveryCharges;
    }

    public void setDeliveryCharges(String deliveryCharges) {
        this.deliveryCharges = deliveryCharges;
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
}