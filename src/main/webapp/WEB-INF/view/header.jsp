<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" ; charset="UTF-8"/>
    <title>CoffeeVan</title>
    <link href="<c:url value='/resources/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css">
    <style>
        li > a {
            font-size : 18px;
        }
        hr{
            height: 1px;
            border: 0;
            background-color: black;
        }

        .wrapper {
            display: flex;
            align-items: center;
        }
    </style>
</head>
<body>
<%@include file="/WEB-INF/view/i18n.jsp" %>
<header>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="/">CoffeeVan</a>
            </div>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <c:if test="${not empty token}">
                        <li><a href="/"><fmt:message key="mainPage" bundle="${bundle}"/></a></li>
                        <c:if test="${not empty admin}">
                            <li><a href="/?command=admin/adminPage"><fmt:message key="adminPage" bundle="${bundle}"/></a></li>
                        </c:if>
                    </c:if>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li class="wrapper">
                        <form action="/" method="POST" style="margin-top: 7px; margin-bottom: 0">
                            <div><input name="command" value="setLang" type="hidden"/></div>
                            <div><input name="page" value="${page}" type="hidden"/></div>
                            <input type="radio" id="lang" name="lang" value="en" checked="checked"/>EN
                            <input type="radio" id="lang" name="lang" value="ua"/>UA
                            <button class="btn" type="submit">
                                <fmt:message key="changeLang" bundle="${bundle}"/>
                            </button>
                        </form>
                    </li>
                    <c:if test="${not empty token}">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                               aria-expanded="false">Dropdown <span class="caret"></span></a>
                            <ul class="dropdown-menu">

                                <li class="active"><a href="/?command=showBasket"><fmt:message key="basket" bundle="${bundle}"/></a></li>
                                <li><a href="/?command=logout"><fmt:message key="logout" bundle="${bundle}"/></a></li>
                            </ul>
                        </li>
                    </c:if>
                </ul>
            </div>
        </div>
    </nav>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src='<c:url value="/resources/js/bootstrap.min.js"/>'></script>
</header>