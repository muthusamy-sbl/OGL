package com.manappuram.ogl.modules.register.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.manappuram.ogl.base.BaseResponse;

public class CustomerCheckResponse extends BaseResponse {

    @SerializedName("customerid")
    @Expose
    private String customerid;

    @SerializedName("emailid")
    @Expose
    private String emailid;

    @SerializedName("phoneno")
    @Expose
    private String phoneno;

    public String getCustomerid() {
        return customerid;
    }

    public String getEmailid() {
        return emailid;
    }

    public String getPhoneno() {
        return phoneno;
    }
}
