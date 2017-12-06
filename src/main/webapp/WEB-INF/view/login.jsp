<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Material Design Login Form</title>
</head>
<body>
<%@include file="/WEB-INF/view/header.jsp" %>

<form class="section" action="/" method="POST">
    <div><input name="command" value="login" type="hidden"/></div>
    <label for=""><fmt:message key="login" bundle="${bundle}" /></label>
    <div class="input-container">
        <div class="input-block input-from">
            <input type="text" id="email" name="email" required/>
        </div>
        </br>
        <div class="input-block input-to">
            <input type="password" id="password" name="password" required/>
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
<li><a href="/?command=registrationPage">Sign up</a></li>

</body>
</html>
