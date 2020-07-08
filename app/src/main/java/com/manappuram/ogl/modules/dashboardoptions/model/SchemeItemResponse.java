package com.manappuram.ogl.modules.dashboardoptions.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.manappuram.ogl.base.BaseResponse;

public class SchemeItemResponse extends BaseResponse {

    @SerializedName("branchId")
    @Expose
    public String branchId;

    @SerializedName("schemeName")
    @Expose
    public String schemeName;

    @SerializedName("eligibleLoanAmt")
    @Expose
    public String eligibleLoanAmt;

    @SerializedName("stampDuty")
    @Expose
    public String stampDuty;

    public String getBranchId() {
        return branchId;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public String getEligibleLoanAmt() {
        return eligibleLoanAmt;
    }

    public String getStampDuty() {
        return stampDuty;
    }

    @Override
    public String toString() {
        return "SchemeItemResponse{" +
                "branchId='" + branchId + '\'' +
                ", schemeName='" + schemeName + '\'' +
                ", eligibleLoanAmt='" + eligibleLoanAmt + '\'' +
                ", stampDuty='" + stampDuty + '\'' +
                '}';
    }
}
