<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/header.jsp" %>

<div><input name="freeVans" value="${freeVans}" type="hidden"/></div>
<div><input name="freeVans" value="${busyVans}" type="hidden"/></div>
<div><input name="allOrders" value="${allOrders}" type="hidden"/></div>
<div><input name="exception" value="${exception}" type="hidden"/></div>

<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <h4 style="color: red">${exception}</h4>
            <fmt:message key="freeVans" bundle="${bundle}"/>:
            </br>
            <c:forEach items="${freeVans}" var="van">
                <tr>
                    <td>Id:${van.id}; <fmt:message key="name" bundle="${bundle}"/>:${van.name}(<fmt:message
                            key="carryingCapacity" bundle="${bundle}"/>: ${van.carryingCapacity}, <fmt:message
                            key="maxVolume" bundle="${bundle}"/>
                        : ${van.maxVolume} )
                    </td>
                    </br>
                </tr>
            </c:forEach>
            <hr/>
            <fmt:message key="busyVans" bundle="${bundle}"/>:
            </br>
            <c:forEach items="${busyVans}" var="van">
                <tr>
                    <td>Id:${van.id}; <fmt:message key="name" bundle="${bundle}"/>:${van.name}(<fmt:message
                            key="carryingCapacity" bundle="${bundle}"/>: ${van.carryingCapacity}, <fmt:message
                            key="maxVolume" bundle="${bundle}"/>
                        : ${van.maxVolume} )
                    </td>
                    </br>
                    <form class="section" action="/" method="POST">
                        <div><input name="command" value="makeVanFree" type="hidden"/></div>
                        <div><input name="vanId" value="${van.id}" type="hidden"/></div>
                        <div class="input-search">
                            <div class="input-block">
                                <button class="button" type="submit">
                                    <fmt:message key="toFree" bundle="${bundle}"/>
                                </button>
                            </div>
                        </div>
                    </form>
                </tr>
            </c:forEach>
            <hr/>
            </br>

            <c:forEach items="${allOrders}" var="frontOrder">
                <fmt:message key="address" bundle="${bundle}"/>:${frontOrder.order.address}
                </br>
                <fmt:message key="user"
                             bundle="${bundle}"/>:${frontOrder.order.user.lastName} ${frontOrder.order.user.firstName}
                <fmt:message key="status" bundle="${bundle}"/>:${frontOrder.order.status.name}
                </br>
                </br>
                <c:forEach items="${frontOrder.beverageOrders}" var="beverageOrder">
                    <fmt:message key="beverageName" bundle="${bundle}"/>: ${beverageOrder.beverage.name}; <fmt:message
                        key="amount"
                        bundle="${bundle}"/>: ${beverageOrder.amount}; <%--Price:${beverageOrder.beverage.price}; Weight:${beverageOrder.beverage.weight}; Volume:${beverageOrder.beverage.volume}--%>
                    </br>
                </c:forEach>
                <fmt:message key="totalPrice" bundle="${bundle}"/> : ${frontOrder.totalPrice}
                <fmt:message key="totalWeight" bundle="${bundle}"/> : ${frontOrder.totalWeight}
                <fmt:message key="totalVolume" bundle="${bundle}"/> : ${frontOrder.totalVolume}
                </br>
                <c:if test="${frontOrder.order.status.name == \"IN_PROCESSING\"}">
                    <form class="section" action="/" method="POST">
                        <div><input name="command" value="setOrderVan" type="hidden"/></div>
                        <div><input name="orderId" value="${frontOrder.order.id}" type="hidden"/></div>
                        <fmt:message key="van" bundle="${bundle}"/> id:
                        <input type="text" id="vanId" name="vanId" required/>
                        <div class="input-search">
                            <div class="input-block">
                                <button class="button" type="submit">
                                    <fmt:message key="setVan" bundle="${bundle}"/>
                                </button>
                            </div>
                        </div>
                    </form>
                </c:if>
                <hr/>

            </c:forEach>

            <div>
                <ul class="pagination">
                    <c:forEach items="${numberOfPages}" var="page">
                        <li><a href="/?command=adminPage&page=${page}">${page + 1}</a></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>

