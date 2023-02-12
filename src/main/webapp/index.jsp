<html>
<head lang="ru">
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <title>Кинотеатр НашеКино</title>
    <link rel="stylesheet" type="text/css" href="sources/css/main_index.css">
</head>

<body>
<div class="main">
    <div class="header">
        <div class="logo">
            <div class="logo_text">
                <h1><a href="/">НашеКино</a></h1>
                <h2>Кино - наша страсть</h2>
            </div>
        </div>

        <div class="menubar">
            <ul class="menu">
                <li class="selected"><a href="/">Главная</a></li>
                <li><a href="/movies">Фильмы</a></li>
                <li><a href="#">Сериалы</a></li>
                <li><a href="#">Рейтинг фильмов</a></li>
                <li><a href="#">Контакты</a></li>
            </ul>
        </div>
    </div>
    <div class="site_content">
        <h1>Выбор режима работы с кинотеатром</h1>
        <br />
        <br />
        <h3><a href="/movies">Фильмы (режим пользователя)</a></h3>
        <br />
        <h3><a href="/admin">Редактирование фильмов (режим администратора)</a></h3>
        <br />
    </div>
</div>
</body>
</html>
