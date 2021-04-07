package dasniko.quarkus.keycloak;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * @author Niko Köbler, https://www.n-k.de, @dasniko
 */
public abstract class AccessTokenProvider {

    @ConfigProperty(name = "quarkus.oidc.auth-server-url")
    String authServerUrl;

    protected String getAccessToken(String username, String password) {
        return given().contentType("application/x-www-form-urlencoded")
                .formParams(Map.of(
                        "username", username,
                        "password", password,
                        "grant_type", "password",
                        "client_id", "quarkus-service",
                        "client_secret", "8a0f23ab-0d32-46a4-8eec-9c386b7453a5"
                ))
                .post(authServerUrl + "/protocol/openid-connect/token")
                .then().assertThat().statusCode(200)
                .extract().path("access_token");
    }
}
