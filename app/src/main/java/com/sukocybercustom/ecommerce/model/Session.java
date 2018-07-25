package com.sukocybercustom.ecommerce.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by suko on 2/12/18.
 */

public class Session {
    @SerializedName("session_id")
    @Expose
    private String sessionid;
    @SerializedName("token")
    @Expose
    private String token;

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
