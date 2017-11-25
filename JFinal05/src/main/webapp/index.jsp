<html>
<body>
<h2>请输入信息</h2>
    <form action="#(ctx)/save" method="post" enctype="multipart/form-data">
    标题:<input type="text" name="title"><br>
    图片上传：<input type="file" multiple="multiple" id="fl" name="print"><img src=""  width="175" height="47" /><br>
    简单介绍：<textarea rows="10" cols="100" name="message"></textarea><br>
    <input type="submit" value="添加">
</form>
</body>
</html>
