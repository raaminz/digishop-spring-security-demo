<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DIGI Shop</title>
</head>
<body>
<%@include file="../header.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-9">
            <div>
                <ol class="breadcrumb">
                    <li><h4>Top Products</h4></li>
                </ol>
            </div>
            <div class="row">
                <c:forEach var="product" items="${productList}">
                    <div class="col-md-4 text-center col-sm-6 col-xs-6">
                        <div class="thumbnail product-box">
                            <img src="${pageContext.request.contextPath}/product_image?product_id=${product.productId}" alt="${product.productName}" style="height: 100px">
                            <div class="caption">
                                <h5>${product.productName}</h5>
                                <p>Price : <strong>$ ${product.price}</strong></p>
                                <p>${product.category.categoryName} </p>
                                <p><a href="${pageContext.servletContext.contextPath}/order/cart?action=add&product_id=${product.productId}&redirect=top_product" class="btn btn-success" role="button">Add To Cart</a></p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<%@include file="../footer.jsp" %>
</body>
</html>
