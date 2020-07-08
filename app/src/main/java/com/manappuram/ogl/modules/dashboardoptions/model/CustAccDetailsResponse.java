package com.manappuram.ogl.modules.dashboardoptions.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.manappuram.ogl.base.BaseResponse;

public class CustAccDetailsResponse extends BaseResponse {

    @SerializedName("cusName")
    @Expose
    private String cusName;

    @SerializedName("cusAccnt")
    @Expose
    private String cusAccnt;

    @SerializedName("bankName")
    @Expose
    private String bankName;

    @SerializedName("branchName")
    @Expose
    private String branchName;

    @SerializedName("ifsc")
    @Expose
    private String ifsc;

    @SerializedName("cusPhone")
    @Expose
    private String cusPhone;

    @SerializedName("cusEmail")
    @Expose
    private String cusEmail;

    public String getCusName() {
        return cusName;
    }

    public String getCusAccnt() {
        return cusAccnt;
    }

    public String getBankName() {
        return bankName;
    }

    public String getBranchName() {
        return branchName;
    }

    public String getIfsc() {
        return ifsc;
    }

    public String getCusPhone() {
        return cusPhone;
    }

    public String getCusEmail() {
        return cusEmail;
    }
}
