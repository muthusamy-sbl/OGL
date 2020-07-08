package com.manappuram.ogl.modules.login.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VersionResponse {


    @SerializedName("Version")
    @Expose
    private String Version;

    @SerializedName("Message")
    @Expose
    private String Message;

    public String getVersion() {
        return Version;
    }

    public String getMessage() {
        return Message;
    }

    @Override
    public String toString() {
        return "VersionResponse{" +
                "Version='" + Version + '\'' +
                ", Message='" + Message + '\'' +
                '}';
    }
}
