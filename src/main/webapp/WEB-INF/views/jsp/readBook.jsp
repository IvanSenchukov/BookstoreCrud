<%@ page import="ru.senchukov.ivan.bookstore.model.Book" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Ivan Senchukov
  Date: 18.02.2018
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Book book = (Book)request.getAttribute("book");
%>

<html>
<head>
    <title>Read the Book!</title>

    <link href="<c:url value="/resources/core/css/hello.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/core/css/bootstrap.min.css" />" rel="stylesheet">

</head>
<body>

<div class="panel panel-default">

    <div class="panel-heading">
        <h1>Book preview</h1>
    </div>

    <div class="panel-body">

        <div class="panel panel-default">

            <div class="panel-heading">

                <div class="row">
                    <div id="bookId" style="display: none"><%=book.getId()%></div>
                    <div id="bookTitle" class="col-sm-4">
                        <h3 style="font-weight: bold">Title:</h3>
                        <h3 style="text-decoration: underline"><%=book.getTitle()%></h3>
                    </div>
                    <div id="bookAuthor" class="col-sm-2">
                        <h3 style="font-weight: bold">Author:</h3>
                        <h3 style="text-decoration: underline"><%=book.getAuthor()%></h3>
                    </div>
                    <div id="bookPrintYear" class="col-sm-2">
                        <h3 style="font-weight: bold">Printing Year:</h3>
                        <h3 style="text-decoration: underline"><%=book.getPrintYear()%></h3>
                    </div>
                    <div id="bookIsbn" class="col-sm-2">
                        <h3 style="font-weight: bold">ISBN:</h3>
                        <h3 style="text-decoration: underline"><%=book.getIsbn()%></h3>
                    </div>
                    <div id="bookIsReaded" class="col-sm-2">
                        <h3 style="font-weight: bold">Read already?</h3>
                        <h3 style="text-decoration: underline"><%=book.isReadAlready() ? "Yes" : "No"%></h3>
                    </div>
                </div>

            </div>

            <div class="panel-body">
                <h3><%=book.getDescription()%></h3>
            </div>

            <div class="panel-footer">
                <div class="row">
                    <a href="/" class="btn btn-primary col-sm-2">Back to the books list!</a>
                    <a href="/readTheBook/<%=book.getId()%>" class="btn btn-danger col-sm-2">Read the book!</a>
                    <a href="/openUpdateForm/<%=book.getId()%>" class="btn btn-default col-sm-2">Update the book.</a>
                </div>
            </div>


        </div>
    </div>
</div>

<script src="<c:url value="/resources/core/js/jquery-1.11.2.js" />"></script>
<script src="<c:url value="/resources/core/js/bootstrap.min.js" />"></script>
</body>
</html>
