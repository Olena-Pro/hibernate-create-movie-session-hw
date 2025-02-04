package mate.academy.dao.impl;

import mate.academy.dao.MovieSessionDao;
import mate.academy.exception.DataProcessingException;
import mate.academy.lib.Dao;
import mate.academy.model.Movie;
import mate.academy.model.MovieSession;
import mate.academy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Dao
public class MovieSessionDaoImpl implements MovieSessionDao {
    @Override
    public MovieSession add(MovieSession movieSession) {

        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(movieSession);
            transaction.commit();
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert session " + movieSession, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<MovieSession> get(Long id) {

            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<MovieSession> getSessionQuery = session.createQuery("from MovieSession s " +
                    "left join fetch s.cinemaHalls " +
                    "where s.id = :id", MovieSession.class);
            getSessionQuery.setParameter("id",id);
            return Optional.ofNullable(getSessionQuery.getSingleResult());
        } catch (Exception e) {
            throw new DataProcessingException("Can't get a session by id: " + id, e);
        }
    }

    @Override
    public List<MovieSession> findAvailableSessions(Movie movie) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<MovieSession> getSessionQuery = session.createQuery("from MovieSession s " +
                    "left join fetch s.cinemaHalls " +
                    "where s.movie = :movie", MovieSession.class);
            getSessionQuery.setParameter("movie",movie);
            return (getSessionQuery.getResultList());
        } catch (Exception e) {
            throw new DataProcessingException("Can't get a sessions", e);
        }
    }
}
