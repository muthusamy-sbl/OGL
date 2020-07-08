package com.manappuram.ogl.modules.dashboardoptions.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.manappuram.ogl.base.BaseResponse;

public class ConfirmationResponse extends BaseResponse {

    @SerializedName("ifscCode")
    @Expose
    private String ifscCode;

    @SerializedName("beneficiaryBank")
    @Expose
    private String beneficiaryBank;

    @SerializedName("beneficiaryAccount")
    @Expose
    private String beneficiaryAccount;

    @SerializedName("accountName")
    @Expose
    private String accountName;

    @SerializedName("bankId")
    @Expose
    private String bankId;

    @SerializedName("custId")
    @Expose
    private String custId;

    @SerializedName("serialno")
    @Expose
    private String serialno;

    @SerializedName("neftStatus")
    @Expose
    private String neftStatus;

    @SerializedName("mobno")
    @Expose
    private String mobno;

    @SerializedName("emailId")
    @Expose
    private String emailId;

    public String getIfscCode() {
        return ifscCode;
    }

    public String getBeneficiaryBank() {
        return beneficiaryBank;
    }

    public String getBeneficiaryAccount() {
        return beneficiaryAccount;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getBankId() {
        return bankId;
    }

    public String getCustId() {
        return custId;
    }

    public String getSerialno() {
        return serialno;
    }

    public String getNeftStatus() {
        return neftStatus;
    }

    public String getMobno() {
        return mobno;
    }

    public String getEmailId() {
        return emailId;
    }

    @Override
    public String toString() {
        return "ConfirmationResponse{" +
                "ifscCode='" + ifscCode + '\'' +
                ", beneficiaryBank='" + beneficiaryBank + '\'' +
                ", beneficiaryAccount='" + beneficiaryAccount + '\'' +
                ", accountName='" + accountName + '\'' +
                ", bankId='" + bankId + '\'' +
                ", custId='" + custId + '\'' +
                ", serialno='" + serialno + '\'' +
                ", neftStatus='" + neftStatus + '\'' +
                ", mobno='" + mobno + '\'' +
                ", emailId='" + emailId + '\'' +
                '}';
    }
}
