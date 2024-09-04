<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Movie Court</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f8f9fa;
	margin: 0;
	padding: 0;
}

.container {
	width: 80%;
	margin: 20px auto;
	background-color: #ffffff;
	border-radius: 8px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	padding: 20px;
}

.d-flex {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 20px;
}

h1 {
	margin: 0;
	font-size: 24px;
	color: #343a40;
}

p a {
	color: #007bff;
	text-decoration: none;
	margin: 0 10px;
}

p a:hover {
	text-decoration: underline;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 20px;
}

th, td {
	padding: 10px;
	text-align: left;
	border-bottom: 1px solid #dee2e6;
}

thead {
	background-color: #e9ecef;
}

tbody tr:hover {
	background-color: #f1f1f1;
}

.btn {
	display: inline-block;
	padding: 10px 20px;
	font-size: 16px;
	color: #ffffff;
	background-color: #007bff;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	text-align: center;
	text-decoration: none;
}

.btn:hover {
	background-color: #0056b3;
}
</style>
</head>
<body>
	<div class="container">
		<div>
			<div class="d-flex">
				<h1>
					Hello,
					<c:out value="${user.userName}" />
					!
				</h1>
				<p>
					<a href="/logout">Logout</a>
				</p>
			</div>
			<div>
				<table class="table">
					<thead>
						<tr>
							<th>Movie Title</th>
							<th>Rating</th>
							<th>Posted By</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="movie" items="${movies}">
							<tr>
								<td><a href="/moviePage/${movie.id}"><c:out
											value="${movie.movieTitle}" /></a></td>
								<td><c:out value="${movie.movieRating}" /></td>
								<td><c:out value="${movie.user.userName}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<a href="/watchMovie" class="btn">Add a Movie</a>
			</div>
		</div>
	</div>
</body>
</html>
