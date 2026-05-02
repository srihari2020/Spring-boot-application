<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${pageTitle} - Library Management System</title>
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
        .container { max-width: 600px; margin: 50px auto; padding: 0 20px; }

        /* ---- Form Card ---- */
        .card {
            background: white;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.08);
            padding: 36px 40px;
        }
        .card h2 {
            color: #1a237e;
            margin-bottom: 28px;
            font-size: 1.35rem;
        }

        /* ---- Alert Messages ---- */
        .alert {
            padding: 12px 18px;
            border-radius: 6px;
            margin-bottom: 20px;
            font-size: 0.95rem;
        }
        .alert-error { background: #ffebee; color: #c62828; border-left: 4px solid #ef5350; }

        /* ---- Form ---- */
        .form-group { margin-bottom: 22px; }
        label {
            display: block;
            font-size: 0.88rem;
            font-weight: 600;
            color: #555;
            margin-bottom: 6px;
            text-transform: uppercase;
            letter-spacing: 0.4px;
        }
        input[type="text"] {
            width: 100%;
            padding: 11px 14px;
            border: 1.5px solid #c5cae9;
            border-radius: 6px;
            font-size: 0.97rem;
            transition: border-color 0.2s;
            outline: none;
        }
        input[type="text"]:focus { border-color: #3949ab; }

        /* ---- Buttons ---- */
        .form-actions {
            display: flex;
            gap: 12px;
            margin-top: 28px;
        }
        .btn-submit {
            background: #1a237e;
            color: white;
            padding: 11px 28px;
            border: none;
            border-radius: 6px;
            font-size: 0.97rem;
            font-weight: 600;
            cursor: pointer;
            transition: background 0.2s;
        }
        .btn-submit:hover { background: #283593; }
        .btn-cancel {
            background: #eceff1;
            color: #546e7a;
            padding: 11px 22px;
            border: none;
            border-radius: 6px;
            font-size: 0.97rem;
            font-weight: 600;
            text-decoration: none;
            cursor: pointer;
            transition: background 0.2s;
        }
        .btn-cancel:hover { background: #cfd8dc; }

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

    <c:if test="${not empty errorMessage}">
        <div class="alert alert-error">${errorMessage}</div>
    </c:if>

    <div class="card">
        <h2>${pageTitle}</h2>

        <form action="${formAction}" method="post">
            <div class="form-group">
                <label for="name">Author Name</label>
                <input type="text" id="name" name="name"
                       value="${author.name}"
                       placeholder="Enter author's full name"
                       required />
            </div>

            <div class="form-actions">
                <button type="submit" class="btn-submit">${buttonLabel}</button>
                <a href="/authors" class="btn-cancel">Cancel</a>
            </div>
        </form>
    </div>

</div>

<footer>
    &copy; 2024 Library Management System &mdash; Built with Spring Boot &amp; JSP
</footer>

</body>
</html>
