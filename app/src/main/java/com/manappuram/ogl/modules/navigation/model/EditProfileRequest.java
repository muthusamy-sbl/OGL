package com.manappuram.ogl.modules.navigation.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditProfileRequest {

    @SerializedName("cusid")
    @Expose
    private String cusid;

    @SerializedName("uniquesessionid")
    @Expose
    private String uniquesessionid;

    @SerializedName("mobileno")
    @Expose
    private String mobileno;

    @SerializedName("emailid")
    @Expose
    private String emailid;

    @SerializedName("changemode")
    @Expose
    private String changemode;

    @SerializedName("requestid")
    @Expose
    private String requestid;

    @SerializedName("langId")
    @Expose
    private String langId;



    public String getCusid() {
        return cusid;
    }

    public void setCusid(String cusid) {
        this.cusid = cusid;
    }

    public String getUniquesessionid() {
        return uniquesessionid;
    }

    public void setUniquesessionid(String uniquesessionid) {
        this.uniquesessionid = uniquesessionid;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getChangemode() {
        return changemode;
    }

    public void setChangemode(String changemode) {
        this.changemode = changemode;
    }

    public String getRequestid() {
        return requestid;
    }

    public void setRequestid(String requestid) {
        this.requestid = requestid;
    }

    public String getLangId() {
        return langId;
    }

    public void setLangId(String langId) {
        this.langId = langId;
    }
}
