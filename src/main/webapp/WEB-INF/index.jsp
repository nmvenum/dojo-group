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
		<h1>Welcome!</h1>
		<div>
			<form:form action="/register" method="post" modelAttribute="registerUser">
            	<p>
                	<form:label path="userName">User Name</form:label>
                	<form:errors class="text-danger" path="userName" />
                	<form:input class="form-control" path="userName" />
            	</p>
            	<p>
                	<form:label path="email">email</form:label>
                	<form:errors class="text-danger" path="email" />
                	<form:input class="form-control" path="email" />
            	</p>
            	<p>
                	<form:label path="password">Password</form:label>
                	<form:errors class="text-danger" path="password" />
                	<form:input class="form-control" type="password" path="password" />
            	</p>
            	<p>
                	<form:label path="confirm">Confirm Password</form:label>
                	<form:errors class="text-danger" path="confirm" />
                	<form:input class="form-control" type="password" path="confirm" />
            		<input class="btn btn-outline-primary mt-3" type="submit" value="Submit" />
            	</p>
        	</form:form>
		</div>
		<div>
			<form:form action="/login" method="post" modelAttribute="loginUser">
            	<p>
                	<form:label path="email">email</form:label>
                	<form:errors class="text-danger" path="email" />
                	<form:input class="form-control" path="email" />
            	</p>
            	<p>
                	<form:label path="password">Password</form:label>
                	<form:errors class="text-danger" path="password" />
                	<form:input class="form-control" type="password" path="password" />
            		<input class="btn btn-outline-primary mt-3" type="submit" value="Submit" />
            	</p>
        	</form:form>
		</div>
	</div>
</body>
</html>