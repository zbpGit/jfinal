<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/6
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
#for(x : user)
<tr>
    <td style="text-align:left;">#(x.id)</td>
    <td style="text-align:left;">#(x.name)</td>
    <td style="text-align:left;">#(x.password)</td>
</tr>
#end
</body>
</html>
