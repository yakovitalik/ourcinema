package ru.yakovitalik.moviegaid.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.yakovitalik.moviegaid.models.Actor;

import java.util.List;

// класс для операций с базой данных для актеров
@Component
public class ActorDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public ActorDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    // выгрузить список всех актеров их базы данных
    @Transactional(readOnly = true)
    public List<Actor> index() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select a from Actor a", Actor.class)
                .getResultList();
    }

    // выгрузить конкретного актера из базы данных
    @Transactional(readOnly = true)
    public Actor show(int id) {
        Session session = sessionFactory.getCurrentSession();
        Actor actor = session.get(Actor.class, id);
        String movies = actor.getMovies().toString();
        return actor;
    }

    // сохранить актера в базе данных
    @Transactional
    public void save(Actor actor) {
        Session session = sessionFactory.getCurrentSession();
        session.save(actor);
    }

    // обновить данные актера в базе данных
    @Transactional
    public void update(int id, Actor updatedActor) {
        Session session = sessionFactory.getCurrentSession();
        Actor actorToBeUpdated = session.get(Actor.class, id);

        actorToBeUpdated.setName(updatedActor.getName());
    }

    // удалить актера из базы данных
    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Actor.class, id));
    }
}