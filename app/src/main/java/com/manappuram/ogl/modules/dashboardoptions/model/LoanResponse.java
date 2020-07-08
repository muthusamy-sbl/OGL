package com.manappuram.ogl.modules.dashboardoptions.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.manappuram.ogl.base.BaseResponse;

public class LoanResponse extends BaseResponse {

    @SerializedName("PledgeNo")
    @Expose
    private String PledgeNo;

    @SerializedName("TraDate")
    @Expose
    private String TraDate;

    @SerializedName("PledgeAmt")
    @Expose
    private String PledgeAmt;

    @SerializedName("maturityDate")
    @Expose
    private String maturityDate;

    @SerializedName("LastTransactionDate")
    @Expose
    private String LastTransactionDate;

    @SerializedName("closedate")
    @Expose
    private String closedate;

    @SerializedName("balanceAmt")
    @Expose
    private String balanceAmt;


    @SerializedName("schemeName")
    @Expose
    private String schemeName;

    @SerializedName("StatusID")
    @Expose
    private String StatusID;

    @SerializedName("InvDate")
    @Expose
    private String InvDate;

    public String getPledgeNo() {
        return PledgeNo;
    }

    public String getTraDate() {
        return TraDate;
    }

    public String getPledgeAmt() {
        return PledgeAmt;
    }

    public String getMaturityDate() {
        return maturityDate;
    }

    public String getLastTransactionDate() {
        return LastTransactionDate;
    }

    public String getClosedate() {
        return closedate;
    }

    public String getBalanceAmt() {
        return balanceAmt;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public String getStatusID() {
        return StatusID;
    }

    public String getInvDate() {
        return InvDate;
    }

    @Override
    public String toString() {
        return "LoanResponse{" +
                "PledgeNo='" + PledgeNo + '\'' +
                ", TraDate='" + TraDate + '\'' +
                ", PledgeAmt='" + PledgeAmt + '\'' +
                ", maturityDate='" + maturityDate + '\'' +
                ", LastTransactionDate='" + LastTransactionDate + '\'' +
                ", closedate='" + closedate + '\'' +
                ", balanceAmt='" + balanceAmt + '\'' +
                ", schemeName='" + schemeName + '\'' +
                ", StatusID='" + StatusID + '\'' +
                ", InvDate='" + InvDate + '\'' +
                '}';
    }
}
