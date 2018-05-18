<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link href="${pageContext.request.contextPath}/static/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/css/font-awesome.min.css" rel="stylesheet"/>
<link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
<link href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.servletContext.contextPath}/product/top_product">
                <strong>DIGI</strong>Shop

            </a>
            <div class="navbar-brand">
            <sec:authorize access="isAuthenticated()">Welcome ${sessionScope.currentUser.username} </sec:authorize>
            </div>

        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="${pageContext.servletContext.contextPath}/product/top_product">Top Products</a></li>
                <li><a href="${pageContext.servletContext.contextPath}/product/search_product_page">Search Products</a>
                </li>
                <li><a href="${pageContext.servletContext.contextPath}/order/show_order">Show Orders</a></li>
                <sec:authorize access="isAuthenticated()">
                    <li><a href="${pageContext.servletContext.contextPath}/logout">Logout</a></li>
                </sec:authorize>

            </ul>
        </div>
    </div>
</nav>