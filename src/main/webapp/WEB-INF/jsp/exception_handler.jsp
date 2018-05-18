<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<%@include file="header.jsp" %>
<div style="height:80%" align="center">
    <br><br><br>
    <h3>Exception Details</h3>
    Servlet Name: ${requestScope.get("javax.servlet.error.servlet_name")}<br>
    Status Code:${pageContext.response.status}<br>
    Exception Name: ${exception.getClass().getName()}<br>
    Requested URL: ${URL}<br>
    Exception Message: ${exception.getMessage()}<br>
    <br><br>
    <h5>This message automatically send to admin of site</h5><br>
    <a href="${pageContext.servletContext.contextPath}/product/top_product" style="color: red"><u>Go To Top Product
        Page</u></a>
</div>
<%@include file="footer.jsp" %>
</body>
</html>
