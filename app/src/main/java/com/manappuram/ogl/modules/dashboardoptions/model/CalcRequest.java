package com.manappuram.ogl.modules.dashboardoptions.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CalcRequest {

    @SerializedName("pledgeValue")
    private String pledgeValue;

    @SerializedName("intrstPayDate")
    private String intrstPayDate;

    @SerializedName("schemeName")
    private String schemeName;

    @SerializedName("branchID")
    private String branchID;

    public CalcRequest(String pledgeValue, String intrstPayDate, String schemeName, String branchID) {
        this.pledgeValue = pledgeValue;
        this.intrstPayDate = intrstPayDate;
        this.schemeName = schemeName;
        this.branchID = branchID;
    }

    public String getPledgeValue() {
        return pledgeValue;
    }

    public String getIntrstPayDate() {
        return intrstPayDate;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public String getBranchID() {
        return branchID;
    }

    @Override
    public String toString() {
        return "CalcRequest{" +
                "pledgeValue='" + pledgeValue + '\'' +
                ", intrstPayDate='" + intrstPayDate + '\'' +
                ", schemeName='" + schemeName + '\'' +
                ", branchID='" + branchID + '\'' +
                '}';
    }
}
