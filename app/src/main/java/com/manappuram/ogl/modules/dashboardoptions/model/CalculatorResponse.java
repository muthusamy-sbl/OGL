package com.manappuram.ogl.modules.dashboardoptions.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.manappuram.ogl.base.BaseResponse;

public class CalculatorResponse extends BaseResponse {

    @SerializedName("minday")
    @Expose
    private String minday;

    @SerializedName("maxday")
    @Expose
    private String maxday;

    @SerializedName("intrt")
    @Expose
    private String intrt;

    @SerializedName("intmt")
    @Expose
    private String intmt;

    public String getMinday() {
        return minday;
    }

    public String getMaxday() {
        return maxday;
    }

    public String getIntrt() {
        return intrt;
    }

    public String getIntmt() {
        return intmt;
    }
}
