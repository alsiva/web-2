<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Arrays" %>
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
    <li>Вариант: 12086</li>
</ul>
</header>
<p class="green-box">
    Разработать веб-приложение на базе сервлетов и JSP, определяющее попадание точки на координатной плоскости в заданную область.
</p>
<img alt="Зона для тестирования попадания" src="areas.png" class="area"/>
<canvas id="area" ></canvas>
<form id="hitDataForm" action="" method="post">
    <div>x
        <c:forEach var="x" items="${Arrays.asList(-3, -2, -1, 0, 1, 2, 3, 4, 5)}">
            <label> <c:out value="${x}"/>
                <input type="radio" name="x" value="<c:out value="${x}"/>" ${x == 0 ? 'checked' : ''}>
            </label>
        </c:forEach>
    </div>

    <label>
        y <input id="yInput" name="y" value="0" type="text" size="15" maxlength="4" />
    </label>

    <label>
        radius
        <select name="r" id="rInput">
            <c:forEach var="r" items="${Arrays.asList(1, 1.5, 2, 2.5, 3)}" varStatus="loop">
                <option value="<c:out value="${r}"/>">
                    <c:out value="${r}"/>
                </option>
            </c:forEach>
        </select>
    </label>
    <button type="submit">submit</button>
</form>
<table>
    <thead>
    <tr>
        <th>x:</th>
        <th>y:</th>
        <th>Радиус:</th>
        <th>Попадание:</th>
    </tr>
    </thead>
    <tbody id="hitDataTable">
    <c:forEach var="hitResult" items="${applicationScope.hits}">
        <tr>
            <td> <c:out value="${hitResult.x}"/></td>
            <td> <c:out value="${hitResult.y}"/></td>
            <td> <c:out value="${hitResult.r}"/></td>
            <td> <c:out value="${hitResult.doesHit ? \"Попадание есть\" : \"Попадания нет\"}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<script src="validation.js"></script>
<script src="canvas.js"></script>
</body>
</html>