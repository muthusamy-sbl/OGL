package com.manappuram.ogl.modules.navigation.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AccDetailsResponse {

    @SerializedName("PledgeNo")
    @Expose
    private String PledgeNo;

    @SerializedName("PledgeAmt")
    @Expose
    private String PledgeAmt;

    @SerializedName("requestid")
    @Expose
    private String requestid;

    public String getPledgeNo() {
        return PledgeNo;
    }

    public String getPledgeAmt() {
        return PledgeAmt;
    }

    public String getRequestid() {
        return requestid;
    }

    @Override
    public String toString() {
        return "AccDetailsResponse{" +
                "PledgeNo='" + PledgeNo + '\'' +
                ", PledgeAmt='" + PledgeAmt + '\'' +
                ", requestid='" + requestid + '\'' +
                '}';
    }
}
