package com.manappuram.ogl.modules.dashboardoptions.model;

import com.google.gson.annotations.SerializedName;

public class ConfirmationRequest {

    @SerializedName("paymethod")
    private String paymethod;

    @SerializedName("amount")
    private String amount;

    @SerializedName("plno")
    private String plno;

    @SerializedName("newpledgeamnt")
    private String newpledgeamnt;

    public void setPaymethod(String paymethod) {
        this.paymethod = paymethod;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setPlno(String plno) {
        this.plno = plno;
    }

    public void setNewpledgeamnt(String newpledgeamnt) {
        this.newpledgeamnt = newpledgeamnt;
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

    public String getNewpledgeamnt() {
        return newpledgeamnt;
    }

    @Override
    public String toString() {
        return "ConfirmationRequest{" +
                "paymethod='" + paymethod + '\'' +
                ", amount='" + amount + '\'' +
                ", plno='" + plno + '\'' +
                ", newpledgeamnt='" + newpledgeamnt + '\'' +
                '}';
    }
}
