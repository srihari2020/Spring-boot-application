<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Authors - Library Management System</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #f0f2f5;
            color: #333;
        }

        /* ---- Header ---- */
        header {
            background: linear-gradient(135deg, #1a237e, #283593);
            color: white;
            padding: 18px 40px;
            display: flex;
            align-items: center;
            justify-content: space-between;
            box-shadow: 0 2px 8px rgba(0,0,0,0.3);
        }
        header h1 { font-size: 1.6rem; letter-spacing: 1px; }
        header span { font-size: 0.9rem; opacity: 0.75; }

        /* ---- Navbar ---- */
        nav {
            background: #283593;
            display: flex;
            gap: 4px;
            padding: 0 40px;
        }
        nav a {
            color: rgba(255,255,255,0.8);
            text-decoration: none;
            padding: 12px 20px;
            font-size: 0.9rem;
            font-weight: 600;
            transition: background 0.2s, color 0.2s;
            border-bottom: 3px solid transparent;
        }
        nav a:hover { color: white; background: rgba(255,255,255,0.1); }
        nav a.active { color: white; border-bottom: 3px solid #ffd740; }

        /* ---- Main Container ---- */
        .container { max-width: 1100px; margin: 40px auto; padding: 0 20px; }

        /* ---- Toolbar ---- */
        .toolbar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }
        .toolbar h2 { font-size: 1.3rem; color: #1a237e; }

        .btn-add {
            background: #1a237e;
            color: white;
            padding: 10px 22px;
            border: none;
            border-radius: 6px;
            text-decoration: none;
            font-size: 0.95rem;
            font-weight: 600;
            cursor: pointer;
            transition: background 0.2s;
        }
        .btn-add:hover { background: #283593; }

        /* ---- Alert Messages ---- */
        .alert {
            padding: 12px 18px;
            border-radius: 6px;
            margin-bottom: 20px;
            font-size: 0.95rem;
        }
        .alert-success { background: #e8f5e9; color: #2e7d32; border-left: 4px solid #4caf50; }
        .alert-error   { background: #ffebee; color: #c62828; border-left: 4px solid #ef5350; }

        /* ---- Table ---- */
        .card {
            background: white;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.08);
            overflow: hidden;
        }
        table { width: 100%; border-collapse: collapse; }
        thead tr { background: #1a237e; color: white; }
        thead th {
            padding: 14px 18px;
            text-align: left;
            font-size: 0.9rem;
            letter-spacing: 0.5px;
            text-transform: uppercase;
        }
        tbody tr {
            border-bottom: 1px solid #e8eaf6;
            transition: background 0.15s;
        }
        tbody tr:last-child { border-bottom: none; }
        tbody tr:hover { background: #e8eaf6; }
        tbody td { padding: 13px 18px; font-size: 0.95rem; }

        /* ---- Book Count Badge ---- */
        .book-count {
            display: inline-block;
            background: #e8eaf6;
            color: #1a237e;
            padding: 3px 10px;
            border-radius: 12px;
            font-size: 0.82rem;
            font-weight: 600;
        }

        /* ---- Action Buttons ---- */
        .btn-edit {
            background: #ff8f00;
            color: white;
            padding: 6px 14px;
            border-radius: 5px;
            text-decoration: none;
            font-size: 0.85rem;
            font-weight: 600;
            transition: background 0.2s;
            margin-right: 6px;
        }
        .btn-edit:hover { background: #e65100; }

        /* ---- Empty State ---- */
        .empty-state {
            text-align: center;
            padding: 50px 20px;
            color: #888;
        }
        .empty-state p { font-size: 1.1rem; margin-bottom: 15px; }

        /* ---- Footer ---- */
        footer {
            text-align: center;
            margin-top: 40px;
            margin-bottom: 20px;
            color: #aaa;
            font-size: 0.85rem;
        }
    </style>
</head>
<body>

<header>
    <h1>&#128218; Library Management System</h1>
    <span>Spring Boot | JPA | MySQL</span>
</header>

<nav>
    <a href="/">&#128218; Books</a>
    <a href="/authors" class="active">&#9997; Authors</a>
</nav>

<div class="container">

    <!-- Success / Error Messages -->
    <c:if test="${not empty successMessage}">
        <div class="alert alert-success">${successMessage}</div>
    </c:if>
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-error">${errorMessage}</div>
    </c:if>

    <div class="toolbar">
        <h2>Author Directory</h2>
        <a href="/authors/add" class="btn-add">+ Add New Author</a>
    </div>

    <div class="card">
        <c:choose>
            <c:when test="${not empty authors}">
                <table>
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Author Name</th>
                            <th>Books</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="author" items="${authors}" varStatus="status">
                            <tr>
                                <td>${status.count}</td>
                                <td><strong>${author.name}</strong></td>
                                <td>
                                    <span class="book-count">
                                        <c:choose>
                                            <c:when test="${not empty author.books}">${author.books.size()} book(s)</c:when>
                                            <c:otherwise>0 books</c:otherwise>
                                        </c:choose>
                                    </span>
                                </td>
                                <td>
                                    <a href="/authors/edit/${author.id}" class="btn-edit">&#9998; Edit</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <div class="empty-state">
                    <p>No authors found in the library.</p>
                    <a href="/authors/add" class="btn-add">+ Add Your First Author</a>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

</div>

<footer>
    &copy; 2024 Library Management System &mdash; Built with Spring Boot &amp; JSP
</footer>

</body>
</html>
