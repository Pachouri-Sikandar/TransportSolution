package com.pachouri.transportsolution.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.io.Serializable;

/**
 * Created by ankit on 12/25/16.
 */

@Table(name = ReceiverDetailsModel.TABLE_NAME)
public class ReceiverDetailsModel extends Model implements Serializable {
    public static final String TABLE_NAME = "receivers_list";

    private static final String COLUMN_IMAGE_URL = "image_url";
    private static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_LAST_NAME = "last_name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PHONE_NUMBER = "phone_number";
    public static final String COLUMN_VR = "";
    private static final String COLUMN_PLACE_FROM = "place_from";
    private static final String COLUMN_PLACE_TO = "place_to";
    private static final String COLUMN_LEAVE_TIME = "leave_time";
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

    @Column(name = COLUMN_VR)
    private String veichle;

    @Column(name = COLUMN_PLACE_FROM)
    private String placeFrom;

    @Column(name = COLUMN_PLACE_TO)
    private String placeTo;

    @Column(name = COLUMN_LEAVE_TIME)
    private String leavingTime;

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

    public static String getColumnVr() {
        return COLUMN_VR;
    }

    public static String getColumnPlaceFrom() {
        return COLUMN_PLACE_FROM;
    }

    public static String getColumnPlaceTo() {
        return COLUMN_PLACE_TO;
    }

    public static String getColumnLeaveTime() {
        return COLUMN_LEAVE_TIME;
    }

    public static String getColumnDeliveryCharges() {
        return COLUMN_DELIVERY_CHARGES;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPlaceFrom() {
        return placeFrom;
    }

    public void setPlaceFrom(String placeFrom) {
        this.placeFrom = placeFrom;
    }

    public String getPlaceTo() {
        return placeTo;
    }

    public void setPlaceTo(String placeTo) {
        this.placeTo = placeTo;
    }

    public String getLeavingTime() {
        return leavingTime;
    }

    public void setLeavingTime(String leavingTime) {
        this.leavingTime = leavingTime;
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

    public String getVeichle() {
        return veichle;
    }

    public void setVeichle(String veichle) {
        this.veichle = veichle;
    }
}