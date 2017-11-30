<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Material Design Login Form</title>
</head>
<body>
<form id="login-form">

    <form class="section" action = "/" method="POST">
        <div><input name="command" value="login" type="hidden"/></div>
        <div class="input-container">
            <div class="input-block input-from">
                <input type="text" id="email" name="email" required />
            </div>
            </br>
            <div class="input-block input-to">
                <input type="password" id="password" name="password" required />
            </div>
        </div>
        <div class="input-search">
            <div class="input-block">
                <label for="">
                    <button class="button" type="submit">
                        Log In
                    </button>
                </label>
            </div>
        </div>
    </form>
</form>
</body>
</html>
