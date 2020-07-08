package com.manappuram.ogl.modules.dashboardoptions.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.manappuram.ogl.base.BaseResponse;

public class LoanFinalResponse extends BaseResponse {

    @SerializedName("loanNo")
    @Expose
    private String loanNo;

    @SerializedName("loanAmt")
    @Expose
    private String loanAmt;

    @SerializedName("schemeName")
    @Expose
    private String schemeName;

    @SerializedName("payamnetDate")
    @Expose
    private String payamnetDate;

    public String getLoanNo() {
        return loanNo;
    }

    public String getLoanAmt() {
        return loanAmt;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public String getPayamnetDate() {
        return payamnetDate;
    }

    @Override
    public String toString() {
        return "LoanFinalResponse{" +
                "loanNo='" + loanNo + '\'' +
                ", loanAmt='" + loanAmt + '\'' +
                ", schemeName='" + schemeName + '\'' +
                ", payamnetDate='" + payamnetDate + '\'' +
                '}';
    }
}
