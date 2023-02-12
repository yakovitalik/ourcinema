package ru.yakovitalik.moviegaid.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Movie")
public class Movie {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;                 // id фильма, генерируется в БД

    @NotEmpty(message = "Название фильма не может быть пустым!")
    @Size(min = 1, max = 150, message = "Название фильма должно быть от 1 до 150 символов!")
    @Column(name = "title")
    private String title;           // название фильма

    @NotEmpty(message = "Жанр не может быть пустым!")
    @Size(min = 1, max = 40, message = "Жанр должен быть от 1 до 40 символов!")
    @Column(name = "genre")
    private String genre;           // жанр

    @NotEmpty(message = "Страна не может быть пустой!")
    @Size(min = 1, max = 70, message = "Страна должна быть от 1 до 70 символов!")
    @Column(name = "country")
    private String country;         // страна

    @Min(value = 1900, message = "Фильм не может быть раньше 1900 года!")
    @Column(name = "year")
    private int year;               // год выхода фильма

    @NotEmpty(message = "Фильм не может быть без режиссера!")
    @Size(min = 1, max = 40, message = "Имя режиссера должно быть от 1 до 40 символов!")
    @Column(name = "director")
    private String director;        // режиссер

    @NotEmpty(message = "Длительность фильма должна быть указана!")
    @Size(min = 1, max = 20, message = "Длительность фильма должна быть от 1 до 20 символов!")
    @Column(name = "duration")
    private String duration;        // длительность фильма (1ч 23мин)

    @NotEmpty(message = "Фильм должен иметь описание сюжета!")
    @Column(name = "description")
    private String description;     // описание сюжета фильма

    @Column(name = "rating")
    private double rating;          // рейтинг фильма

    @Column(name = "cover")
    private String cover;           // название файла обложки фильма(с расширением)

    @Column(name = "video")
    private String video;           // название видео файла фильма(с расширением)


    @ManyToMany
    @JoinTable(
            name = "Movie_Actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private List<Actor> actors;     // список актеров

    // пустой конструктор
    public Movie() {
    }

    // общий конструктор с параметрами(поле id не задаем, его генерирует БД)
    public Movie(String title, String genre, String country, int year, String director,
                 String duration, String description, double rating, String cover, String video) {
        this.title = title;
        this.genre = genre;
        this.country = country;
        this.year = year;
        this.director = director;
        this.duration = duration;
        this.description = description;
        this.rating = rating;
        this.cover = cover;
        this.video = video;
    }

    // гетерры и сеттеры для полей

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    // toString() для тестового вывода актеров
    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", country='" + country + '\'' +
                ", year=" + year +
                ", director='" + director + '\'' +
                ", duration='" + duration + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                '}';
    }

    // для сравнения и поиска объектов в списке
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id && year == movie.year && Double.compare(movie.rating, rating)
                == 0 && Objects.equals(title, movie.title) && Objects.equals(genre, movie.genre)
                && Objects.equals(country, movie.country) && Objects.equals(director, movie.director)
                && Objects.equals(duration, movie.duration) && Objects.equals(description, movie.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, genre, country, year, director, duration, description, rating);
    }
}
