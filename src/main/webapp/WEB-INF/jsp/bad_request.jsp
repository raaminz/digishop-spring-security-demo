<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bad Request</title>
</head>
<body>
<%@include file="header.jsp" %>
<div style="height:80%" align="center">
    <br><br><br>
    <h1>Bad Request</h1>
    <h3>wrong value send to server. please try again</h3>
    <br><br><br>
    <a href="${pageContext.servletContext.contextPath}/product/top_product" style="color: red">
        <u>Go To Top Product Page</u>
    </a>
</div>
<%@include file="footer.jsp" %>
</body>
</html>
