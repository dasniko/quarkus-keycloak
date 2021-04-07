package dasniko.quarkus.keycloak;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;

/**
 * @author Niko Köbler, https://www.n-k.de, @dasniko
 */
@QuarkusTest
@QuarkusTestResource(KeycloakResource.class)
public class UsersResourceTest extends AccessTokenProvider {

    @Test
    public void testMeAnonymous() {
        given().when().get("/users/me").then().statusCode(401);
    }

    @Test
    public void testMeSuccessfully() {
        given()
                .auth().oauth2(getAccessToken("john", "john"))
                .when().get("/users/me")
                .then()
                .statusCode(200)
                .body(allOf(
                        containsString("username"),
                        containsString("john")
                ));
    }

    @Test
    public void testMeAdminSuccessfully() {
        given()
                .auth().oauth2(getAccessToken("admin", "admin"))
                .when().get("/users/me")
                .then()
                .statusCode(200)
                .body(allOf(
                        containsString("username"),
                        containsString("admin")
                ));
    }

    @Test
    public void testInfoEndpoint() {
        given()
                .auth().oauth2(getAccessToken("john", "john"))
                .when().get("/users/info")
                .then()
                .statusCode(200)
                .body(allOf(
                        containsString("email"),
                        containsString("john@localhost")
                ));
    }

}
