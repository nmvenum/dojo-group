<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
    <div class="container">
        <div>
            <div class="d-flex justify-content-between">
                <h1>Hello <c:out value="${user.userName}"/></h1>
                <p><a href="#">Home</a> | <a href="#">logout</a></p>
            </div>
            <div>
                <table class="table table-hover border-primary">
                    <thead>
                        <tr >
                            <th>Movie Title</th>
                            <th>Rating</th>
                            <th>Posted By</th>
                        </tr>
                    </thead>
                    <tbody class="table-group-divider">
                        <c:forEach var="movie" items="${movies}">
                            <tr class="table-active">
                                <td><a href="#"><c:out value="${movie.movieTitle}"></c:out></a></td>
                                <td>$<c:out value="${movie.movieRating}"></c:out></td>
                                <td><c:out value="${movie.user.userName}"></c:out></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <a href="#"><button>Add a Movie</button></a>
            </div>
        </div>
    </div>
</body>
</html>