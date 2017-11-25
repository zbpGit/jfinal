<html>
<body>
<h2>Hello World!</h2>
</body>

<form action="#(ctx)/File/doAdd" method="post">
    <p>账号:<input type="text" name="user.name"></p>
    <p>密码:<input type="text" name="user.password"></p>
    #@yemianFunction()
    <p>提交:<input type="submit" name="提交"></p>
</form>
#--导入函数--#
#--#include("common.html")--#
<hr>
#--函数--#
#@templatFunction()
<hr>
#@templateFunction()
<hr>
#--传参--#
#@template2Function("hello")
<hr>
#--静态常量--#
#(ID)
#--静态方法--#
#(name())
<hr>
#--任意--#
#(all.test)
#(all.test1)
#(all.way())
#(all.way1())
</html>
