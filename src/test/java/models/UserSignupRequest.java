package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserSignupRequest {

    private String email;
    private String password;


    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
