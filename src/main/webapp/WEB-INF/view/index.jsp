<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/header.jsp" %>

<div><input name="allBeverage" value="${allBeverage}" type="hidden"/></div>

<div class="container">
    <form name="input" action="/" method="POST">
        <input type="checkbox" name="sort" value="price"><fmt:message key="sortByPrice" bundle="${bundle}"/><br>
        <input type="checkbox" name="sort" value="quality"><fmt:message key="sortByQuality" bundle="${bundle}"/>
        <input type="submit" value="<fmt:message key="sort" bundle="${bundle}"/>">
    </form>
    </br>
    </br>
    <div class="row">
        <div class="col-sm-12">
            <c:forEach items="${allBeverage}" var="beverage">
                <tr>
                    <td>${beverage.name}( ${beverage.type.name}, ${beverage.state.name}, ${beverage.quality.name} ).
                        <fmt:message key="volume" bundle="${bundle}"/>: ${beverage.volume}; <fmt:message key="weight"
                                                                                                         bundle="${bundle}"/>: ${beverage.weight};
                        <fmt:message key="price" bundle="${bundle}"/>: ${beverage.price}</td>
                    <form class="section" action="/" method="POST">
                        <div><input name="command" value="addToBasket" type="hidden"/></div>
                        <div><input name="id" value="${beverage.id}" type="hidden"/></div>
                        <div class="input-block input-from">
                            <fmt:message key="amount" bundle="${bundle}"/>
                            <input type="text" id="amount" name="amount" required/>
                        </div>
                        <div class="input-search">
                            <div class="input-block">
                                <button class="button" type="submit">
                                    <fmt:message key="add" bundle="${bundle}"/>
                                </button>
                            </div>
                        </div>
                    </form>
                    </br>
                </tr>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
