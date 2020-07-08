package com.manappuram.ogl.modules.dashboardoptions.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.manappuram.ogl.base.BaseResponse;

public class ApplicationFormResponse extends BaseResponse {

    @SerializedName("cusName")
    @Expose
    private String cusName;

    @SerializedName("cusPhone")
    @Expose
    private String cusPhone;

    @SerializedName("cusEmail")
    @Expose
    private String cusEmail;

    @SerializedName("cusAddr")
    @Expose
    private String cusAddr;

    @SerializedName("cusPost")
    @Expose
    private String cusPost;

    @SerializedName("cusDist")
    @Expose
    private String cusDist;

    @SerializedName("cusState")
    @Expose
    private String cusState;

    @SerializedName("invID")
    @Expose
    private String invID;

    @SerializedName("pledgeNo")
    @Expose
    private String pledgeNo;

    @SerializedName("itmCount")
    @Expose
    private String itmCount;

    @SerializedName("totStdWeight")
    @Expose
    private String totStdWeight;

    @SerializedName("totGrossWeight")
    @Expose
    private String totGrossWeight;

    @SerializedName("totStoneWeight")
    @Expose
    private String totStoneWeight;

    public String getCusName() {
        return cusName;
    }

    public String getCusPhone() {
        return cusPhone;
    }

    public String getCusEmail() {
        return cusEmail;
    }

    public String getCusAddr() {
        return cusAddr;
    }

    public String getCusPost() {
        return cusPost;
    }

    public String getCusDist() {
        return cusDist;
    }

    public String getCusState() {
        return cusState;
    }

    public String getInvID() {
        return invID;
    }

    public String getPledgeNo() {
        return pledgeNo;
    }

    public String getItmCount() {
        return itmCount;
    }

    public String getTotStdWeight() {
        return totStdWeight;
    }

    public String getTotGrossWeight() {
        return totGrossWeight;
    }

    public String getTotStoneWeight() {
        return totStoneWeight;
    }

    @Override
    public String toString() {
        return "ApplicationFormResponse{" +
                "cusName='" + cusName + '\'' +
                ", cusPhone='" + cusPhone + '\'' +
                ", cusEmail='" + cusEmail + '\'' +
                ", cusAddr='" + cusAddr + '\'' +
                ", cusPost='" + cusPost + '\'' +
                ", cusDist='" + cusDist + '\'' +
                ", cusState='" + cusState + '\'' +
                ", invID='" + invID + '\'' +
                ", pledgeNo='" + pledgeNo + '\'' +
                ", itmCount='" + itmCount + '\'' +
                ", totStdWeight='" + totStdWeight + '\'' +
                ", totGrossWeight='" + totGrossWeight + '\'' +
                ", totStoneWeight='" + totStoneWeight + '\'' +
                '}';
    }
}
