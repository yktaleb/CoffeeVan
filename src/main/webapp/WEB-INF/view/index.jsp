<!DOCTYPE html>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<%@include file="/WEB-INF/view/header.jsp" %>

<div><input name="allBeverage" value="${allBeverage}" type="hidden"/></div>
<c:forEach items="${allBeverage}" var="beverage">
    <tr>
        <td>${beverage.name}</td>
    </tr>
</c:forEach>
</body>
</html>
