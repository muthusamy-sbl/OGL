package com.manappuram.ogl.modules.pay.model;

import com.google.gson.annotations.SerializedName;
import com.manappuram.ogl.base.BaseResponse;

public class FinalCheckResponse extends BaseResponse {
    @SerializedName("Hash")
    private String hash;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
