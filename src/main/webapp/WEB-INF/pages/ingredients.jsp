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

    <c:if test="${ingredient.id==null}">
        <h2>Add ingredient</h2>
    </c:if>
    <c:if test="${!(ingredient.id==null)}">
        <h2>Edit ingredient</h2>
    </c:if>
    <c:url var="addAction" value="/add_ingredient"/>
    <form:form action="${addAction}" commandName="ingredient">
        <form:hidden path="id"/>
        <br>
        <form:label path="name">Name:</form:label>
        <form:input path="name" class="form-control" placeholder="Name"/>

        <c:if test="${ingredient.id==null}">
            <button type="submit" class="btn btn-success" size="Small">Add ingredient</button>
        </c:if>
        <c:if test="${!(ingredient.id==null)}">
            <button type="submit" class="btn btn-warning" size="Small">Save ingredient</button>
        </c:if>
    </form:form>

    <h2>Ingredients</h2>

    <div class="dropdown">
        <button class="btn btn-primary dropdown-toggle" type="button" id="filter" data-toggle="dropdown">Filter
            <span class="caret"/></button>
        <ul class="dropdown-menu" role="menu" aria-labelledby="filter">
            <li role="presentation"><a role="menuitem" href="/">All</a></li>
            <li role="presentation" class="divider"></li>
            <li role="presentation"><a role="menuitem" href="filter/used">Used</a></li>
            <li role="presentation"><a role="menuitem" href="filter/unused">Unused</a></li>
        </ul>
    </div>

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
                                <td><form:form action="edit_ingredient/${ingredient.id}" method="post"><input type="submit"
                                                                                                   size="XSmall"
                                                                                                   class="btn btn-info btn-mini"
                                                                                                   value="Edit..."/>
                                </form:form></td>
                                <td><form:form action="remove_ingredient/${ingredient.id}" method="post"><input type="submit"
                                                                                                     size="XSmall"
                                                                                                     class="btn btn-danger btn-mini"
                                                                                                     value="Delete"/>
                                </form:form></td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

</div>
</body>
</html>
