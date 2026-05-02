<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Management System</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
    <div class="container">
        <h1>Student List</h1>
        <a href="/showNewStudentForm" class="btn btn-primary mb-3">Add Student</a>
        
        <table class="table">
            <thead>
                <tr>
                    <th>Student Name</th>
                    <th>Student Email</th>
                    <th>Course Enrolled</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="student" items="${listStudents}">
                    <tr>
                        <td>${student.name}</td>
                        <td>${student.email}</td>
                        <td>${student.course.name}</td>
                        <td>
                            <a href="/showFormForUpdate/${student.id}" class="btn btn-primary btn-sm">Update</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
