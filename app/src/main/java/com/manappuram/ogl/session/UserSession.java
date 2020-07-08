package com.manappuram.ogl.session;

import android.os.Parcel;
import android.os.Parcelable;

import com.manappuram.ogl.modules.login.model.LoginRequest;
import com.manappuram.ogl.modules.login.model.LoginResponse;
import com.manappuram.ogl.util.Utility;

///**
// * Session Manager , Hold User Data
// * <p>
// * diff between loan and settlement amt
// */
public class UserSession implements Parcelable {
    public static UserSession ourInstance = new UserSession();
    private String customerName;
    public String proof;
    private String customerId;
    private String uniqueSessionId;
    public String requestId;
    public boolean isMPINRegister;
    public boolean isOGLCustomer;
    public String langId;


    private UserSession() {

    }

    protected UserSession(Parcel in) {
        customerName = in.readString();
        customerId = in.readString();
        uniqueSessionId = in.readString();
        requestId = in.readString();
        proof = in.readString();
        langId = in.readString();
        isMPINRegister = in.readByte() != 0;
        isOGLCustomer = in.readByte() != 0;
    }

    public static final Parcelable.Creator<UserSession> CREATOR = new Creator<UserSession>() {
        @Override
        public UserSession createFromParcel(Parcel in) {
            return new UserSession(in);
        }

        @Override
        public UserSession[] newArray(int size) {
            return new UserSession[size];
        }
    };

    public static UserSession getInstance() {
        return ourInstance;
    }

    public void setUserData(LoginResponse loginResponse) {
        customerName = loginResponse.getCustomername();
        customerId = loginResponse.getCustomerid();
        uniqueSessionId = loginResponse.getUniquesessid();
        requestId = loginResponse.getRequestid();
        langId = loginResponse.getCustlang();
//        isMPINRegister = (loginResponse.getMpinstatus() != null && loginResponse.getMpinstatus().equalsIgnoreCase("Y"));
    }

    public void setRequestId(String requestId) {
        if (!Utility.isNull(requestId))
            this.requestId = requestId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getUniqueSessionId() {
        return uniqueSessionId;
    }

    public String getRequestId() {
        return requestId;
    }

    public boolean isMPINRegister() {
        return isMPINRegister;
    }

    public boolean isOGLCustomer() {
        return isOGLCustomer;
    }

    public void setOGLCustomer(boolean OGLCustomer) {
        isOGLCustomer = OGLCustomer;
    }

    public boolean isUserLoggedIn() {
        return customerId != null;
    }

    public String getProof() {
        return proof;
    }

    public void setProof(String proof) {
        this.proof = proof;
    }

    public String getLangId() {
        return langId;
    }

    public LoginRequest getUserData() {
        LoginRequest request = new LoginRequest();
        request.setLangId("EN");
        request.setPass("12345");
        return request;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(customerName);
        dest.writeString(customerId);
        dest.writeString(proof);
        dest.writeString(uniqueSessionId);
        dest.writeString(requestId);
        dest.writeString(langId);
        dest.writeByte((byte) (isMPINRegister ? 1 : 0));
        dest.writeByte((byte) (isOGLCustomer ? 1 : 0));
    }
}
