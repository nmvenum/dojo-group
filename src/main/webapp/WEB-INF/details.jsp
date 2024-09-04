<%@ page language="java" contentType="text/html; charset=ISO-8859-1"

    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %> 

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>Insert title here</title>

<title>Movie Court</title>

</head>
<body>
	<div class="container">
		<div class="d-flex justify-content-between">

            <h1><c:out value="{movie.movieTitle}"/></h1>
            <p><a href="#">Home</a> | <a href="#">logout</a></p>
		</div>
		<div>
			<p><c:out value="${house.listing} out of 5"/></p>
			<p><c:out value="{movie.user.userName}"/></p>
            <p>**Comment**</p>
            <div class="d-flex">
			<c:if test="${user.id == movie.user.id}">
			<a class="btn btn-primary m-1" href="#">Update</a>
			<form action="#" method="post">
				<input type="hidden" name="_method" value="delete"> <input
					class="btn btn-warning m-1" type="submit" value="Delete">
			</form>	
			</c:if>
		</div>

			<h1>
				<c:out value="{movie.movieTitle}" />
			</h1>
			<p>
				<a href="#">Home</a> | <a href="/logout">logout</a>
			</p>
		</div>
		<div>
			<p>
				<c:out value="{movie.user.userName}" />
			</p>
			<p>**Comment**</p>
			<div class="d-flex">
				<c:if test="${user.id == movie.user.id}">
					<a class="btn btn-primary m-1" href="#">Update</a>
					<form action="#" method="post">
						<input type="hidden" name="_method" value="delete"> <input
							class="btn btn-warning m-1" type="submit" value="Delete">
					</form>
				</c:if>
			</div>

		</div>
		<div class="card border-danger mb-3">
			<c:forEach var="#" items="${#}">
				<p class="card-body">

					<span><c:out value="${#.user.userName}"/></span>
					<c:out value="${#}"/>

					<span><c:out value="${#.user.userName}" /></span>
					<c:out value="${#}" />

				</p>
			</c:forEach>
		</div>
		<div class="card border-danger mb-3">
			<form:form action="#" method="post" modelAttribute="#">

			<p>
				<form:label class="text-danger" path="comment">Add a comment: </form:label>
				<form:errors class="text-danger" path="comment" />
				<form:input class="form-control" path="comment" />
			</p>
			<button class="btn btn-danger">Submit</button>
		</form:form>
		</div>
</body>
</html>

				<p>
					<form:label class="text-danger" path="comment">Add a comment: </form:label>
					<form:errors class="text-danger" path="comment" />
					<form:input class="form-control" path="comment" />
				</p>
				<button class="btn btn-danger">Submit</button>
			</form:form>
		</div>
	</div>
</body>
</html>

