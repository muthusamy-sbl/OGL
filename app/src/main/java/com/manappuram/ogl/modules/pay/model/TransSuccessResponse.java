package com.manappuram.ogl.modules.pay.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.manappuram.ogl.base.BaseResponse;

public class TransSuccessResponse extends BaseResponse implements Parcelable {

    @SerializedName("loanNo")
    @Expose
    private String loanNo;
    @SerializedName("loanAmt")
    @Expose
    private String loanAmt;
    @SerializedName("schemeName")
    @Expose
    private String schemEName;
    @SerializedName("payamentDate")
    @Expose
    private String payamnetDate;
    @SerializedName("suc_msg")
    @Expose
    private String suc_msg;

    protected TransSuccessResponse(Parcel in) {
        loanNo = in.readString();
        loanAmt = in.readString();
        schemEName = in.readString();
        payamnetDate = in.readString();
        suc_msg = in.readString();
//        status = in.readString();
//        result = in.readString();
    }

    public static final Creator<TransSuccessResponse> CREATOR = new Creator<TransSuccessResponse>() {
        @Override
        public TransSuccessResponse createFromParcel(Parcel in) {
            return new TransSuccessResponse(in);
        }

        @Override
        public TransSuccessResponse[] newArray(int size) {
            return new TransSuccessResponse[size];
        }
    };

//    public String getRequestid() {
//        return requestid;
//    }
//
//    public void setRequestid(String requestid) {
//        this.requestid = requestid;
//    }

    public String getSuc_msg() {
        return suc_msg;
    }

    public void setSuc_msg(String suc_msg) {
        this.suc_msg = suc_msg;
    }

    public String getLoanNo() {
        return loanNo;
    }

    public void setLoanNo(String loanNo) {
        this.loanNo = loanNo;
    }

    public String getLoanAmt() {
        return loanAmt;
    }

    public void setLoanAmt(String loanAmt) {
        this.loanAmt = loanAmt;
    }

    public String getSchemEName() {
        return schemEName;
    }

    public void setSchemEName(String schemEName) {
        this.schemEName = schemEName;
    }

    public String getPayamnetDate() {
        return payamnetDate;
    }

    public void setPayamnetDate(String payamnetDate) {
        this.payamnetDate = payamnetDate;
    }

//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getResult() {
//        return result;
//    }
//
//    public void setResult(String result) {
//        this.result = result;
//    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(loanNo);
        dest.writeString(loanAmt);
        dest.writeString(schemEName);
        dest.writeString(payamnetDate);
        dest.writeString(suc_msg);
//        dest.writeString(status);
//        dest.writeString(result);
    }
}