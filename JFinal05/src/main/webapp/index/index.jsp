<html>
<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<head>
    <title>Title</title>
</head>
<body>
<!-- 上传 -->
    <input type="file" name="uploadfile" id="uploadfile" multiple="multiple"><br>
    监测点id：<input type="text" id="monPointId"><br>
    描述：<input type="text" id="description"><br>
    拍摄地点：<input type="text" id="location"><br>
    <button id="upload" type="button" onclick="return false;">上传</button>
</body>
<script>
    $(document).ready(function() {
        $('#upload').click(function() {
            upload();
        });
    });
    function upload() {
        var monPointId=$("#monPointId").val();
        var description=$("#description").val();
        var location=$("#location").val();
        $.ajaxFileUpload({
            url : '/upload?monPointId='+monPointId+'&description='+description+'&location='+location,   //提交的路径
            type: 'post',
            secureuri : false, // 是否启用安全提交，默认为false
            fileElementId : 'uploadfile', // file控件id
            dataType : 'json',
            data:{
                'monPointId' : monPointId,
                'description' : monPointId,
                'location' : monPointId,
            },
            success : function(data, status) {
                console.log("aa");
                console.log(data);
                console.log(status);
            },
            error : function(data, status) {
                alert("上传失败");
            }
        });
    }
</script>
</html>
