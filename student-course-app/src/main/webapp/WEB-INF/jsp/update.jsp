<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Student</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
    <div class="container">
        <h1>Update Student</h1>
        <hr>
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger">
                ${errorMessage}
            </div>
        </c:if>

        <form:form action="/updateStudent" modelAttribute="student" method="POST" class="form">
            <form:hidden path="id"/>
            
            <div class="form-group">
                <form:input path="name" placeholder="Student Name" class="form-control mb-4"/>
                <form:errors path="name" cssClass="error-message" />
            </div>
            
            <div class="form-group">
                <form:input path="email" placeholder="Student Email" class="form-control mb-4"/>
                <form:errors path="email" cssClass="error-message" />
            </div>
            
            <div class="form-group">
                <form:select path="course.id" class="form-control mb-4">
                    <form:options items="${listCourses}" itemValue="id" itemLabel="name"/>
                </form:select>
                <form:errors path="course" cssClass="error-message" />
            </div>
            
            <button type="submit" class="btn btn-info col-2">Update Student</button>
            <a href="/" class="btn btn-secondary col-2">Back to List</a>
        </form:form>
    </div>
</body>
</html>
