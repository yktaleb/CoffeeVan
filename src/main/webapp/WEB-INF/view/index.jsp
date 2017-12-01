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
        <td>${beverage.name}( ${beverage.type.name}, ${beverage.state.name}, ${beverage.quality.name} ).
            Volume: ${beverage.volume}; Weight: ${beverage.weight}; Price: ${beverage.price}</td>
        <form class="section" action="/" method="POST">
            <div><input name="command" value="addToBasket" type="hidden"/></div>
            <div><input name="id" value="${beverage.id}" type="hidden"/></div>
            <div class="input-block input-from">
                Amount:
                <input type="text" id="amount" name="amount" required/>
            </div>
            <div class="input-search">
                <div class="input-block">
                    <label for="">
                        <button class="button" type="submit">
                            Add
                        </button>
                    </label>
                </div>
            </div>
        </form>
        </br>
    </tr>
</c:forEach>
</body>
</html>
