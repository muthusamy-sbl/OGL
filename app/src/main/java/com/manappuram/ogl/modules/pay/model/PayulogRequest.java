package com.manappuram.ogl.modules.pay.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.manappuram.ogl.base.BaseSessionRequest;

public class PayulogRequest extends BaseSessionRequest {

    @SerializedName("payment_gateway")
    @Expose
    private String payment_gateway;

    @SerializedName("mode_of_pay")
    @Expose
    private String mode_of_pay;

    @SerializedName("tran_id")
    @Expose
    private String tran_id;

    @SerializedName("wallet_id")
    @Expose
    private String wallet_id;

    @SerializedName("payment_status")
    @Expose
    private String payment_status;

    @SerializedName("location")
    @Expose
    private String location;

    @SerializedName("amount")
    @Expose
    private String amount;


    public PayulogRequest() {
    }
    public String getPayment_gateway() {
        return payment_gateway;
    }

    public void setPayment_gateway(String payment_gateway) {
        this.payment_gateway = payment_gateway;
    }

    public String getMode_of_pay() {
        return mode_of_pay;
    }

    public void setMode_of_pay(String mode_of_pay) {
        this.mode_of_pay = mode_of_pay;
    }

    public String getTran_id() {
        return tran_id;
    }

    public void setTran_id(String tran_id) {
        this.tran_id = tran_id;
    }

    public String getWallet_id() {
        return wallet_id;
    }

    public void setWallet_id(String wallet_id) {
        this.wallet_id = wallet_id;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "PayulogRequest{" +
                "payment_gateway='" + payment_gateway + '\'' +
                ", mode_of_pay='" + mode_of_pay + '\'' +
                ", tran_id='" + tran_id + '\'' +
                ", wallet_id='" + wallet_id + '\'' +
                ", payment_status='" + payment_status + '\'' +
                ", location='" + location + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
