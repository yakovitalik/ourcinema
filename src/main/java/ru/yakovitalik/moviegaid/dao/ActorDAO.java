package ru.yakovitalik.moviegaid.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.yakovitalik.moviegaid.models.Actor;
import ru.yakovitalik.moviegaid.models.Movie;

import java.util.List;

@Component
public class ActorDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public ActorDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Actor> allactors() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select p from Actor p", Actor.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public List<Actor> showall(int movieId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select p from Actor p", Actor.class)
                .getResultList();
    }

    public List<Actor> show(int movieId) {
        Session session = sessionFactory.getCurrentSession();
        Movie movie = session.get(Movie.class, movieId);
        List<Actor> actors = movie.getActors();
        return actors;
    }

    @Transactional
    public void save(Actor actor) {
        Session session = sessionFactory.getCurrentSession();
        session.save(actor);
    }

    @Transactional
    public void update(int id, Actor updatedActor) {
        Session session = sessionFactory.getCurrentSession();
        Actor actorToBeUpdated = session.get(Actor.class, id);

        actorToBeUpdated.setName(updatedActor.getName());
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Actor.class, id));
    }
}