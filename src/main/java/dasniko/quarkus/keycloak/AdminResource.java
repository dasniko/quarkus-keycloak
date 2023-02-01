package dasniko.quarkus.keycloak;

import io.quarkus.security.Authenticated;
import io.quarkus.security.identity.SecurityIdentity;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.inject.Inject;
import javax.security.auth.AuthPermission;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.security.Permission;
import java.util.List;
import java.util.Map;

/**
 * @author Niko Köbler, https://www.n-k.de, @dasniko
 */
@Authenticated
@Path("admin")
@Produces(MediaType.APPLICATION_JSON)
public class AdminResource {

    @Inject
    JsonWebToken jwt;
    @Inject
    SecurityIdentity securityIdentity;

    @GET
    // @RolesAllowed("admin")
    public Map<String, String> admin() {
        return Map.of(
                "subject", jwt.getSubject(),
                "preferred_username", jwt.getClaim("preferred_username")
        );
    }

    @GET
    @Path("permissions")
    public Uni<List<Permission>> permissions() {
        return securityIdentity.checkPermission(new AuthPermission("Admin Resource")).onItem()
                .transform(granted -> {
                    if (granted) {
                        return securityIdentity.getAttribute("permissions");
                    }
                    throw new ForbiddenException();
                });
    }
}
