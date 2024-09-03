<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>ore" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Movie Court</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f0f0f5;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }

    .container {
        width: 400px;
        background-color: #ffffff;
        border-radius: 8px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        padding: 30px;
    }

    h1 {
        text-align: center;
        margin-bottom: 20px;
    }

    p {
        margin-bottom: 15px;
    }

    .form-control {
        width: 100%;
        padding: 8px;
        margin-top: 5px;
        border: 1px solid #ddd;
        border-radius: 4px;
    }

    .text-danger {
        color: #ff0000;
        font-size: 12px;
    }

    .btn {
        display: block;
        width: 100%;
        padding: 10px;
        background-color: #4CAF50;
        color: #ffffff;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        margin-top: 10px;
        font-size: 16px;
    }

    .btn:hover {
        background-color: #45a049;
    }

    .form-section {
        margin-bottom: 20px;
    }
</style>
</head>
<body>
    <div class="container">
        <h1>Welcome to The Movie Court!</h1>
        <div class="form-section">
            <form:form action="/register" method="post" modelAttribute="registerUser">
                <p>
                    <form:label path="userName">User Name</form:label>
                    <form:errors class="text-danger" path="userName" />
                    <form:input class="form-control" path="userName" />
                </p>
                <p>
                    <form:label path="email">Email</form:label>
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
                    <input class="btn" type="submit" value="Submit" />
                </p>
            </form:form>
        </div>
        <div class="form-section">
            <form:form action="/login" method="post" modelAttribute="loginUser">
                <p>
                    <form:label path="email">Email</form:label>
                    <form:errors class="text-danger" path="email" />
                    <form:input class="form-control" path="email" />
                </p>
                <p>
                    <form:label path="password">Password</form:label>
                    <form:errors class="text-danger" path="password" />
                    <form:input class="form-control" type="password" path="password" />
                    <input class="btn" type="submit" value="Submit" />
                </p>
            </form:form>
        </div>
    </div>
</body>
</html>
