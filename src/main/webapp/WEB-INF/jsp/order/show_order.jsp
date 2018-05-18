<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Show Order</title>
</head>
<body>
<%@include file="../header.jsp" %>
<div class="container"  style="height:80%">
    <div class="row">
        <div class="col-md-9">
            <div>
                <ol class="breadcrumb">
                    <li><h4>Your Orders</h4></li>
                </ol>
            </div>
            <div class="row">
                <c:forEach var="product" items="${productList}">
                    <div class="col-md-6 text-center col-sm-6 col-xs-6">
                        <div class="thumbnail product-box">
                            <img src="${pageContext.request.contextPath}/product_image?product_id=${product.productId}" alt="${product.productName}" style="height: 100px">
                            <div class="caption">
                                <h5>${product.productName}</h5>
                                <p>Price : <strong>$ ${product.price}</strong></p>
                                <p>${product.category.categoryName} </p>
                                <p><a href="${pageContext.servletContext.contextPath}/order/cart?action=delete&product_id=${product.productId}&redirect=none" class="btn btn-success" role="button">Delete From Cart</a></p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div>
                <ol class="breadcrumb">
                    <li><h3>Total Price:$${totalPrice}</h3></li>
                </ol>
            </div>
        </div>
    </div>
</div>
<%@include file="../footer.jsp" %>
</body>
</html>
