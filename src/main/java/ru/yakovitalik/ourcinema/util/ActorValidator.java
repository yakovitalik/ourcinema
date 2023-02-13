package ru.yakovitalik.ourcinema.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.yakovitalik.ourcinema.dao.ActorDAO;
import ru.yakovitalik.ourcinema.models.Actor;

@Component
public class ActorValidator implements Validator {

    private final ActorDAO actorDAO;

    @Autowired
    public ActorValidator(ActorDAO actorDAO) {
        this.actorDAO = actorDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Actor.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Actor actor = (Actor) o;

        // посмотреть, есть ли актер с таким именем в базе данных
        // если будет не null, значит ошибка, такой актер в базе есть
        if (actorDAO.show(actor.getName()).isPresent()) {
            errors.rejectValue("name", "", "Этот актер уже есть в базе данных!");
        }
    }
}
