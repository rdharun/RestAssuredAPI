package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserSignupResponse {

    private String userId;
    private String accessToken;


    @JsonProperty("userId")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonProperty("accessToken")
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
