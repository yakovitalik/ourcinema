package ru.yakovitalik.moviegaid.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.yakovitalik.moviegaid.models.Actor;

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
    public List<Actor> showall(int movieId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select p from Actor p", Actor.class)
                .getResultList();
    }
}
