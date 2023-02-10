<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<html>
<head>
    <title>Meal</title>
    <style>
      dl {
        background: none repeat scroll 0 0 #FAFAFA;
        margin: 8px 0;
        padding: 0;
      }

      dt {
        display: inline-block;
        width: 170px;
      }

      dd {
        display: inline-block;
        margin-left: 8px;
        vertical-align: top;
      }

    </style>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr>
    <h2>${param.action == 'create' ? 'Create meal' : 'Update meal'}</h2>
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <form method="post" action="meals">
        <input type="hidden" name="id" value="${meal.id}">
        <dl>
            <dt>DateTime:</dt>
            <dd>
                <label>
                    <input type="datetime-local" value="${meal.dateTime}" name="dateTime" required>
                </label>
            </dd>
        </dl>
        <dl>
            <dt>Description:</dt>
            <dd>
                <label>
                    <input type="text" value="${meal.description}" name="description" required>
                </label>
            </dd>
        </dl>
        <dl>
            <dt>Calories:</dt>
            <dd>
                <label>
                    <input type="number" value="${meal.calories}" name="calories" required>
                </label>
            </dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>
