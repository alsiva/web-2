<%@ page import="edu.ifmo.web.HitResult" %>
<%@ page contentType="text/html;charset=UTF-8"  %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Веб-программирование</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,300;0,400;0,500;0,700;1,300;1,400;1,500;1,700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="main.css">
</head>
<body>
<header>
<h1>Лабораторная работа №2</h1>
<ul>
    <li>Студент: <span data-name="alex">Иванов Алексей Анатольевич</span></li>
    <li>Группа: <span class="cursive">P3211</span></li>
    <li>Вариант: 11008</li>
</ul>
</header>
<p class="green-box">
    Разработать веб-приложение на базе сервлетов и JSP, определяющее попадание точки на координатной плоскости в заданную область.
</p>
<img alt="Зона для тестирования попадания" src="areas.png" class="area"/>
<form id="hitDataForm" action="" method="post">
    <label>
        x <input id="xInput" name="x" value="0" type="text" size="15" maxlength="4" />
    </label>

    <label>
        y <input id="yInput" name="y" value="0" type="text" size="15" maxlength="4" />
    </label>

    <label>
        radius
        <select name="r">
            <option value="1" selected>1</option>
            <option value="1.5" selected>1.5</option>
            <option value="2" selected>2</option>
            <option value="2.5" selected>2.5</option>
            <option value="3" selected>3</option>
        </select>
    </label>
    <button type="submit">submit</button>
</form>
<jsp:useBean id="hitStorage" scope="application" class="edu.ifmo.web.HitStorage">
    <jsp:setProperty name="hitStorage" property="*"/>
</jsp:useBean>
<table>
    <thead>
    <tr>
        <th>x:</th>
        <th>y:</th>
        <th>Радиус:</th>
        <th>Попадание:</th>
    </tr>
    </thead>
    <tbody>
    <% for (HitResult hitResult: hitStorage.getHits()) {%>
    <tr>
        <td><%= hitResult.getX() %></td>
        <td><%= hitResult.getY() %></td>
        <td><%= hitResult.getR() %></td>
        <td><%= hitResult.isDoesHit() ? "Попадание есть" : "Попадания нет" %></td>
    </tr>
    <%}%>
    <?php endforeach; ?>
    </tbody>
</table>
<script src="validation.js"></script>
</body>
</html>