package com.manappuram.ogl.modules.navigation.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccDetailsRequest {

    @SerializedName("cus_id")
    @Expose
    private String cus_id;

    @SerializedName("uniquesessionid")
    @Expose
    private String uniquesessionid;

    @SerializedName("requestid")
    @Expose
    private String requestid;

    @SerializedName("langId")
    @Expose
    private String langId;

    public String getCus_id() {
        return cus_id;
    }

    public void setCus_id(String cus_id) {
        this.cus_id = cus_id;
    }

    public String getUniquesessionid() {
        return uniquesessionid;
    }

    public void setUniquesessionid(String uniquesessionid) {
        this.uniquesessionid = uniquesessionid;
    }

    public String getRequestid() {
        return requestid;
    }

    public void setRequestid(String requestid) {
        this.requestid = requestid;
    }

    public String getLangId() {
        return langId;
    }

    public void setLangId(String langId) {
        this.langId = langId;
    }

    @Override
    public String toString() {
        return "AccDetailsRequest{" +
                "cus_id='" + cus_id + '\'' +
                ", uniquesessionid='" + uniquesessionid + '\'' +
                ", requestid='" + requestid + '\'' +
                ", langId='" + langId + '\'' +
                '}';
    }
}
