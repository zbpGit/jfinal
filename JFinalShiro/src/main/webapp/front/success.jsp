<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
success
<%--判断角色--%>
<shiro:hasAnyRoles name="admin">
   欢迎admin<shiro:principal/>
</shiro:hasAnyRoles>
<%--判断权限--%>
<shiro:hasAnyRoles name="teacher">
    欢迎teacher<shiro:principal/>
</shiro:hasAnyRoles>

<shiro:user></shiro:user>
</body>
</html>
