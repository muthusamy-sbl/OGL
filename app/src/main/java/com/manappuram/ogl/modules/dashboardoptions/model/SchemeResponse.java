package com.manappuram.ogl.modules.dashboardoptions.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.manappuram.ogl.base.BaseResponse;

public class SchemeResponse extends BaseResponse {
    @SerializedName("schmName")
    @Expose
    public String schmName;

    @SerializedName("lendRate")
    @Expose
    public String lendRate;

    @SerializedName("duration")
    @Expose
    public String duration;

    @SerializedName("interest")
    @Expose
    public String interest;

    @SerializedName("loanValue")
    @Expose
    public String loanValue;

    @SerializedName("messg")
    @Expose
    public String messg;

    @SerializedName("maxLoan")
    @Expose
    public String maxLoan;

    @SerializedName("minLoan")
    @Expose
    public String minLoan;

    public String getSchmName() {
        return schmName;
    }

    public String getLendRate() {
        return lendRate;
    }

    public String getDuration() {
        return duration;
    }

    public String getInterest() {
        return interest;
    }

    public String getLoanValue() {
        return loanValue;
    }

    public String getMessg() {
        return messg;
    }

    public String getMaxLoan() {
        return maxLoan;
    }

    public String getMinLoan() {
        return minLoan;
    }
}
