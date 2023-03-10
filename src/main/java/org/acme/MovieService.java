package org.acme;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class MovieService {

    @Inject
    MovieRepository repository;

    public List<Movie> getMovies() {
        return repository.listAll();
    }

    @Transactional
    public boolean delete(Long id) {

        boolean deleted = repository.deleteById(id);
        if (deleted)
            return true;
        return false;
    }

    @Transactional
    public boolean update(Long id, String name) {

        Optional<Movie> movie = Optional.ofNullable(repository.findById(id));

        if (movie.isPresent()) {
            movie.get().setName(name);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean create(Movie movie) {

        Movie newMovie = new Movie();
        newMovie.setName(movie.getName());
        repository.persist(newMovie);
        if (repository.isPersistent(newMovie))
            return true;
        return false;
    }
}
