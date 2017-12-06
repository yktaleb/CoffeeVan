<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/header.jsp" %>

<div><input name="basketFields" value="${basketFields}" type="hidden"/></div>
<div><input name="totalPrice" value="${totalPrice}" type="hidden"/></div>


<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <c:forEach items="${basketFields}" var="field">
                <tr>
                    <td>${field.beverage.name}; <fmt:message key="amount" bundle="${bundle}"/>:${field.amount};
                        <fmt:message key="price" bundle="${bundle}"/>:${field.price}</td>
                    </br>
                </tr>
            </c:forEach>
            <fmt:message key="totalPrice" bundle="${bundle}"/>: ${totalPrice}
            <form>

                <form class="section" action="/" method="POST">
                    <div><input name="command" value="createOrder" type="hidden"/></div>
                    <div class="input-container">
                        <div class="input-block input-from">
                            <input type="text" id="address" name="address" required/>
                        </div>
                        </br>
                    </div>
                    <div class="input-search">
                        <div class="input-block">
                            <button class="button" type="submit">
                                <fmt:message key="createOrder" bundle="${bundle}"/>
                            </button>
                        </div>
                    </div>
                </form>
            </form>
        </div>
    </div>
</div>
</body>
</html>
