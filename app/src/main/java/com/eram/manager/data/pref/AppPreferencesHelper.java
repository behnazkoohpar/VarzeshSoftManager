package com.eram.manager.data.pref;

import android.content.Context;
import android.content.SharedPreferences;

import com.eram.manager.data.DataManager;
import com.eram.manager.di.PreferenceInfo;

import javax.inject.Inject;

/**
 * Created by behnaz on 10/29/2017.
 */

public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE";
    private static final String PREF_KEY_USERNAME = "PREF_KEY_USERNAME";
    private static final String PREF_KEY_PASSWORD = "PREF_KEY_PASSWORD";
    private static final String PREF_KEY_SECURITY_KEY = "PREF_KEY_SECURITY_KEY";
    private static final String PREF_KEY_FIRST_NAME = "PREF_KEY_FIRST_NAME";
    private static final String PREF_KEY_LAST_NAME = "PREF_KEY_LAST_NAME";
    private static final String PREF_KEY_PROFILE_PICTURE = "PREF_KEY_PROFILE_PICTURE";
    private static final String PREF_KEY_USER_ID = "PREF_KEY_USER_ID";
    private static final String PREF_KEY_TOKEN = "PREF_KEY_TOKEN";
    private static final String PREF_KEY_TOKEN_DATE = "PREF_KEY_TOKEN_DATE";
    private static final String PREF_KEY_INVOICE_NUMBER = "PREF_KEY_INVOICE_NUMBER";
    private static final String PREF_KEY_TRIPLE_DES = "PREF_KEY_TRIPLE_DES";
    private static final String PREF_KEY_NATIONAL_CODE = "PREF_KEY_NATIONAL_CODE";
    private static final String PREF_KEY_LATITUDE = "PREF_KEY_LATITUDE";
    private static final String PREF_KEY_LONGITUDE = "PREF_KEY_LONGITUDE";
    private static final String PREF_KEY_NATIONAL_CODE_CLUB = "PREF_KEY_NATIONAL_CODE_CLUB";
    private static final String PREF_KEY_ADDRESS = "PREF_KEY_ADDRESS";
    private static final String PREF_KEY_EMAIL = "PREF_KEY_EMAIL";
    private static final String PREF_KEY_SUPPORTER_NAME = "PREF_KEY_SUPPORTER_NAME";
    private static final String PREF_KEY_SUPPORTER_PHONE = "PREF_KEY_SUPPORTER_PHONE";
    private static final String PREF_KEY_SERVER_ADDRESS = "PREF_KEY_SERVER_ADDRESS";
    private static final String PREF_KEY_PHONE_NUMBER = "PREF_KEY_PHONE_NUMBER";
    private static final String PREF_KEY_NAME = "PREF_KEY_NAME";
    private static final String PREF_KEY_ID = "PREF_KEY_ID";

    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(Context context,
                                @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public String getUserId() {
        return mPrefs.getString(PREF_KEY_USER_ID, "");
    }

    @Override
    public void setUserId(String userId) {
        mPrefs.edit().putString(PREF_KEY_USER_ID, userId).apply();
    }

    @Override
    public String getUsername() {
        return mPrefs.getString(PREF_KEY_USERNAME, "");
    }

    @Override
    public void setUsername(String username) {
        mPrefs.edit().putString(PREF_KEY_USERNAME, username).apply();
    }

    @Override
    public String getPassword() {
        return mPrefs.getString(PREF_KEY_PASSWORD, "");
    }

    @Override
    public void setPassword(String password) {
        mPrefs.edit().putString(PREF_KEY_PASSWORD, password).apply();
    }

    @Override
    public String getFirstName() {
        return mPrefs.getString(PREF_KEY_FIRST_NAME, "");
    }

    @Override
    public void setFirstName(String firstName) {
        mPrefs.edit().putString(PREF_KEY_FIRST_NAME, firstName).apply();
    }

    @Override
    public String getLastName() {
        return mPrefs.getString(PREF_KEY_LAST_NAME, "");
    }

    @Override
    public void setLastName(String lastName) {
        mPrefs.edit().putString(PREF_KEY_LAST_NAME, lastName).apply();
    }

    @Override
    public String getProfilePicture() {
        return mPrefs.getString(PREF_KEY_PROFILE_PICTURE, "");
    }

    @Override
    public void setProfilePicture(String profilePicture) {
        mPrefs.edit().putString(PREF_KEY_PROFILE_PICTURE, profilePicture).apply();
    }

    @Override
    public String getToken() {
        return mPrefs.getString(PREF_KEY_TOKEN, "");
    }

    @Override
    public void setToken(String token) {
        mPrefs.edit().putString(PREF_KEY_TOKEN, token).apply();
    }

    @Override
    public String getTokenDate() {
        return mPrefs.getString(PREF_KEY_TOKEN_DATE, "");
    }

    @Override
    public void setTokenDate(String tokenDate) {
        mPrefs.edit().putString(PREF_KEY_TOKEN_DATE, tokenDate).apply();
    }


    @Override
    public String getInvoiceNumber() {
        return mPrefs.getString(PREF_KEY_INVOICE_NUMBER, "");
    }

    @Override
    public void setInvoiceNumber(String invoiceNumber) {
        mPrefs.edit().putString(PREF_KEY_INVOICE_NUMBER, invoiceNumber).apply();
    }

    @Override
    public String getTripleDes() {
        return mPrefs.getString(PREF_KEY_TRIPLE_DES, "");
    }

    @Override
    public void setTripleDes(String tripleDes) {
        mPrefs.edit().putString(PREF_KEY_TRIPLE_DES, tripleDes).apply();
    }

    @Override
    public String getNationalCode() {
        return mPrefs.getString(PREF_KEY_NATIONAL_CODE, "");
    }

    @Override
    public void setNationalCode(String nationalCode) {
        mPrefs.edit().putString(PREF_KEY_NATIONAL_CODE, nationalCode).apply();
    }


    @Override
    public String getSecurityKey() {
        return mPrefs.getString(PREF_KEY_SECURITY_KEY, "");
    }

    @Override
    public void setSecurityKey(String securityKey) {
        mPrefs.edit().putString(PREF_KEY_SECURITY_KEY, securityKey).apply();
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mPrefs.getInt(PREF_KEY_USER_LOGGED_IN_MODE,
                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType());
    }

    @Override
    public void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode) {
        mPrefs.edit().putInt(PREF_KEY_USER_LOGGED_IN_MODE, mode.getType()).apply();
    }

    @Override
    public String getLatitude() {
        return mPrefs.getString(PREF_KEY_LATITUDE, "");
    }

    @Override
    public void setLatitude(String latitude) {
        mPrefs.edit().putString(PREF_KEY_LATITUDE, latitude).apply();
    }

    @Override
    public String getLongitude() {
        return mPrefs.getString(PREF_KEY_LONGITUDE, "");
    }

    @Override
    public void setLongitude(String longitude) {
        mPrefs.edit().putString(PREF_KEY_LONGITUDE, longitude).apply();
    }


    @Override
    public String getNationalCodeClub() {
        return mPrefs.getString(PREF_KEY_NATIONAL_CODE_CLUB, "");
    }

    @Override
    public void setNationalCodeClub(String nationalCodeClub) {
        mPrefs.edit().putString(PREF_KEY_NATIONAL_CODE_CLUB, nationalCodeClub).apply();
    }

    @Override
    public void setAddress(String s) {
        mPrefs.edit().putString(PREF_KEY_ADDRESS, s).apply();
    }

    @Override
    public String getAddress() {
        return mPrefs.getString(PREF_KEY_ADDRESS, "");
    }

    @Override
    public void setEmail(String s) {
        mPrefs.edit().putString(PREF_KEY_EMAIL, s).apply();
    }

    @Override
    public String getEmail() {
        return mPrefs.getString(PREF_KEY_EMAIL, "");
    }

    @Override
    public void setSupporterName(String s) {
        mPrefs.edit().putString(PREF_KEY_SUPPORTER_NAME, s).apply();
    }

    @Override
    public String getSupporterName() {
        return mPrefs.getString(PREF_KEY_SUPPORTER_NAME, "");
    }

    @Override
    public void setSupporterPhone(String s) {
        mPrefs.edit().putString(PREF_KEY_SUPPORTER_PHONE, s).apply();
    }

    @Override
    public String getSupporterPhone() {
        return mPrefs.getString(PREF_KEY_SUPPORTER_PHONE, "");
    }

    @Override
    public void setServerAddress(String s) {
        mPrefs.edit().putString(PREF_KEY_SERVER_ADDRESS, s).apply();
    }

    @Override
    public String getServerAddress() {
        return mPrefs.getString(PREF_KEY_SERVER_ADDRESS, "");
    }

    @Override
    public void setPhoneNumber(String s) {
        mPrefs.edit().putString(PREF_KEY_PHONE_NUMBER, s).apply();
    }

    @Override
    public String getPhoneNumber() {
        return mPrefs.getString(PREF_KEY_PHONE_NUMBER, "");
    }

    @Override
    public void setName(String s) {
        mPrefs.edit().putString(PREF_KEY_NAME, s).apply();
    }

    @Override
    public String getName() {
        return mPrefs.getString(PREF_KEY_NAME, "");
    }

    @Override
    public void setID(String s) {
        mPrefs.edit().putString(PREF_KEY_ID, s).apply();
    }

    @Override
    public String getID() {
        return mPrefs.getString(PREF_KEY_ID, "");
    }
}
