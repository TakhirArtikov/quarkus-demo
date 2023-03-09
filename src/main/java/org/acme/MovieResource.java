package org.acme;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/movie")
public class MovieResource {

    @Inject
    MovieRepository repository;



    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovies(){
        List<Movie> movies=repository.listAll();
        return Response.ok(movies).build();

    }

    @GET
    @Path("/size")
    @Produces(MediaType.TEXT_PLAIN)
    public long countMovies(){
        return repository.count();

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createMovie(Movie newMovie){
        Movie movie= new Movie();
        movie.setName(newMovie.getName());
        repository.persist(movie);
        if (repository.isPersistent(movie)) {
            return Response.ok("Saved").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Path("{id}/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response updateMovie(
            @PathParam("id") Long id,
            @PathParam("name") String name){

            Movie movie=repository.findById(id);

            movie.setName(name);
            repository.persist(movie);
            if (repository.isPersistent(movie)) {
                return Response.ok("Updated").build();

            }
            return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteMovie(@PathParam("id") Long id){

        boolean removed=repository.deleteById(id);
        if (removed){
            return Response.ok("Deleted").build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();

    }


}

