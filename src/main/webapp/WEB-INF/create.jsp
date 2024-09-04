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
    <meta charset="ISO-8859-1">
    <title>Movie Court</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            margin-top: 30px;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .header {
            margin-bottom: 20px;
        }
        .header h1 {
            font-size: 1.5rem;
            color: #343a40;
        }
        .header p {
            margin-bottom: 0;
        }
        .header a {
            color: #007bff;
            text-decoration: none;
        }
        .header a:hover {
            text-decoration: underline;
        }
        .form-group label {
            font-weight: bold;
        }
        .btn-primary {
            background-color: #007bff;
            border: none;
        }
        .btn-primary:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header d-flex justify-content-between align-items-center">
            <h1>Add a Movie!</h1>
            <p>
                <a href="#">Home</a> | <a href="/logout">Logout</a>
            </p>
        </div>
        <div>
            <form:form action="#" method="post" modelAttribute="movie">
                <div class="form-group">
                    <form:label path="movieTitle" class="form-label">Movie Title:</form:label>
                    <form:errors class="text-danger" path="movieTitle" />
                    <form:input class="form-control" path="movieTitle" />
                </div>
                <div class="form-group">
                    <form:label path="movieRating" class="form-label">Rating:</form:label>
                    <form:errors class="text-danger" path="movieRating" />
                    <form:input class="form-control" path="movieRating" />
                </div>
                <div class="form-group">
                    <form:label path="MovieComment" class="form-label">Comment:</form:label>
                    <form:errors class="text-danger" path="MovieComment" />
                    <form:textarea class="form-control" path="MovieComment" />
                </div>
                <button class="btn btn-primary mt-3" type="submit">Submit</button>
            </form:form>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
