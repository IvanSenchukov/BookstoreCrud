<%@ page import="ru.senchukov.ivan.bookstore.model.Book" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Ivan Senchukov
  Date: 18.02.2018
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Create new Book!</title>

    <link href="<c:url value="/resources/core/css/hello.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/core/css/bootstrap.min.css" />" rel="stylesheet">

</head>
<body>

<%--@elvariable id="updateForm" type=""--%>
<form:form action="/createBook" method="post" modelAttribute="bookForm">

    <div class="panel panel-default">

        <div class="panel-heading">
            <h1>Create book!</h1>
        </div>

        <div class="panel-body">

            <div class="panel panel-default">

                <div class="panel-heading">

                    <div class="row">

                        <div id="bookTitle" class="col-sm-4">
                            <h3 style="font-weight: bold">Title:</h3>
                            <form:input type="text"
                                        name="title"
                                        path="title"
                                        maxlength="100"
                                        required="true"
                                        style="font-size: 24px; width: 100%;"/>
                        </div>
                        <div id="bookAuthor" class="col-sm-2">
                            <h3 style="font-weight: bold">Author:</h3>
                            <form:input type="text"
                                        name="author"
                                        path="author"
                                        maxlength="100"
                                        required="true"
                                        style="font-size: 24px; width: 100%;"/>
                        </div>
                        <div id="bookPrintYear" class="col-sm-2">
                            <h3 style="font-weight: bold">Printing Year:</h3>
                            <form:input type="text"
                                        name="printYear"
                                        path="printYear"
                                        required="true"
                                        pattern="[0-9]{4}"
                                        style="font-size: 24px; width: 100%;"/>
                        </div>
                        <div id="bookIsbn" class="col-sm-2">
                            <h3 style="font-weight: bold">ISBN:</h3>
                            <form:input type="text"
                                        name="isbn"
                                        path="isbn"
                                        style="font-size: 24px; width: 100%;"
                                        maxlength="20"
                                        required="true"/>
                        </div>

                    </div>

                </div>

                <div class="panel-body">
                    <form:textarea type="textarea"
                                   name="description"
                                   path="description"
                                   rows="3"
                                   maxlength="255"
                                   required="true"
                                   style="font-size: 24px; width: 100%;"/>
                </div>

                <div class="panel-footer">
                    <div class="row">
                        <a href="/" class="btn btn-default col-sm-2">Back to the books list!</a>
                        <button type="submit" class="btn btn-primary col-sm-2">Create the book!</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

</form:form>

<script src="<c:url value="/resources/core/js/jquery-1.11.2.js" />"></script>
<script src="<c:url value="/resources/core/js/bootstrap.min.js" />"></script>
</body>
</html>
