package mate.academy.service.impl;

import jakarta.persistence.EntityNotFoundException;
import mate.academy.dao.MovieSessionDao;
import mate.academy.lib.Inject;
import mate.academy.lib.Service;
import mate.academy.model.Movie;
import mate.academy.model.MovieSession;
import mate.academy.service.MovieSessionService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {
    @Inject
    private MovieSessionDao movieSessionDao;

    @Override
    public MovieSession add(MovieSession movieSession) {
        return movieSessionDao.add(movieSession);
    }

    @Override
    public MovieSession get(Long id) {
        Optional<MovieSession> actorOptional = movieSessionDao.get(id);
        return actorOptional.orElseThrow(() ->
                new EntityNotFoundException("Movie with id = " + id + " no exist."));
    }
    @Override
    public List<MovieSession> findAvailableSessions(Movie movie) {
        return movieSessionDao.findAvailableSessions(movie);
    }
}
