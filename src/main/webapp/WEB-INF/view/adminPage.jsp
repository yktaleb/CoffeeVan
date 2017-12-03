<!DOCTYPE html>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<%@include file="/WEB-INF/view/header.jsp" %>

<div><input name="freeVans" value="${freeVans}" type="hidden"/></div>
<div><input name="allOrders" value="${allOrders}" type="hidden"/></div>
<c:forEach items="${freeVans}" var="van">
    <tr>
        <td>${van.name}(Carrying Capacity : ${van.carryingCapacity}, Max volume : ${van.maxVolume} )</td>
        </br>
    </tr>
</c:forEach>

<c:forEach items="${allOrders}" var="order">
    <tr>
        <td>Address:${order.address}; User: : ${order.user.id}</td>
        </br>
    </tr>
</c:forEach>
</body>
</html>
