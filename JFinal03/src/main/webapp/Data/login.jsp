<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="#(ctx)/Sess/doValidator" method="post">
        <p>校验字符串<input name="str" value="#(str??'')">#(errorMsgKey??'')</p>
        <p><input value="提交" type="submit"></p>
    </form>
</body>
</html>
