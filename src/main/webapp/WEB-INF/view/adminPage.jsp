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

<c:forEach items="${allOrders}" var="frontOrder">
    <tr>
        <td>Address:${frontOrder.order.address};
            User:${frontOrder.order.user.lastName} ${frontOrder.order.user.firstName}</td>
            <c:forEach items="${frontOrder.beverageOrders}" var="beverageOrder">
                <tr>
                    <td>Beverage name: ${beverageOrder.beverage.name}; Amount: ${beverageOrder.amount}; <%--Price:${beverageOrder.beverage.price}; Weight:${beverageOrder.beverage.weight}; Volume:${beverageOrder.beverage.volume}--%></td>
                    </br>
                 </tr>
            </c:forEach>
            Total price : ${frontOrder.totalPrice}
            Total weight : ${frontOrder.totalWeight}
            Total volume : ${frontOrder.totalVolume}
        </br>
    </tr>
</c:forEach>
</body>
</html>
