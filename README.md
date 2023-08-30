# НашеКино

(English translation at the bottom of the page)
Это онлайн-кинотеатр <br />
Посмотреть видео работы этого проекта можно здесь::
<a href="https://youtu.be/0udewm455hM">Video review on youtube</a>
***
<img src=https://github.com/yakovitalik/ourcinema/blob/master/screen1.jpg>

***

<img src=https://github.com/yakovitalik/ourcinema/blob/master/screen2.jpg>

***
## О программе
Это онлайн-кинотеатр.
Приложение динамически строит веб-страницы онлайн-кинотеатра, загружая информацию из базы данных и используя файлы в папке приложения.
В него можно добавлять фильмы, и пользователи приложения смогут смотреть их онлайн.

Стек: Java17/SpringMVC/Hibernate ORM/Maven/Apache Tomcat 9/HTML5/CSS3/Thymeleaf/.
Приложение написано на Java (JDK 17) в Spring MVC + Hibernate ORM.
Это полноценное CRUD-приложение.
Интерфейс этого приложения написан на HTML и CSS без использования JavaScript.

В проекте используется база данных PostgreSQL.

Механизм шаблонов Thymeleaf используется для подключения кода Java к HTML.
Поля класса Film имеют валидацию, которая предотвращает
неправильные значения от передачи в базу данных.


## Как использовать приложение
Для использования приложения у вас должны быть установлены базы данных PostgreSQL,
Apache Tomcat (рекомендуется версия 8 или 9).
Программа работает как war-архив.

В каталоге "\src\main\resources" находится файл sql.txt, содержащий инструкции по необходимому набору таблиц для данного приложения, а также sql-запросы для создания таблиц в базе данных.
Вам также необходимо добавить свои данные в файл data.properties.
Шаблон такого файла можно найти в папке «\src\main\resources». Вам нужно будет удалить из него расширение .origin, а также ввести в нее название базы созданных вами данных и ваш логин и пароль.

Помимо создания базы данных, вам необходимо добавить файлы изображений обложки фильма в папку «\webapp\sources\img», а видеофайл фильма — в папку «\webapp\sources\video».
Рекомендуемый размер изображения — 250x375 пикселей.
Рекомендуемый формат видео — .mp4, но вы можете использовать и другие, изменив настройки отображения видео в файле «\webapp\WEB-INF\views\movies\show.html».

##################################################################

# OurCinema
This is our online cinema. <br />
You can watch a video of this project here:
<a href="https://youtu.be/0udewm455hM">Video review on youtube</a>
***
<img src=https://github.com/yakovitalik/ourcinema/blob/master/screen1.jpg>

***

<img src=https://github.com/yakovitalik/ourcinema/blob/master/screen2.jpg>

***
## About program
This is an online cinema.
The application dynamically builds the web pages of the online cinema by downloading information from the database and using the files in the application folder. 
You can add movies to it, and application users will be able to watch them online.

Stack: Java17/SpringMVC/Hibernate ORM/Maven/Apache Tomcat 9/HTML5/CSS3/Thymeleaf/.
The application is written in Java (JDK 17) in the Spring MVC + Hibernate ORM.
This is a full-fledged CRUD application.
The frontend of this application is written in HTML and CSS, without the use of JavaScript.

The project uses a PostgreSQL database.

This application is configured with Java code and uses annotations.
Thymeleaf template engine is used to connect Java code to HTML.
The fields of the Film class have form validation that prevents
incorrect values from being passed to the database.
## How to use the application
To use the application, you must have installed databases PostgreSQL, 
Apache Tomcat  (version 8 or 9 is recommended).
The program runs as a war application.

The "\src\main\resources" directory contains the sql.txt file, which contains instructions on the required set of tables for this application, as well as sql queries for creating tables in the database.
You also need to add your data to the database.properties file.
You can find a template for such a file in the "\src\main\resources" folder. You will need to remove the .origin extension from it, as well as enter the name of the database of your created data and your username and password into it.

In addition to creating the database, you need to add the movie cover image files to the "\webapp\sources\img" folder, and the movie's video file to the "\webapp\sources\video" folder.
The recommended image size is 250x375 pixels.
The recommended video format is .mp4, but you can use others by changing the video display settings in the "\webapp\WEB-INF\views\movies\show.html" file.
