package com.manappuram.ogl.modules.dashboardoptions.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.manappuram.ogl.base.BaseResponse;

import java.util.ArrayList;

public class PledgeResponse extends BaseResponse {

    @SerializedName("invID")
    @Expose
    public String invID;
    @SerializedName("pledgeNo")
    @Expose
    public String pledgeNo;

    @SerializedName("itmCount")
    @Expose
    public String itmCount;

    @SerializedName("totStdWeight")
    @Expose
    public String totStdWeight;


    @SerializedName("totGrossWeight")
    @Expose
    public String totGrossWeight;

    @SerializedName("totStoneWeight")
    @Expose
    public String totStoneWeight;

    @SerializedName("pledgeVal")
    @Expose
    public String pledgeVal;

    @SerializedName("settlementVal")
    @Expose
    public String settlementVal;

    @SerializedName("eligibleloanamt")
    @Expose
    public String eligibleloanamt;


    @SerializedName("eligiblebalance")
    @Expose
    public String eligiblebalance;


    @SerializedName("InvDate")
    @Expose
    public String InvDate;

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

    public String getPledgeVal() {
        return pledgeVal;
    }

    public String getSettlementVal() {
        return settlementVal;
    }

    public String getEligibleloanamt() {
        return eligibleloanamt;
    }

    public String getEligiblebalance() {
        return eligiblebalance;
    }

    public String getInvDate() {
        return InvDate;
    }
}
