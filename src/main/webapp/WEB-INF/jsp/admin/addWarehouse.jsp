<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search products</title>
</head>
<body>
<%@include file="../header.jsp" %>
<div style="height:80%" align="center">
    <br>
    <h4>Just Admins</h4>
    <h5>Here You Can Add WareHouse</h5>

    <c:if test="${searchFiledIsEmpty=='true'}" >
        <h3 style="color: red"> You Should at least fill one filed for search products</h3>
    </c:if>
</div>
<%@include file="../footer.jsp" %>
</body>
</html>
