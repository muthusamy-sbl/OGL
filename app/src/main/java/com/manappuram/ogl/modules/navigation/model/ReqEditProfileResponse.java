package com.manappuram.ogl.modules.navigation.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.manappuram.ogl.base.BaseResponse;

public class ReqEditProfileResponse extends BaseResponse {

    @SerializedName("customerid")
    @Expose
    private String customerid;

    @SerializedName("mobileno")
    @Expose
    private String mobileno;

    @SerializedName("emailid")
    @Expose
    private String emailid;

    @SerializedName("change")
    @Expose
    private String change;

    @SerializedName("changemode")
    @Expose
    private String changemode;

    public String getCustomerid() {
        return customerid;
    }

    public String getMobileno() {
        return mobileno;
    }

    public String getEmailid() {
        return emailid;
    }

    public String getChange() {
        return change;
    }

    public String getChangemode() {
        return changemode;
    }

    @Override
    public String toString() {
        return "ReqEditProfileResponse{" +
                "customerid='" + customerid + '\'' +
                ", mobileno='" + mobileno + '\'' +
                ", emailid='" + emailid + '\'' +
                ", change='" + change + '\'' +
                ", changemode='" + changemode + '\'' +
                '}';
    }
}
