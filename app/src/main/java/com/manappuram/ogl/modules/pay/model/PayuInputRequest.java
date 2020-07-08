package com.manappuram.ogl.modules.pay.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.manappuram.ogl.base.BaseSessionRequest;

public class PayuInputRequest extends BaseSessionRequest implements Parcelable {

    @SerializedName("cusid")
    @Expose
    private String cusid;

    @SerializedName("plno")
    @Expose
    private String plno;

    @SerializedName("amount")
    @Expose
    private String amount;

    private transient String paymentType;

    private transient String loanAccount;

    private transient String status;

    private transient String paymentMethod;


    public PayuInputRequest() {
    }

    protected PayuInputRequest(Parcel in) {
        cusid = in.readString();
        plno = in.readString();
        amount = in.readString();
        paymentType = in.readString();
        loanAccount = in.readString();
        status = in.readString();
        paymentMethod = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cusid);
        dest.writeString(plno);
        dest.writeString(amount);
        dest.writeString(paymentType);
        dest.writeString(loanAccount);
        dest.writeString(status);
        dest.writeString(paymentMethod);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PayuInputRequest> CREATOR = new Creator<PayuInputRequest>() {
        @Override
        public PayuInputRequest createFromParcel(Parcel in) {
            return new PayuInputRequest(in);
        }

        @Override
        public PayuInputRequest[] newArray(int size) {
            return new PayuInputRequest[size];
        }
    };

    public String getCusid() {
        return cusid;
    }

    public void setCusid(String cusid) {
        this.cusid = cusid;
    }

    public String getPlno() {
        return plno;
    }

    public void setPlno(String plno) {
        this.plno = plno;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getLoanAccount() {
        return loanAccount;
    }

    public void setLoanAccount(String loanAccount) {
        this.loanAccount = loanAccount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
