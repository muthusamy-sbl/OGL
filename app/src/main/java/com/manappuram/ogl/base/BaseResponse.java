package com.manappuram.ogl.base;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class BaseResponse {
    @SerializedName("requestid")
    @Expose
    private String requestid;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("result")
    @Expose
    private String result;
    public BaseResponse(String i, String s) {
        status = i;
        result = s;
    }
    public BaseResponse() {

    }

    public String getRequestid() {
        return requestid;
    }

    public void setRequestid(String requestid) {
        this.requestid = requestid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "requestid='" + requestid + '\'' +
                ", status='" + status + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
