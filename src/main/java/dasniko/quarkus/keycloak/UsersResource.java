package dasniko.quarkus.keycloak;

import io.quarkus.oidc.UserInfo;
import io.quarkus.security.identity.SecurityIdentity;
import org.jboss.resteasy.reactive.NoCache;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Niko Köbler, https://www.n-k.de, @dasniko
 */
@Path("users")
@Produces(MediaType.APPLICATION_JSON)
public class UsersResource {

    @Inject
    SecurityIdentity securityIdentity;

    @Inject
    UserInfo userInfo;

    @GET
    @Path("me")
    @RolesAllowed("user")
    @NoCache
    public Map<String, String> me() {
        return Map.of("username", securityIdentity.getPrincipal().getName());
    }

    @GET
    @Path("info")
    @RolesAllowed("user")
    @NoCache
    public Map<String, String> info() {
        return userInfo.getAllProperties().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().toString()));
    }
}
