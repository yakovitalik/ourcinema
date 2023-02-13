package ru.yakovitalik.ourcinema.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.yakovitalik.ourcinema.models.Movie;

import java.util.List;

// класс для операций с базой данных для фильмов
@Component
public class MovieDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public MovieDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // метод для вывода общего списка всех фильмов
    @Transactional(readOnly = true)
    public List<Movie> index() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select m from Movie m", Movie.class)
                .getResultList();
    }

    // выведет конкретный фильм
    @Transactional(readOnly = true)
    public Movie show(int id) {
        Session session = sessionFactory.getCurrentSession();
        Movie movie = session.get(Movie.class, id);
        String actors = movie.getActors().toString();
        return movie;
    }

    // сохранение фильма в БД
    @Transactional
    public void save(Movie movie) {
        Session session = sessionFactory.getCurrentSession();
        session.save(movie);
    }

    // обновление фильма в БД
    @Transactional
    public void update(int id, Movie updatedMovie) {
        Session session = sessionFactory.getCurrentSession();
        Movie movieToBeUpdated = session.get(Movie.class, id);

        movieToBeUpdated.setTitle(updatedMovie.getTitle());
        movieToBeUpdated.setGenre(updatedMovie.getGenre());
        movieToBeUpdated.setCountry(updatedMovie.getCountry());
        movieToBeUpdated.setYear(updatedMovie.getYear());
        movieToBeUpdated.setDirector(updatedMovie.getDirector());
        movieToBeUpdated.setDuration(updatedMovie.getDuration());
        movieToBeUpdated.setDescription(updatedMovie.getDescription());
        movieToBeUpdated.setRating(updatedMovie.getRating());
    }

    // удаление фильма из базы данных
    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Movie.class, id));
    }
}