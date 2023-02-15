# GefestFilms
*****************************
## About program
*****************************
This is the backend part of the cinema portal. 
The application is written in Java (JDK 17) in the Spring MVC framework.
This is a full-fledged CRUD application.

I use JDBC template to work with the database (I write SQL queries in the code).
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

You also need to add your data to the database.properties file.
Use the sql.txt file to create tables in the database.

*****************************
## What does this app
*****************************
This program manages the server part of the movie portal. 
It writes and reads information from the database and passes it to the HTML view.
