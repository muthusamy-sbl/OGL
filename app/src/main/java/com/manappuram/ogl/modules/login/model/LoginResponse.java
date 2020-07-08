package com.manappuram.ogl.modules.login.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.manappuram.ogl.base.BaseResponse;

public class LoginResponse {

    @SerializedName("customerid")
    @Expose
    private String customerid;

    @SerializedName("customername")
    @Expose
    private String customername;

    @SerializedName("custlang")
    @Expose
    private String custlang;

    @SerializedName("uniquesessid")
    @Expose
    private String uniquesessid;

    @SerializedName("requestid")
    @Expose
    private String requestid;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("result")
    @Expose
    private String result;

    public String getCustomerid() {
        return customerid;
    }

    public String getCustomername() {
        return customername;
    }

    public String getCustlang() {
        return custlang;
    }

    public String getUniquesessid() {
        return uniquesessid;
    }

    public String getRequestid() {
        return requestid;
    }

    public String getStatus() {
        return status;
    }

    public String getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "customerid='" + customerid + '\'' +
                ", customername='" + customername + '\'' +
                ", custlang='" + custlang + '\'' +
                ", uniquesessid='" + uniquesessid + '\'' +
                ", requestid='" + requestid + '\'' +
                ", status='" + status + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
