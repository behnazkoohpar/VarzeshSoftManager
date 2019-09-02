package com.eram.manager.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.eram.manager.data.pref.PreferencesHelper;
import com.google.gson.Gson;

import javax.inject.Inject;


/**
 * Created by behnaz on 10/29/2017.
 */

public class AppDataManager implements DataManager {

    private final Context mContext;
    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public AppDataManager(Context context,
                          PreferencesHelper preferencesHelper) {
        mContext = context;
        mPreferencesHelper = preferencesHelper;
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mPreferencesHelper.getCurrentUserLoggedInMode();
    }

    @Override
    public void setCurrentUserLoggedInMode(LoggedInMode mode) {
        mPreferencesHelper.setCurrentUserLoggedInMode(mode);
    }

    @Override
    public String getUserId() {
        return mPreferencesHelper.getUserId();
    }

    @Override
    public void setUserId(String userId) {
        mPreferencesHelper.setUserId(userId);
    }

    @Override
    public String getUsername() {
        return mPreferencesHelper.getUsername();
    }

    @Override
    public void setUsername(String username) {
        mPreferencesHelper.setUsername(username);
    }

    @Override
    public String getPassword() {
        return mPreferencesHelper.getPassword();
    }

    @Override
    public void setPassword(String password) {
        mPreferencesHelper.setPassword(password);
    }

    @Override
    public String getSecurityKey() {
        return mPreferencesHelper.getSecurityKey();
    }

    @Override
    public void setSecurityKey(String securityKey) {
        mPreferencesHelper.setSecurityKey(securityKey);
    }

    @Override
    public String getFirstName() {
        return mPreferencesHelper.getFirstName();
    }

    @Override
    public void setFirstName(String firstName) {
        mPreferencesHelper.setFirstName(firstName);
    }

    @Override
    public String getLastName() {
        return mPreferencesHelper.getLastName();
    }

    @Override
    public void setLastName(String lastName) {
        mPreferencesHelper.setLastName(lastName);
    }

    @Override
    public String getProfilePicture() {
        return mPreferencesHelper.getProfilePicture();
    }

    @Override
    public void setProfilePicture(String profilePicture) {
        mPreferencesHelper.setProfilePicture(profilePicture);
    }

    @Override
    public String getToken() {
        return mPreferencesHelper.getToken();
    }

    @Override
    public void setToken(String token) {
        mPreferencesHelper.setToken(token);
    }

    @Override
    public String getTokenDate() {
        return mPreferencesHelper.getTokenDate();
    }

    @Override
    public void setTokenDate(String tokenDate) {
        mPreferencesHelper.setTokenDate(tokenDate);
    }

    @Override
    public String getInvoiceNumber() {
        return mPreferencesHelper.getInvoiceNumber();
    }

    @Override
    public void setInvoiceNumber(String invoiceNumber) {
        mPreferencesHelper.setInvoiceNumber(invoiceNumber);
    }

    @Override
    public String getTripleDes() {
        return mPreferencesHelper.getTripleDes();
    }

    @Override
    public void setTripleDes(String tripleDes) {
        mPreferencesHelper.setTripleDes(tripleDes);
    }

    @Override
    public String getNationalCode() {
        return mPreferencesHelper.getNationalCode();
    }

    @Override
    public void setNationalCode(String nationalCode) {
        mPreferencesHelper.setNationalCode(nationalCode);
    }

    @Override
    public String getLatitude() {
        return mPreferencesHelper.getLatitude();
    }

    @Override
    public void setLatitude(String latitude) {
        mPreferencesHelper.setLatitude(latitude);
    }

    @Override
    public String getLongitude() {
        return mPreferencesHelper.getLongitude();
    }

    @Override
    public void setLongitude(String longitude) {
        mPreferencesHelper.setLongitude(longitude);
    }

    @Override
    public String getNationalCodeClub() {
        return mPreferencesHelper.getNationalCodeClub();
    }

    @Override
    public void setNationalCodeClub(String nationalCodeClub) {
        mPreferencesHelper.setNationalCodeClub(nationalCodeClub);
    }

    @Override
    public void setAddress(String s) {
        mPreferencesHelper.setAddress(s);
    }

    @Override
    public String getAddress() {
        return mPreferencesHelper.getAddress();
    }

    @Override
    public void setEmail(String s) {
        mPreferencesHelper.setEmail(s);
    }

    @Override
    public String getEmail() {
        return mPreferencesHelper.getEmail();
    }

    @Override
    public void setSupporterName(String s) {
        mPreferencesHelper.setSupporterName(s);
    }

    @Override
    public String getSupporterName() {
        return mPreferencesHelper.getSupporterName();
    }

    @Override
    public void setSupporterPhone(String s) {
        mPreferencesHelper.setSupporterPhone(s);
    }

    @Override
    public String getSupporterPhone() {
        return mPreferencesHelper.getSupporterPhone();
    }

    @Override
    public void setServerAddress(String s) {
        mPreferencesHelper.setServerAddress(s);
    }

    @Override
    public String getServerAddress() {
        return mPreferencesHelper.getServerAddress();
    }

    @Override
    public void setPhoneNumber(String s) {
        mPreferencesHelper.setPhoneNumber(s);
    }

    @Override
    public String getPhoneNumber() {
        return mPreferencesHelper.getPhoneNumber();
    }

    @Override
    public void setName(String s) {
        mPreferencesHelper.setName(s);
    }

    @Override
    public String getName() {
        return mPreferencesHelper.getName();
    }

    @Override
    public void setID(String s) {
        mPreferencesHelper.setID(s);
    }

    @Override
    public String getID() {
        return mPreferencesHelper.getID();
    }

    @Override
    public void updateUserInfo(
            LoggedInMode loggedInMode,
            String userId,
            String vUserName,
            String vPassword,
            String vFirstName,
            String vLastName) {
        setCurrentUserLoggedInMode(loggedInMode);
        setUserId(userId);
        setUsername(vUserName);
        setPassword(vPassword);
        setFirstName(vFirstName);
        setLastName(vLastName);
    }

    @Override
    public void setUserAsLoggedOut() {
        updateUserInfo(
                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT,
                "",
                "",
                "",
                "",
                "");
    }

}
