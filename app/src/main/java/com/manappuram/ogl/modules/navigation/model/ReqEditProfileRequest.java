package com.manappuram.ogl.modules.navigation.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReqEditProfileRequest {

    @SerializedName("oldmobno")
    @Expose
    private String oldmobno;

    @SerializedName("newmobno")
    @Expose
    private String newmobno;

    @SerializedName("oldemailid")
    @Expose
    private String oldemailid;

    @SerializedName("newemailid")
    @Expose
    private String newemailid;

    @SerializedName("sendOTP")
    @Expose
    private String sendOTP;

    public ReqEditProfileRequest(String oldmobno, String newmobno, String oldemailid, String newemailid, String sendOTP) {
        this.oldmobno = oldmobno;
        this.newmobno = newmobno;
        this.oldemailid = oldemailid;
        this.newemailid = newemailid;
        this.sendOTP = sendOTP;
    }

    public String getOldmobno() {
        return oldmobno;
    }

    public String getNewmobno() {
        return newmobno;
    }

    public String getOldemailid() {
        return oldemailid;
    }

    public String getNewemailid() {
        return newemailid;
    }

    public String getSendOTP() {
        return sendOTP;
    }


    @Override
    public String toString() {
        return "ReqEditProfileRequest{" +
                "oldmobno='" + oldmobno + '\'' +
                ", newmobno='" + newmobno + '\'' +
                ", oldemailid='" + oldemailid + '\'' +
                ", newemailid='" + newemailid + '\'' +
                ", sendOTP='" + sendOTP + '\'' +
                '}';
    }
}
