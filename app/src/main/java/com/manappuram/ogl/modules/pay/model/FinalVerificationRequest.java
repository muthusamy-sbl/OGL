package com.manappuram.ogl.modules.pay.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.manappuram.ogl.base.BaseSessionRequest;

import java.io.Serializable;

public class FinalVerificationRequest extends BaseSessionRequest implements Serializable {

    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("txnid")
    @Expose
    private String txnid;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("productinfo")
    @Expose
    private String productinfo;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("udf1")
    @Expose
    private String udf1 = "";
    @SerializedName("udf2")
    @Expose
    private String udf2 = "";
    @SerializedName("udf3")
    @Expose
    private String udf3 = "";
    @SerializedName("udf4")
    @Expose
    private String udf4 = "";
    @SerializedName("udf5")
    @Expose
    private String udf5 = "";
    @SerializedName("udf6")
    @Expose
    private String udf6 = "";
    @SerializedName("udf7")
    @Expose
    private String udf7 = "";
    @SerializedName("udf8")
    @Expose
    private String udf8 = "";
    @SerializedName("udf9")
    @Expose
    private String udf9 = "";
    @SerializedName("udf10")
    @Expose
    private String udf10 = "";
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("salt")
    @Expose
    private String salt;

    public FinalVerificationRequest(PayuInputResponse payuInputResponse) {
        this.key = payuInputResponse.getKey();
        this.amount = payuInputResponse.getAmount();
        this.email = payuInputResponse.getEmail();
        this.firstname = payuInputResponse.getFirstname();
        this.productinfo = payuInputResponse.getProductinfo();
        this.txnid = payuInputResponse.getTxnid();
        this.salt = payuInputResponse.getSalt();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTxnid() {
        return txnid;
    }

    public void setTxnid(String txnid) {
        this.txnid = txnid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getProductinfo() {
        return productinfo;
    }

    public void setProductinfo(String productinfo) {
        this.productinfo = productinfo;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUdf1() {
        return udf1;
    }

    public void setUdf1(String udf1) {
        this.udf1 = udf1;
    }

    public String getUdf2() {
        return udf2;
    }

    public void setUdf2(String udf2) {
        this.udf2 = udf2;
    }

    public String getUdf3() {
        return udf3;
    }

    public void setUdf3(String udf3) {
        this.udf3 = udf3;
    }

    public String getUdf4() {
        return udf4;
    }

    public void setUdf4(String udf4) {
        this.udf4 = udf4;
    }

    public String getUdf5() {
        return udf5;
    }

    public void setUdf5(String udf5) {
        this.udf5 = udf5;
    }

    public String getUdf6() {
        return udf6;
    }

    public void setUdf6(String udf6) {
        this.udf6 = udf6;
    }

    public String getUdf7() {
        return udf7;
    }

    public void setUdf7(String udf7) {
        this.udf7 = udf7;
    }

    public String getUdf8() {
        return udf8;
    }

    public void setUdf8(String udf8) {
        this.udf8 = udf8;
    }

    public String getUdf9() {
        return udf9;
    }

    public void setUdf9(String udf9) {
        this.udf9 = udf9;
    }

    public String getUdf10() {
        return udf10;
    }

    public void setUdf10(String udf10) {
        this.udf10 = udf10;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
