package org.acme;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/movie")
public class MovieResource {

    @Inject
    MovieService service;

    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovies() {
        List<Movie> movies = service.getMovies();
        return Response.ok(movies).build();

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createMovie(Movie newMovie) {
        boolean saved = service.create(newMovie);
        if (saved) {
            return Response.ok("Saved").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Path("{id}/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMovie(
            @PathParam("id") Long id,
            @PathParam("name") String name) {
        boolean updated = service.update(id, name);
        if (updated) {
            return Response.ok("Updated").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMovie(@PathParam("id") Long id) {
        boolean removed = service.delete(id);
        if (removed) {
            return Response.ok("Deleted").build();

        }

        return Response.status(Response.Status.NOT_FOUND).build();

    }


}

