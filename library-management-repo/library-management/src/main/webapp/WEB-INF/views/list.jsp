<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Library Management System</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

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
        .container {
            max-width: 1100px;
            margin: 40px auto;
            padding: 0 20px;
        }

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

        /* ---- Genre Badge ---- */
        .badge {
            display: inline-block;
            padding: 3px 10px;
            border-radius: 12px;
            font-size: 0.8rem;
            font-weight: 600;
        }
        .badge-fantasy  { background: #e3f2fd; color: #1565c0; }
        .badge-mystery  { background: #fce4ec; color: #880e4f; }
        .badge-horror   { background: #fbe9e7; color: #bf360c; }
        .badge-sci-fi   { background: #e8f5e9; color: #1b5e20; }
        .badge-thriller { background: #fff8e1; color: #e65100; }
        .badge-fiction  { background: #f3e5f5; color: #4a148c; }
        .badge-default  { background: #eceff1; color: #455a64; }

        /* ---- Action Buttons ---- */
        .btn-edit {
            background: #ff8f00;
            color: white;
            padding: 6px 12px;
            border-radius: 5px;
            text-decoration: none;
            font-size: 0.82rem;
            font-weight: 600;
            transition: background 0.2s;
            margin-right: 5px;
        }
        .btn-edit:hover { background: #e65100; }

        .btn-delete {
            background: #c62828;
            color: white;
            padding: 6px 12px;
            border-radius: 5px;
            text-decoration: none;
            font-size: 0.82rem;
            font-weight: 600;
            transition: background 0.2s;
            cursor: pointer;
            border: none;
        }
        .btn-delete:hover { background: #b71c1c; }

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
    <a href="/" class="active">&#128218; Books</a>
    <a href="/authors">&#9997; Authors</a>
</nav>

<div class="container">

    <!-- Success / Error Messages -->
    <c:if test="${not empty successMessage}">
        <div class="alert alert-success">${successMessage}</div>
    </c:if>
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-error">${errorMessage}</div>
    </c:if>

    <div class="toolbar" style="flex-wrap: wrap; gap: 15px;">
        <div>
            <h2>${pageTitle}</h2>
        </div>
        
        <div style="display: flex; gap: 10px; align-items: center; flex-grow: 1; justify-content: center;">
            <form action="/" method="get" style="display: flex; gap: 8px;">
                <input type="text" name="search" value="${searchKeyword}" placeholder="Search titles..." 
                       style="padding: 8px 15px; border: 1px solid #ddd; border-radius: 6px; font-size: 0.9rem; width: 250px;">
                <button type="submit" class="btn-add" style="padding: 8px 15px;">Search</button>
            </form>
            
            <form action="/" method="get" style="display: flex; gap: 8px; align-items: center;">
                <span style="font-size: 0.9rem; color: #666;">Genre:</span>
                <select name="genre" onchange="this.form.submit()" 
                        style="padding: 8px 10px; border: 1px solid #ddd; border-radius: 6px; font-size: 0.9rem;">
                    <option value="All" ${selectedGenre eq 'All' ? 'selected' : ''}>All Genres</option>
                    <option value="Fantasy" ${selectedGenre eq 'Fantasy' ? 'selected' : ''}>Fantasy</option>
                    <option value="Mystery" ${selectedGenre eq 'Mystery' ? 'selected' : ''}>Mystery</option>
                    <option value="Horror" ${selectedGenre eq 'Horror' ? 'selected' : ''}>Horror</option>
                    <option value="Sci-Fi" ${selectedGenre eq 'Sci-Fi' ? 'selected' : ''}>Sci-Fi</option>
                    <option value="Thriller" ${selectedGenre eq 'Thriller' ? 'selected' : ''}>Thriller</option>
                    <option value="Fiction" ${selectedGenre eq 'Fiction' ? 'selected' : ''}>Fiction</option>
                </select>
            </form>
            
            <c:if test="${not empty searchKeyword or (not empty selectedGenre and selectedGenre ne 'All')}">
                <a href="/" style="font-size: 0.85rem; color: #c62828; text-decoration: none; font-weight: 600;">Clear All</a>
            </c:if>
        </div>

        <a href="/add" class="btn-add">+ Add New Book</a>
    </div>

    <div class="card">
        <c:choose>
            <c:when test="${not empty books}">
                <table>
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Title</th>
                            <th>Genre</th>
                            <th>Author</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="book" items="${books}" varStatus="status">
                            <tr>
                                <td>${status.count}</td>
                                <td><strong>${book.title}</strong></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${book.genre eq 'Fantasy'}">
                                            <span class="badge badge-fantasy">${book.genre}</span>
                                        </c:when>
                                        <c:when test="${book.genre eq 'Mystery'}">
                                            <span class="badge badge-mystery">${book.genre}</span>
                                        </c:when>
                                        <c:when test="${book.genre eq 'Horror'}">
                                            <span class="badge badge-horror">${book.genre}</span>
                                        </c:when>
                                        <c:when test="${book.genre eq 'Sci-Fi'}">
                                            <span class="badge badge-sci-fi">${book.genre}</span>
                                        </c:when>
                                        <c:when test="${book.genre eq 'Thriller'}">
                                            <span class="badge badge-thriller">${book.genre}</span>
                                        </c:when>
                                        <c:when test="${book.genre eq 'Fiction'}">
                                            <span class="badge badge-fiction">${book.genre}</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge badge-default">${book.genre}</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${book.author.name}</td>
                                <td>
                                    <a href="/edit/${book.id}" class="btn-edit">&#9998; Edit</a>
                                    <a href="/delete/${book.id}"
                                       class="btn-delete"
                                       onclick="return confirm('Are you sure you want to delete this book?');">
                                        &#128465; Delete
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <div class="empty-state">
                    <p>No books found in the library.</p>
                    <a href="/add" class="btn-add">+ Add Your First Book</a>
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
