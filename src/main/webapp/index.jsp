<%--
  Created by IntelliJ IDEA.
  User: huaian
  Date: 2021/1/15
  Time: 21:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>权限系统</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/test" method="post">
        <label>姓名：
            <input name="name">
        </label><br>
        <input type="submit" value="提交">
    </form>
</body>
</html>
