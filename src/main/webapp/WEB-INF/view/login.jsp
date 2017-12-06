<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/header.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <form action="/" method="POST">
                <div><input name="command" value="login" type="hidden"/></div>
                <div class="form-group">
                    <label for="email"><fmt:message key="email" bundle="${bundle}" /></label>
                    <input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp" placeholder="Enter email" required>
                </div>
                <div class="form-group">
                    <label for="password"><fmt:message key="password" bundle="${bundle}" /></label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
                </div>
                <button type="submit" class="btn btn-primary"><fmt:message key="signIn" bundle="${bundle}" /></button>
                </br>
                <a href="/?command=registrationPage"><fmt:message key="registration" bundle="${bundle}" /></a>
            </form>
        </div>
    </div>
</div>
</body>
</html>
