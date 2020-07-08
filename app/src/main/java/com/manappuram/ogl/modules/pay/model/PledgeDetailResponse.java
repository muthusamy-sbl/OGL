package com.manappuram.ogl.modules.pay.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.manappuram.ogl.base.BaseResponse;

public class PledgeDetailResponse extends BaseResponse {

    @SerializedName("Processing_Charge")
    @Expose
    private String Processing_Charge;

    @SerializedName("Interest_Total")
    @Expose
    private String Interest_Total;

    @SerializedName("Interest_Rebate")
    @Expose
    private String Interest_Rebate;

    @SerializedName("Interest_Amount")
    @Expose
    private String Interest_Amount;

    @SerializedName("Full_Total")
    @Expose
    private String Full_Total;

    @SerializedName("Full_Rebate")
    @Expose
    private String Full_Rebate;

    @SerializedName("Principle_Amount")
    @Expose
    private String Principle_Amount;

    @SerializedName("Part_MinAmount")
    @Expose
    private String Part_MinAmount;

    @SerializedName("Part_MaxAmount")
    @Expose
    private String Part_MaxAmount;

    @SerializedName("paymentModeStatus")
    @Expose
    private String paymentModeStatus;

    @SerializedName("loanval")
    @Expose
    private String loanval;

    @SerializedName("minpay")
    @Expose
    private String minpay;

    @SerializedName("scheme")
    @Expose
    private String scheme;

    @SerializedName("duration")
    @Expose
    private String duration;

    @SerializedName("int_rate")
    @Expose
    private String int_rate;


    @SerializedName("Eligibility")
    @Expose
    private String Eligibility;

    @SerializedName("extend")
    @Expose
    private String extend;

    @SerializedName("maxPay")
    @Expose
    private String maxPay;

    public String getProcessing_Charge() {
        return Processing_Charge;
    }

    public String getInterest_Total() {
        return Interest_Total;
    }

    public String getInterest_Rebate() {
        return Interest_Rebate;
    }

    public String getInterest_Amount() {
        return Interest_Amount;
    }

    public String getFull_Total() {
        return Full_Total;
    }

    public String getFull_Rebate() {
        return Full_Rebate;
    }

    public String getPrinciple_Amount() {
        return Principle_Amount;
    }

    public String getPart_MinAmount() {
        return Part_MinAmount;
    }

    public String getPart_MaxAmount() {
        return Part_MaxAmount;
    }

    public String getPaymentModeStatus() {
        return paymentModeStatus;
    }

    public String getLoanval() {
        return loanval;
    }

    public String getMinpay() {
        return minpay;
    }

    public String getScheme() {
        return scheme;
    }

    public String getDuration() {
        return duration;
    }

    public String getInt_rate() {
        return int_rate;
    }

    public String getEligibility() {
        return Eligibility;
    }

    public String getExtend() {
        return extend;
    }

    public String getMaxPay() {
        return maxPay;
    }

    @Override
    public String toString() {
        return "PledgeDetailResponse{" +
                "Processing_Charge='" + Processing_Charge + '\'' +
                ", Interest_Total='" + Interest_Total + '\'' +
                ", Interest_Rebate='" + Interest_Rebate + '\'' +
                ", Interest_Amount='" + Interest_Amount + '\'' +
                ", Full_Total='" + Full_Total + '\'' +
                ", Full_Rebate='" + Full_Rebate + '\'' +
                ", Principle_Amount='" + Principle_Amount + '\'' +
                ", Part_MinAmount='" + Part_MinAmount + '\'' +
                ", Part_MaxAmount='" + Part_MaxAmount + '\'' +
                ", paymentModeStatus='" + paymentModeStatus + '\'' +
                ", loanval='" + loanval + '\'' +
                ", minpay='" + minpay + '\'' +
                ", scheme='" + scheme + '\'' +
                ", duration='" + duration + '\'' +
                ", int_rate='" + int_rate + '\'' +
                ", Eligibility='" + Eligibility + '\'' +
                ", extend='" + extend + '\'' +
                ", maxPay='" + maxPay + '\'' +
                '}';
    }
}
