<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Movie Court</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <h1>Edit Movie</h1>
        <form:form action="/processUpdateReview/${movie.id}" method="post" modelAttribute="movie">
            <div class="form-group">
                <form:label path="movieTitle">Movie Title:</form:label>
                <form:input path="movieTitle" class="form-control"/>
            </div>
            <div class="form-group">
                <form:label path="movieRating">Rating:</form:label>
                <form:input path="movieRating" class="form-control"/>
            </div>
            <div class="form-group">
                <form:label path="movieComment">Comment:</form:label>
                <form:textarea path="movieComment" class="form-control" rows="3"/>
            </div>
            <button type="submit" class="btn btn-primary">Update Movie</button>
        </form:form>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
