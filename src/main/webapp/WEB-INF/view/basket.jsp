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
<form>

    <form class="section" action = "/" method="POST">
        <div><input name="command" value="createOrder" type="hidden"/></div>
        <div class="input-container">
            <div class="input-block input-from">
                <input type="text" id="address" name="address" required />
            </div>
            </br>
        </div>
        <div class="input-search">
            <div class="input-block">
                <label for="">
                    <button class="button" type="submit">
                        Create Order
                    </button>
                </label>
            </div>
        </div>
    </form>
</form>
</body>
</html>
