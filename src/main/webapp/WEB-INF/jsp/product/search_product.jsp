<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>results</title>
</head>
<body>
<%@include file="../header.jsp" %>
<div class="container" style="height:80%">
    <div class="row">
        <div class="col-md-9">
            <div>
                <ol class="breadcrumb">
                    <li><h4>Results <c:if test="${pageContext.request.getParameter('product_name')!=''}">
                        for "${pageContext.request.getParameter('product_name')}"
                    </c:if></h4></li>
                </ol>
            </div>
            <div class="row">
                <c:forEach var="product" items="${productList}">
                    <div class="col-md-6 text-center col-sm-6 col-xs-6">
                        <div class="thumbnail product-box">
                            <img src="${pageContext.request.contextPath}/product_image?product_id=${product.productId}"
                                 alt="${product.productName}" style="height: 100px">
                            <div class="caption">
                                <h4>${product.productName}</h4>
                                <p>Price : <strong>$ ${product.price}</strong></p>
                                <p>${product.category.categoryName}</p>
                                <p>
                                    <a href="${pageContext.servletContext.contextPath}/order/cart?action=add&product_id=${product.productId}&redirect=search_product&category_id=${pageContext.request.getParameter('category_id')}&product_name=${pageContext.request.getParameter('product_name')}&page_number=${pageContext.request.getParameter('page_number')}"
                                       class="btn btn-success" role="button">Add To Cart</a></p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <a href="${pageContext.servletContext.contextPath}/product/top_product">Back To Top Product</a>
            <br>
            <br>
            <c:if test="${productCount!=1}">
                <div class="row">
                    <ul class="pagination alg-right-pad">
                        <c:forEach begin="1" end="${productCount}" varStatus="loop">
                            <li>
                                <a href="${pageContext.servletContext.contextPath}/product/search_product?category_id=${pageContext.request.getParameter('category_id')}&product_name=${pageContext.request.getParameter('product_name')}&page_number=${loop.index}">
                                        ${loop.index}
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>
        </div>
    </div>
</div>
<%@include file="../footer.jsp" %>
</body>
</html>
