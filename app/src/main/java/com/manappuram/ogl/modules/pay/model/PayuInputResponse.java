package com.manappuram.ogl.modules.pay.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.manappuram.ogl.base.BaseResponse;
import com.manappuram.ogl.util.Constants;
public class PayuInputResponse extends BaseResponse {

    @Override
    public String toString() {
        return "PayuInputResponse{" +
                "hash='" + hash + '\'' +
                ", txnid='" + txnid + '\'' +
                ", key='" + key + '\'' +
                ", salt='" + salt + '\'' +
                ", amount='" + amount + '\'' +
                ", firstname='" + firstname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", productinfo='" + productinfo + '\'' +
                ", action='" + action + '\'' +
                '}';
    }

    @SerializedName("hash")
    @Expose
    private String hash;
    @SerializedName("txnid")
    @Expose
    private String txnid;
    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("salt")
    @Expose
    private String salt;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("productinfo")
    @Expose
    private String productinfo;
    @SerializedName("action")
    @Expose
    private String action;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getTxnid() {
        return txnid;
    }

    public void setTxnid(String txnid) {
        this.txnid = txnid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProductinfo() {
        return productinfo;
    }

    public void setProductinfo(String productinfo) {
        this.productinfo = productinfo;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }



//    @Override
//    public String toString() {
//        String postData = getPostValue("hash", hash) + getPostValue("txnid", txnid) +
//                getPostValue("key", key) + getPostValue("salt", salt) +
//                getPostValue("amount", amount) + getPostValue("firstname", firstname) +
//                getPostValue("email", email) + getPostValue("phone", phone) +
//                getPostValue("surl", Constants.Urls.PAY_SUCCESS) + getPostValue("furl", Constants.Urls.PAY_FAILED) +
//                getLastPostValue("productinfo", productinfo);
//        return postData;
//    }

    public String getUrlWithPGUPI(String method, String amount1){

        System.out.println("model:   "  +amount1);
        String postData = getPostValue("hash", hash) + getPostValue("txnid", txnid) +
                getPostValue("key", key) + getPostValue("salt", salt) +
                getPostValue("amount", amount1) + getPostValue("firstname", firstname) +
                getPostValue("email", email) + getPostValue("phone", phone) +
                getPostValue("surl", Constants.Urls.PAY_SUCCESS) + getPostValue("furl", Constants.Urls.PAY_FAILED) +
                getPostValue("pg", "UPI") +
                getPostValue("bankcode", "INTENT") +
                getPostValue("txn_s2s_flow", "2") +
                //getPostValue("enforce_paymethod", getEnforceType(paymentMethod)) +
                getLastPostValue("productinfo", productinfo);
        return postData;
    }


    public String getUrlWithPG(String paymentMethod, String amount1) {

        System.out.println("model:   "  +amount1);
        String postData = getPostValue("hash", hash) + getPostValue("txnid", txnid) +
                getPostValue("key", key) + getPostValue("salt", salt) +
                getPostValue("amount", amount1) + getPostValue("firstname", firstname) +
                getPostValue("email", email) + getPostValue("phone", phone) +
                getPostValue("surl", Constants.Urls.PAY_SUCCESS) + getPostValue("furl", Constants.Urls.PAY_FAILED) +
                getPostValue("enforce_paymethod", getEnforceType(paymentMethod)) +
                getLastPostValue("productinfo", productinfo);
        return postData;
    }


    public String getPostValue(String name, String value) {
        return name + "=" + value + "&";
    }

    public String getLastPostValue(String name, String value) {
        return name + "=" + value;
    }

    public String getPgType(String payType) {
        if (payType.equals(Constants.PAYU_DEBIT_CARD)) {
            return "DC";
        } else if (payType.equals(Constants.PAYU_NETBANKING)) {
            return "NB";
        } else if (payType.equals(Constants.PAYU_UPI)) {
            return "UPI";
        }
        return "";
    }

    public String getEnforceType(String payType) {
        if (payType.equals(Constants.PAYU_DEBIT_CARD)) {
            return "VISA|MAST|SMAE|MAES";
        } else if (payType.equals(Constants.PAYU_NETBANKING)) {
            return "netbanking";
        } else if (payType.equals(Constants.PAYU_UPI)) {
            return "UPI";
        } else {
            return "RUPAY";
        }
    }
}