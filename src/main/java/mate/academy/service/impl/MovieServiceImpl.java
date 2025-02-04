package mate.academy.service.impl;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;
import mate.academy.dao.MovieDao;
import mate.academy.lib.Inject;
import mate.academy.lib.Service;
import mate.academy.model.CinemaHall;
import mate.academy.model.Movie;
import mate.academy.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
    @Inject
    private MovieDao movieDao;

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public Movie get(Long id) {

        Optional<Movie> actorOptional = movieDao.get(id);
        return actorOptional.orElseThrow(() ->
                new EntityNotFoundException("Movie with id = " + id + " no exist."));
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }
}
