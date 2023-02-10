<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Meals</title>
    <style>
      .normal {
        color: green;
      }
      .excess {
        color: red;
      }
    </style>
</head>
<body>
<section>
<h3><a href="index.html">Home</a></h3>
<hr>
<table border="1">
    <caption><h2>Meal list</h2></caption>
    <a href="meals?action=create">Add meal</a>
    <hr>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    <c:forEach items="${requestScope.meals}" var="meal">
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealTo"/>
    <tr class="${meal.excess ? 'excess' : 'normal'}">
        <td>${fn:replace(meal.dateTime, 'T', ' ')}</td>
        <td>${meal.description}</td>
        <td>${meal.calories}</td>
        <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
        <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
        </c:forEach>
</table>
</section>
</body>
</html>
