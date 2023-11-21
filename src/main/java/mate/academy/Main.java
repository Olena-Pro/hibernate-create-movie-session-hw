package mate.academy;

import mate.academy.lib.Injector;
import mate.academy.model.CinemaHall;
import mate.academy.model.Movie;
import mate.academy.model.MovieSession;
import mate.academy.service.CinemaHallService;
import mate.academy.service.MovieService;
import mate.academy.service.MovieSessionService;

import java.time.LocalDateTime;
import java.time.Month;

public class Main {
    private static final Injector injector = Injector.getInstance("mate.academy");

    public static void main(String[] args) {
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);

        Movie fastAndFurious = new Movie("Fast and Furious");
        fastAndFurious.setDescription("An action film about street racing, heists, and spies.");
        movieService.add(fastAndFurious);
        System.out.println(movieService.get(fastAndFurious.getId()));
        movieService.getAll().forEach(System.out::println);

        CinemaHallService cinemaHallServic = (CinemaHallService) injector.getInstance(CinemaHallService.class);
        CinemaHall blu = new CinemaHall();
        blu.setCapacity(20);
        blu.setDescription("For premiere shows");
        cinemaHallServic.add(blu);
        System.out.println(cinemaHallServic.get(blu.getId()));
        cinemaHallServic.getAll().forEach(System.out::println);

        //MovieSessionService movieSessionService = (MovieSessionService) injector.getInstance(MovieSessionService.class);
        //MovieSession one = new MovieSession();
        //one.setMovie(fastAndFurious);
        //one.setShowTime(LocalDateTime.of(2019, Month.MAY, 15, 11, 30));
        //movieSessionService.add(one);
        //System.out.println(movieSessionService.get(one.getId()));
        //movieSessionService.findAvailableSessions(fastAndFurious.getId());



    }
}
