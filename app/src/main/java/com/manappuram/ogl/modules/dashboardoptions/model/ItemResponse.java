package com.manappuram.ogl.modules.dashboardoptions.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.manappuram.ogl.base.BaseResponse;

public class ItemResponse extends BaseResponse {

    @SerializedName("brID")
    @Expose
    private String brID;
    @SerializedName("frmID")
    @Expose
    private String frmID;
    @SerializedName("cusID")
    @Expose
    private String cusID;
    @SerializedName("itmName")
    @Expose
    private String itmName;
    @SerializedName("itmCount")
    @Expose
    private String itmCount;
    @SerializedName("ddnm")
    @Expose
    private String ddnm;
    @SerializedName("actWt")
    @Expose
    private String actWt;

    @SerializedName("stoneWt")
    @Expose
    private String stoneWt;

    @SerializedName("netWt")
    @Expose
    private String netWt;

    @SerializedName("newActWt")
    @Expose
    private String newActWt;

    @SerializedName("itmID")
    @Expose
    private String itmID;
    @SerializedName("remark")
    @Expose
    private String remark;

    public String getBrID() {
        return brID;
    }

    public String getFrmID() {
        return frmID;
    }

    public String getCusID() {
        return cusID;
    }

    public String getItmName() {
        return itmName;
    }

    public String getItmCount() {
        return itmCount;
    }

    public String getDdnm() {
        return ddnm;
    }

    public String getActWt() {
        return actWt;
    }

    public String getStoneWt() {
        return stoneWt;
    }

    public String getNetWt() {
        return netWt;
    }

    public String getNewActWt() {
        return newActWt;
    }

    public String getItmID() {
        return itmID;
    }

    public String getRemark() {
        return remark;
    }
}
