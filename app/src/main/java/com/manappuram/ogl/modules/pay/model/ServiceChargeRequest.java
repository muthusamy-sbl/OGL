package com.manappuram.ogl.modules.pay.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceChargeRequest {

    @SerializedName("paymethod")
    private String paymethod;

    @SerializedName("amount")
    private String amount;

    @SerializedName("plno")
    private String plno;

    @SerializedName("paymenttype")
    private String paymenttype;

    public void setPaymethod(String paymethod) {
        this.paymethod = paymethod;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setPlno(String plno) {
        this.plno = plno;
    }

    public void setPaymenttype(String paymenttype) {
        this.paymenttype = paymenttype;
    }

    public String getPaymethod() {
        return paymethod;
    }

    public String getAmount() {
        return amount;
    }

    public String getPlno() {
        return plno;
    }

    public String getPaymenttype() {
        return paymenttype;
    }
}
