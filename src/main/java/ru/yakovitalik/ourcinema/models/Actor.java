package ru.yakovitalik.ourcinema.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Actor")
public class Actor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;             // генерируется в БД

    @NotEmpty(message = "Имя актера не может быть пустым!")
    @Size(min = 1, max = 100, message = "Имя актера должно быть от 1 до 100 символов!")
    @Column(name = "name", unique = true, nullable = false)
    private String name;        // имя актера

    @ManyToMany(mappedBy = "actors", fetch = FetchType.EAGER)
    private List<Movie> movies; // список фильмов

    // конструктор без параметров
    public Actor() {
    }

    // конструктор с параметрами(id задается в БД)
    public Actor(String name) {
        this.name = name;
    }

    // геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    // toString для вывода списка актеров
    @Override
    public String toString() {
        return " " + name;
    }

    // для сравнения и поиска объектов в списке
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return id == actor.id &&  Objects.equals(name, actor.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
