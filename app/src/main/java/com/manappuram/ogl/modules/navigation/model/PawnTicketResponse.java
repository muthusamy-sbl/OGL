package com.manappuram.ogl.modules.navigation.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.manappuram.ogl.base.BaseResponse;

public class PawnTicketResponse extends BaseResponse {

    @SerializedName("pawntktPaths")
    @Expose
    private String pawntktPaths;

    public String getPawntktPaths() {
        return pawntktPaths;
    }

    @Override
    public String toString() {
        return "PawnTicketResponse{" +
                "pawntktPaths='" + pawntktPaths + '\'' +
                '}';
    }
}
