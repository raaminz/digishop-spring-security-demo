<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<%@include file="../header.jsp" %>
<div style="height:80%" align="center">
    <br>
    <form action="${pageContext.servletContext.contextPath}/login" method="post">
        <div class="form-group">
            <br><br>
            <h2>Login to purchase products</h2>
            <br>
            <input type="text" name="username" placeholder="username" class="form-control" style="width: 200px;">
            <br>
            <input type="password" name="password" placeholder="password" class="form-control" style="width: 200px;">
            <br>
            <button type="submit" class="btn btn-primary" dir="ltr">Login To DIGIShop</button>
            <c:if test="${param.error ne null}">
                <br>
                <span style="color:red">User Name Or Password is not correct</span>
            </c:if>
            <c:if test="${param.logout ne null}">
                <br>
                <span style="color:green">You were logged out successfully</span>
            </c:if>
        </div>
    </form>
</div>
<%@include file="../footer.jsp" %>
</body>
</html>
