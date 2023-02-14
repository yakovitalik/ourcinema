package ru.yakovitalik.ourcinema.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.yakovitalik.ourcinema.models.Actor;
import ru.yakovitalik.ourcinema.models.Movie;

import java.util.List;

// класс для операций с базой данных совместно для фильмов и актеров
@Component
public class MovieActorDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public MovieActorDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // выгрузит список актеров для конкретного фильма
    @Transactional(readOnly = true)
    public List<Actor> showList(int movieId) {
        Session session = sessionFactory.getCurrentSession();
        Movie movie = session.get(Movie.class, movieId);
        List<Actor> actorList = movie.getActors();
        String actors = actorList.toString();
        return actorList;
    }

    // сохранить актера в базе данных и вернуть его id
    @Transactional
    public int saveActor(Actor actor) {
        Session session = sessionFactory.getCurrentSession();
        return (int) session.save(actor);
    }

    // получить фильм из БД
    @Transactional(readOnly = true)
    public Movie getMovie(int movieId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Movie.class, movieId);
    }

    // получить актера из БД
    @Transactional(readOnly = true)
    public Actor getActor(int actorId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Actor.class, actorId);
    }

    // привязка актера к фильму и фильма к актеру в БД
    @Transactional
    public void add(int movieId, int actorId) {
        Session session = sessionFactory.getCurrentSession();
        Movie movie = session.get(Movie.class, movieId);
        Actor actor = session.get(Actor.class, actorId);

        // добавляем актера в список актеров этого фильма
        List<Actor> actors = movie.getActors();
        actors.add(actor);
        movie.setActors(actors);

        // добавляем фильм в список фильмов этого актера
        List<Movie> movies = actor.getMovies();
        movies.add(movie);
        actor.setMovies(movies);
    }

}
