<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Material Design Login Form</title>
</head>
<body>

    <form class="section" action="/" method="POST">
        <div><input name="command" value="registration" type="hidden"/></div>
        <div class="input-container">
            <div class="input-block input-from">
                First name:
                <input type="text" id="firstName" name="firstName" required/>
            </div>
            <div class="input-block input-to">
                Last name:
                <input type="text" id="lastName" name="lastName" required/>
            </div>
        </div>
        <div class="input-data">
            <div class="input-block">
                Phone number:
                <input type="text" id="phoneNumber" name="phoneNumber" required/>
            </div>
        </div>
        <div class="input-data">
            <div class="input-block">
                Email:
                <input type="text" id="email" name="email" required/>
            </div>
        </div>
        <div class="input-data">
            <div class="input-block">
                Password
                <input type="password" id="password" name="password" required/>
            </div>
        </div>
        <div class="input-search">
            <div class="input-block">
                <label for="">
                    <button class="button" type="submit">
                        Sign up
                    </button>
                </label>
            </div>
        </div>
    </form>
    </br>

</body>
</html>
