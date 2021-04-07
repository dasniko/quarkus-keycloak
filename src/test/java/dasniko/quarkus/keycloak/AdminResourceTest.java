package dasniko.quarkus.keycloak;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;

/**
 * @author Niko Köbler, https://www.n-k.de, @dasniko
 */
@QuarkusTest
public class AdminResourceTest extends AccessTokenProvider {

    @Test
    public void testAdminAnonymous() {
        given().when().get("/admin").then().statusCode(401);
    }

    @Test
    public void testAdminSuccessfully() {
        given()
                .auth().oauth2(getAccessToken("admin", "admin"))
                .when().get("/admin")
                .then()
                .statusCode(200)
                .body(allOf(
                        containsString("preferred_username"),
                        containsString("admin")
                ));
    }

    @Test
    public void testAdminForbidden() {
        given()
                .auth().oauth2(getAccessToken("john", "john"))
                .when().get("/admin")
                .then()
                .statusCode(403);
    }

}
