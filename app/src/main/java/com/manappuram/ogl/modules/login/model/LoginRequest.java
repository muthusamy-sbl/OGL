package com.manappuram.ogl.modules.login.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.manappuram.ogl.util.Utility;

public class LoginRequest {


    @SerializedName("uid")
    @Expose
    private String uid;

    @SerializedName("pass")
    @Expose
    private String pass;
    @SerializedName("langId")
    @Expose
    private String langId;

    public LoginRequest() {
    }

    public LoginRequest(String uid, String pass, String langId) {
        this.uid = Utility.encodeString(uid);
        this.pass = Utility.encodeString(pass);
        this.langId = langId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getLangId() {
        return langId;
    }

    public void setLangId(String langId) {
        this.langId = langId;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "uid='" + uid + '\'' +
                ", pass='" + pass + '\'' +
                ", langId='" + langId + '\'' +
                '}';
    }
}
