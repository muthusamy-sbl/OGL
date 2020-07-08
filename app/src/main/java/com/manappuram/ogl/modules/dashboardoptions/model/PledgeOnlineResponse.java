package com.manappuram.ogl.modules.dashboardoptions.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.manappuram.ogl.base.BaseResponse;

public class PledgeOnlineResponse extends BaseResponse {

    @SerializedName("Principle_Amount")
    @Expose
    private String Principle_Amount;

    @SerializedName("Interest_Amount")
    @Expose
    private String Interest_Amount;

    @SerializedName("Processing_Charge")
    @Expose
    private String Processing_Charge;

    @SerializedName("Full_Rebate")
    @Expose
    private String Full_Rebate;

    @SerializedName("Full_Total")
    @Expose
    private String Full_Total;

    public String getPrinciple_Amount() {
        return Principle_Amount;
    }

    public String getInterest_Amount() {
        return Interest_Amount;
    }

    public String getProcessing_Charge() {
        return Processing_Charge;
    }

    public String getFull_Rebate() {
        return Full_Rebate;
    }

    public String getFull_Total() {
        return Full_Total;
    }
}
