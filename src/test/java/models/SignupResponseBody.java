package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignupResponseBody {
    private int statusCode;
    private Data data;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Data {
        private Session session;
        private User user;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Session {
        @JsonProperty("access_token")
        private String accessToken;
        @JsonProperty("refresh_token")
        private String refreshToken;
        @JsonProperty("expires_at")
        private int expiresAt;
        @JsonProperty("token_type")
        private String tokenType;
        @JsonProperty("expires_in")
        private int expiresIn;
        private User user;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class User {
        private String aud;
        private String role;
        @JsonProperty("email_confirmed_at")
        private String emailConfirmedAt;
        @JsonProperty("user_metadata")
        private UserMetadata userMetadata;
        private List<IdentitiesItem> identities;
        @JsonProperty("last_sign_in_at")
        private String lastSignInAt;
        @JsonProperty("updated_at")
        private String updatedAt;
        private String phone;
        @JsonProperty("created_at")
        private String createdAt;
        private String id;
        private String email;
        @JsonProperty("app_metadata")
        private AppMetadata appMetadata;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class UserMetadata {

    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class IdentitiesItem {
        @JsonProperty("identity_data")
        private IdentityData identityData;
        @JsonProperty("last_sign_in_at")
        private String lastSignInAt;
        @JsonProperty("updated_at")
        private String updatedAt;
        @JsonProperty("identity_id")
        private String identityId;
        @JsonProperty("user_id")
        private String userId;
        private String provider;
        @JsonProperty("created_at")
        private String createdAt;
        private String id;
        private String email;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class IdentityData {
        private String sub;
        @JsonProperty("email_verified")
        private boolean emailVerified;
        @JsonProperty("phone_verified")
        private boolean phoneVerified;
        private String email;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AppMetadata {
        private String provider;
        private List<String> providers;
    }

    public void assertSuccessfullySignupResponse(String email) {
        assertEquals(this.getStatusCode(), 201, "Invalid status code");
        assertEquals(this.getData().getUser().getEmail(), email, "Invalid email id");
        assertNotNull(this.getData().getSession().accessToken, "Access token should not be null");
    }
}
