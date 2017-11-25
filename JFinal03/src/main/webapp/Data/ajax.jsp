<html>
<head>
    <title>Title</title>
</head>
<script type="text/javascript" src="/js/jquery-1.12.4.js"></script>
<script type="text/javascript">
    function om() {
        var i1 = $("#i1").val();
        var j1 = $("#j1").val();
        alert(i1);
        alert(j1);
        $.ajax({
            type:'post',
            url : '#(ctx)/Sess/ajax',
            data:{
                name:i1,
                password:j1
            },
            success:function(data){
                alert(data.name+"------"+data.password);
                $("#i2").val(data.name);
                $("#j2").val(data.password);
            }
        });
    }

    function saveRequestFrom(){

        var form = document.getElementById("form1");

        form.action="#(ctx)/File/update";

        form.submit();

    }
</script>
<body>
    <form>
        用户名：<input type="text" id="i1" name="name"><br>
        密码:<input type="text" id="j1" name="password"><br>
        <input type="button" value="提交" onclick="om()">
    </form>
<hr>
    <form>
        用户名:<input type="text" id="i2" name=""><br>
        密码：<input type="text" id="j2" name=""><br>
    </form>
<hr>
    <form id="form1" method="post" enctype="multipart/form-data" >

        　　<input type="file" id="upload" name="upload" />

        　　<input type = "button"  value = "保存" onclick="saveRequestFrom()" />

    </form>

</body>
</html>
