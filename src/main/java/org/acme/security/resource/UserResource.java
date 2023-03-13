package org.acme.security.resource;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

@Path("/api/users")
public class UserResource {

    @GET
    @RolesAllowed("user")
    public String userResource(@Context SecurityContext securityContext) {
        return securityContext.getUserPrincipal().getName();
    }
}
