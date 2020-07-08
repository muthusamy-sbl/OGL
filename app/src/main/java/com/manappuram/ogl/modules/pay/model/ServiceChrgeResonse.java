package com.manappuram.ogl.modules.pay.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.manappuram.ogl.base.BaseResponse;

public class ServiceChrgeResonse extends BaseResponse {

    @SerializedName("ServiceCharge")
    @Expose
    private String ServiceCharge;

     @SerializedName("TotalAmount")
    @Expose
    private String TotalAmount;

    public String getServiceCharge() {
        return ServiceCharge;
    }

    public String getTotalAmount() {
        return TotalAmount;
    }

    @Override
    public String toString() {
        return "ServiceChrgeResonse{" +
                "ServiceCharge='" + ServiceCharge + '\'' +
                ", TotalAmount='" + TotalAmount + '\'' +
                '}';
    }
}
