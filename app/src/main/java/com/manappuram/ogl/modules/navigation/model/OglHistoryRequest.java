package com.manappuram.ogl.modules.navigation.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OglHistoryRequest {

    @SerializedName("plno")
    @Expose
    private String plno;

    @SerializedName("newplno")
    @Expose
    private String newplno;

    @SerializedName("fromdate")
    @Expose
    private String fromdate;

    @SerializedName("todate")
    @Expose
    private String todate;

    @SerializedName("txnid")
    @Expose
    private String txnid;


    public OglHistoryRequest(String plno, String newplno, String fromdate, String todate, String txnid) {
        this.plno = plno;
        this.newplno = newplno;
        this.fromdate = fromdate;
        this.todate = todate;
        this.txnid = txnid;
    }

    public String getPlno() {
        return plno;
    }

    public String getNewplno() {
        return newplno;
    }

    public String getFromdate() {
        return fromdate;
    }

    public String getTodate() {
        return todate;
    }

    public String getTxnid() {
        return txnid;
    }
}
