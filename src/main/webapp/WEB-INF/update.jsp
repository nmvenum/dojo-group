<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Movie Court</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container {
            margin-top: 30px;
        }
        .card {
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <!-- Header -->
        <div class="d-flex justify-content-between align-items-center">
            <h1><c:out value="${movie.movieTitle}" /></h1>
            <p>
                <a href="/home" class="btn btn-link" aria-label="Home">Home</a> |
                <a href="/logout" class="btn btn-link" aria-label="Logout">Logout</a>
            </p>
        </div>
        <!-- Movie Details -->
        <div class="mt-3">
            <p>Rating: <c:out value="${movie.rating}" /> out of 5</p>
            <p>Posted by: <c:out value="${movie.user.userName}" /></p>
            <p><strong>Comment:</strong></p>
            <div class="d-flex">
                <c:if test="${user.id == movie.user.id}">
                    <a class="btn btn-primary m-1" href="/updateMovie/${movie.id}" aria-label="Update Movie">Update</a>
                    <form action="/deleteMovie/${movie.id}" method="post" class="m-1">
                        <input type="hidden" name="_method" value="delete">
                        <input class="btn btn-danger" type="submit" value="Delete" aria-label="Delete Movie">
                    </form>
                </c:if>
            </div>
        </div>
        <!-- Comments Section -->
        <div class="card border-danger mb-3">
            <div class="card-header">Comments</div>
            <div class="card-body">
                <c:forEach var="comment" items="${movie.comments}">
                    <p><strong><c:out value="${comment.user.userName}" />:</strong> <c:out value="${comment.text}" /></p>
                </c:forEach>
            </div>
        </div>
        <!-- Add Comment Form -->
        <div class="card border-danger mb-3">
            <div class="card-header">Add a Comment</div>
            <div class="card-body">
                <form:form action="/addComment" method="post" modelAttribute="commentForm">
                    <div class="form-group">
                        <form:label class="form-label" path="comment">Add a comment:</form:label>
                        <form:errors class="text-danger" path="comment" />
                        <form:textarea class="form-control" path="comment" rows="3" />
                    </div>
                    <button class="btn btn-danger" type="submit" aria-label="Submit Comment">Submit</button>
                </form:form>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5-1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
