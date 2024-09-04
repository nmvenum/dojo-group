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
        <div >
            <div class="d-flex justify-content-between">
                <h1>Add a Movie!</h1>
                <p><a href="#">Home</a> | <a href="#">logout</a></p>
            </div>
            <div>
                <form:form action="#" method="post" modelAttribute="movie">
			<p>
				<form:label path="movieTitle">Movie Title:</form:label>
				<form:errors class="text-danger" path="movieTitle" />
				<form:input class="form-control" path="movieTitle" />
			</p>
			<p>
				<form:label path="movieRating">Rating:</form:label>
				<form:errors class="text-danger" path="movieRating" />
				<form:input class="form-control" path="movieRating" />
			</p>
            <p>
				<form:label path="MovieComment">Comment:</form:label>
				<form:errors class="text-danger" path="MovieComment" />
				<form:textarea class="form-control" path="MovieComment" />
			</p>
			<button class="btn btn-outline-primary mt-3">Submit</button>
		</form:form>
            </div>
        </div>
    </div>
</body>
</html>