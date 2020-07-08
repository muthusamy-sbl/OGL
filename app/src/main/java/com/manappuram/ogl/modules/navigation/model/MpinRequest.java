package com.manappuram.ogl.modules.navigation.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MpinRequest {

    @SerializedName("MPIN")
    @Expose
    private String MPIN;

    @SerializedName("deviceid")
    @Expose
    private String deviceid;

    @SerializedName("langId")
    @Expose
    private String langId;

    public MpinRequest(String MPIN, String deviceid, String langId) {
        this.MPIN = MPIN;
        this.deviceid = deviceid;
        this.langId = langId;
    }

    public String getMPIN() {
        return MPIN;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public String getLangId() {
        return langId;
    }
}
