<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
</head>
<body>
<%@include file="/WEB-INF/view/i18n.jsp" %>
<c:if test="${not empty token}">
    <li><a href="/?command=showBasket">Basket</a></li>
    <li><a href="/?command=admin/adminPage">Admin page</a></li>
</c:if>
<form action="/" method="POST">
    <div><input name="command" value="setLang" type="hidden"/></div>
    <div><input name="page" value="${page}" type="hidden"/></div>
    <input type="radio" id="lang" name="lang" value="en" checked="checked"/>EN
    <input type="radio" id="lang" name="lang" value="ua"/>UA
    <button class="change-button" type="submit">
        <fmt:message key="changeLang" bundle="${bundle}"/>
    </button>
</form>
</body>
