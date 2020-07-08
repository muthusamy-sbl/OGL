package com.manappuram.ogl.base;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.manappuram.ogl.session.UserSession;

public class BaseSessionRequest {

    @SerializedName("uniquesessionid")
    @Expose
    private String uniquesessionid;


    public BaseSessionRequest() {
        this.uniquesessionid = UserSession.getInstance().getUniqueSessionId();
    }

    public String getUniquesessionid() {
        return uniquesessionid;
    }

    public void setUniquesessionid(String uniquesessionid) {
        this.uniquesessionid = uniquesessionid;
    }

}
