<!DOCTYPE html>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<%@include file="/WEB-INF/view/header.jsp" %>

<div><input name="freeVans" value="${freeVans}" type="hidden"/></div>
<div><input name="freeVans" value="${busyVans}" type="hidden"/></div>
<div><input name="allOrders" value="${allOrders}" type="hidden"/></div>
<div><input name="exception" value="${exception}" type="hidden"/></div>
<h1 style="color: red">${exception}</h1>
Free vans:
</br>
<c:forEach items="${freeVans}" var="van">
    <tr>
        <td>Id:${van.id}; Name:${van.name}(Carrying Capacity : ${van.carryingCapacity}, Max volume : ${van.maxVolume} )</td>
        </br>
    </tr>
</c:forEach>
__________________________________________________________
</br>

</br>
</br>
Busy vans:
</br>
<c:forEach items="${busyVans}" var="van">
    <tr>
        <td>Id:${van.id}; Name:${van.name}(Carrying Capacity : ${van.carryingCapacity}, Max volume : ${van.maxVolume} )</td>
        </br>
        <form class="section" action="/" method="POST">
            <div><input name="command" value="makeVanFree" type="hidden"/></div>
            <div><input name="vanId" value="${van.id}" type="hidden"/></div>
            <div class="input-search">
                <div class="input-block">
                    <label for="">
                        <button class="button" type="submit">
                            To free
                        </button>
                    </label>
                </div>
            </div>
        </form>
        </br>
    </tr>
</c:forEach>
__________________________________________________________
</br>

<c:forEach items="${allOrders}" var="frontOrder">
    </br>
    </br>
    Address:${frontOrder.order.address}
    </br>
    User:${frontOrder.order.user.lastName} ${frontOrder.order.user.firstName}
    Status:${frontOrder.order.status.name}
    </br>
    </br>
    <c:forEach items="${frontOrder.beverageOrders}" var="beverageOrder">
        Beverage name: ${beverageOrder.beverage.name}; Amount: ${beverageOrder.amount}; <%--Price:${beverageOrder.beverage.price}; Weight:${beverageOrder.beverage.weight}; Volume:${beverageOrder.beverage.volume}--%>
        </br>
    </c:forEach>
    Total price : ${frontOrder.totalPrice}
    Total weight : ${frontOrder.totalWeight}
    Total volume : ${frontOrder.totalVolume}
    </br>
    <c:if test="${frontOrder.order.status.name == \"IN_PROCESSING\"}">
        <form class="section" action="/" method="POST">
            <div><input name="command" value="setOrderVan" type="hidden"/></div>
            <div><input name="orderId" value="${frontOrder.order.id}" type="hidden"/></div>
            Van id:
            <input type="text" id="vanId" name="vanId" required/>
            <div class="input-search">
                <div class="input-block">
                    <label for="">
                        <button class="button" type="submit">
                            Set van
                        </button>
                    </label>
                </div>
            </div>
        </form>
    </c:if>
    __________________________________________________________

</c:forEach>
</body>
</html>

