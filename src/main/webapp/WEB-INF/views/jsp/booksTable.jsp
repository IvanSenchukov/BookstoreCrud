<%@ page import="net.sf.json.JSONArray" %>
<%--
  Created by IntelliJ IDEA.
  User: Ivan Senchukov
  Date: 16.02.2018
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    JSONArray booksJson = (JSONArray) request.getAttribute("books");
    int pagesCount = (int) request.getAttribute("pagesCount");
    int currentPage = (int) request.getAttribute("currentPage");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bookstore</title>

    <link href="<c:url value="/resources/core/css/hello.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/core/css/bootstrap.min.css" />" rel="stylesheet">

</head>

<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Spring 5 MVC Project</a>
        </div>
    </div>
</nav>

<div class="jumbotron">
    <div class="container">
        <h1>Bookstore</h1>
    </div>
</div>

<div class="container">

    <div class="row">

        <div class="col-sm-4">
            <nav class="navbar navbar-default">
                <div class="row">
                    <div class="container-fluid">
                        <ul id="paginationNavbar" class="nav navbar-nav">

                        </ul>
                    </div>
                </div>
            </nav>
        </div>

        <div class="col-sm-2">
            <a href="/openCreateForm" class="btn btn-primary btn-lg">Add new Book</a>
        </div>

        <div class="col-sm-6 row">
            <form action="/filterByTitle" method="get">
                <div class="col-sm-8">
                    <input type="text"
                           name="title"
                           maxlength="100"
                           required="true"
                           placeholder="Type the title here"
                           class="form-control input-lg"/>
                </div>
                <div class="col-sm-4">
                    <button type="submit"
                           class="btn btn-primary btn-lg">
                        Filter by Title
                    </button>
                </div>

            </form>
        </div>
    </div>


    <table class="table" id="tableContainer">
        <thead style="font-size: 22px;">
        <tr>
            <th class="col-md-8">Title and Author</th>
            <th class="col-md-4">Options</th>
        </tr>
        </thead>
        <tbody>
    </table>

</div>

<hr>
<footer>
    <p></p>
</footer>
</div>

<script src="<c:url value="/resources/core/js/jquery-1.11.2.js" />"></script>
<script src="<c:url value="/resources/core/js/booksTable.js" />"></script>
<script src="<c:url value="/resources/core/js/bootstrap.min.js" />"></script>

<script>
    bookstoreFrontPageMaster.currentPage = <%=currentPage%>;
    bookstoreFrontPageMaster.pagesCount = <%=pagesCount%>;
    bookstoreFrontPageMaster.books = <%=booksJson.toString()%>;

    $(document).ready(function () {
        bookstoreFrontPageMaster.printPaginationBar();
        bookstoreFrontPageMaster.fillTable();
    });


</script>

</body>
</html>
