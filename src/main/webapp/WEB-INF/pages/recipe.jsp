<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
    <meta charset="utf-8">
    <title>Cook book</title>

    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="http://getbootstrap.com/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="http://getbootstrap.com/dist/css/bootstrap-responsive.min.css" rel="stylesheet">

    <style>
        .container {
            width: 60%;
            margin-left: 5%;
            margin-right: 5%;
            margin-top: 2%;
            height: 95%;
        }

    </style>
</head>
<body>
<div class="container">
    <a href="/cookbook/">Back to Main</a> <br>

    <c:if test="${empty recipe.id}">
        <h2>Add recipe</h2>
    </c:if>
    <c:if test="${!empty recipe.id}">
        <h2>Edit recipe</h2>
    </c:if>
    <c:url var="addAction" value="/save_recipe"/>
    <form:form action="${addAction}" commandName="recipe">
        <%--<form:hidden path="id"/>--%>
        <form:label path="name">Name:</form:label>
        <form:input path="name" class="form-control" placeholder="Name"/>
        <form:label path="description">Description:</form:label>
        <form:input path="description" class="form-control" placeholder="Description"/>


        <table class="table table-bordered">
            <tbody>
            <tr>
                <td>
                    <h3>In recipe</h3>
                    <c:if test="${!empty recipe.ingredients}">

                        <table class="table table-bordered table-striped table-hover header-fixed">
                            <thead>
                            <tr>
                                <th>Name</th>
                                <th>Quantity</th>
                                <th>Units</th>
                                <th width="120px">&nbsp;</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${recipe.ingredients}" var="recipeIngredient">
                                <tr>
                                    <td>${recipeIngredient.ingredient.name}</td>
                                    <td>${recipeIngredient.quantity}</td>
                                    <td>${recipeIngredient.unit}</td>
                                    <td width="120px">
                                        <table>
                                            <tr>
                                                <td><form:form action="Drop/${recipeIngredient.id}" method="post"><input
                                                        type="submit"
                                                        size="XSmall"
                                                        class="btn btn-danger btn-mini"
                                                        value="Drop"/>
                                                </form:form></td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                </td>
                <td>
                    <h3>Ingredients</h3>
                    <c:if test="${!empty ingredients}">

                        <table class="table table-bordered table-striped table-hover header-fixed">
                            <thead>
                            <tr>
                                <th>Name</th>
                                <th width="120px">&nbsp;</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${ingredients}" var="ingredient">
                                <tr>
                                    <td>${ingredient.name}</td>
                                    <td width="120px">
                                        <table>
                                            <tr>
                                                <td><form:form action="take/${ingredient.id}" method="post"><input
                                                        type="submit"
                                                        size="XSmall"
                                                        class="btn btn-info btn-mini"
                                                        value="Take"/>
                                                </form:form></td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                </td>
            </tr>
            </tbody>
        </table>
        <c:if test="${recipe.id==null}">
            <button type="submit" class="btn btn-success" size="Small">Add recipe</button>
        </c:if>
        <c:if test="${!(recipe.id==null)}">
            <button type="submit" class="btn btn-warning" size="Small">Save recipe</button>
        </c:if>
    </form:form>
</div>
</body>
</html>
