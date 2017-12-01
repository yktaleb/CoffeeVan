<!DOCTYPE html>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<%@include file="/WEB-INF/view/header.jsp" %>

<div><input name="basketFields" value="${basketFields}" type="hidden"/></div>
<div><input name="totalPrice" value="${totalPrice}" type="hidden"/></div>
<c:forEach items="${basketFields}" var="field">
    <tr>
        <td>${field.beverage.name}; Amount:${field.amount}; Price:${field.price}</td>
        </br>
    </tr>
</c:forEach>
Total price: ${totalPrice}
</body>
</html>
