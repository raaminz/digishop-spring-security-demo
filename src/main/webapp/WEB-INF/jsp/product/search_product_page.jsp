<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search products</title>
</head>
<body>
<%@include file="../header.jsp" %>
<div style="height:80%" align="center">
    <br>
    <h5>You Should at least fill one filed for search products </h5>
    <form action="search_product" method="get">
        <div class="form-group">
            <select name="category_id" class="form-control" style="width: 220px;">
                <option value="none">Select Product Category</option>
                <c:forEach items="${categoryList}" var="category">
                    <option value=${category.categoryId}>${category.categoryName}</option>
                </c:forEach>
                <option value="none">None</option>
            </select>
            <br>
            <input type="text" name="product_name" placeholder=" Enter Product Name" class="form-control" style="width: 220px;">
            <br>
            <input type="hidden" name="page_number" value="1">
            <button type="submit" class="btn btn-primary" dir="ltr">Search</button>
        </div>
    </form>
    <c:if test="${searchFiledIsEmpty=='true'}" >
        <h3 style="color: red"> You Should at least fill one filed for search products</h3>
    </c:if>
</div>
<%@include file="../footer.jsp" %>
</body>
</html>
