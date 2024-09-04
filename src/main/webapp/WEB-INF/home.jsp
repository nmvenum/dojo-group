<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
                <a href="/dashboard" class="btn btn-link" aria-label="Home">Home</a> |
                <a href="/logout" class="btn btn-link" aria-label="Logout">Logout</a>
            </p>
        </div>
        <!-- Movie Details -->
        <div class="mt-3">
            <p>Rating: <c:out value="${movie.movieRating}" /> out of 5</p>
            <p>Posted by: <c:out value="${movie.user.userName}" /></p>
            <p><strong>Comment: <c:out value="${movie.movieComment }"/> </strong></p>
        </div>
        <!-- Comments Section -->
        <div class="card border-danger mb-3">
            <div class="card-header">Comments</div>
            <div class="card-body">
                <c:forEach var="comment" items="${movie.comments}">
                    <p>
                        <strong><c:out value="${comment.user.userName}" />:</strong> 
                        <c:out value="${comment.text}" />
                        <c:if test="${user.id == comment.user.id}">
                            <a href="/editComment/${comment.id}" class="btn btn-warning btn-sm ml-2">Edit</a>
                            <form action="/deleteComment/${comment.id}" method="post" style="display:inline;">
                                <input type="hidden" name="_method" value="delete">
                                <button type="submit" class="btn btn-danger btn-sm ml-2">Delete</button>
                            </form>
                        </c:if>
                    </p>
                </c:forEach>
            </div>
        </div>
        <!-- Add Comment Form -->
        <div class="card border-danger mb-3">
            <div class="card-header">Add a Comment</div>
            <div class="card-body">
                <form:form action="/addComment" method="post" modelAttribute="commentForm">
                    <div class="form-group">
                        <form:label class="form-label" path="text">Add a comment:</form:label>
                        <form:errors class="text-danger" path="text" />
                        <form:textarea class="form-control" path="text" rows="3" />
                    </div>
                    <input type="hidden" name="movieId" value="<c:out value='${movie.id}'/>" />
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
