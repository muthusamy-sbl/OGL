package com.manappuram.ogl.modules.navigation.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangePwdRequest {

    @SerializedName("uid")
    @Expose
    private String uid;

    @SerializedName("pass")
    @Expose
    private String pass;

    @SerializedName("newpass")
    @Expose
    private String newpass;

    @SerializedName("langId")
    @Expose
    private String langId;

    public ChangePwdRequest(String uid, String pass, String newpass, String langId) {
        this.uid = uid;
        this.pass = pass;
        this.newpass = newpass;
        this.langId = langId;
    }

    public String getUid() {
        return uid;
    }

    public String getPass() {
        return pass;
    }

    public String getNewpass() {
        return newpass;
    }

    public String getLangId() {
        return langId;
    }
}
