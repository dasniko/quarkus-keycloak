package dasniko.quarkus.keycloak;

import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Map;

/**
 * @author Niko Köbler, https://www.n-k.de, @dasniko
 */
@Path("admin")
@Produces(MediaType.APPLICATION_JSON)
public class AdminResource {

    @Inject
    JsonWebToken jwt;

    @GET
    @RolesAllowed("admin")
    public Map<String, String> admin() {
        return Map.of(
                "subject", jwt.getSubject(),
                "preferred_username", jwt.getClaim("preferred_username")
        );
    }
}
