# OurCinema
*****************************
## About program
*****************************
This is an online cinema.
The application dynamically builds the web pages of the online cinema by downloading information from the database and using the files in the application folder. 
You can add movies to it, and application users will be able to watch them online.
A little later, a link to a YouTube video will appear here, where I will review this project.

Stack: Java17/SpringMVC/Hibernate ORM/Maven/Apache Tomcat 9/HTML5/CSS3/Thymeleaf/.
The application is written in Java (JDK 17) in the Spring MVC + Hibernate ORM.
This is a full-fledged CRUD application.
The frontend of this application is written in HTML and CSS, without the use of JavaScript.

The project uses a PostgreSQL database.

This application is configured with Java code and uses annotations.
Thymeleaf template engine is used to connect Java code to HTML.
The fields of the Film class have form validation that prevents
incorrect values from being passed to the database.

*****************************
## How to use the application
*****************************
To use the application, you must have installed databases PostgreSQL, 
Apache Tomcat  (version 8 or 9 is recommended).
The program runs as a war application.

The "\src\main\resources" directory contains the sql.txt file, which contains instructions on the required set of tables for this application, as well as sql queries for creating tables in the database.
You also need to add your data to the database.properties file.
You can find a template for such a file in the "\src\main\resources" folder. You will need to remove the .origin extension from it, as well as enter the name of the database of your created data and your username and password into it.

In addition to creating the database, you need to add the movie cover image files to the "\webapp\sources\img" folder, and the movie's video file to the "\webapp\sources\video" folder.
The recommended image size is 250x375 pixels.
The recommended video format is .mp4, but you can use others by changing the video display settings in the "\webapp\WEB-INF\views\movies\show.html" file.
