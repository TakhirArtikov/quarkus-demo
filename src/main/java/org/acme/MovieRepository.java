package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.acme.Movie;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MovieRepository implements PanacheRepository<Movie> {
}
