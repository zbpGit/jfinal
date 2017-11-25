<html>
<head>
    <title>设备管理</title>
</head>
<body>
<table border="1">
    <tbody>
    #(records)

    <tr>
        <td style="text-align:left;">#(records.get("uid"))</td>
        <td style="text-align:left;">#(records.get("password"))</td>
        <td style="text-align:left;">#(records.get("name"))</td>
        <td style="text-align:left;">#(records.get("id"))</td>
    </tr>
    #for(x : records)
    <tr>
        <td style="text-align:left;">#(x.uid)</td>
        <td style="text-align:left;">#(x.password)</td>
        <td style="text-align:left;">#(x.name)</td>
        <td style="text-align:left;">#(x.id)</td>
        <td style="text-align:left;">
            &nbsp;&nbsp;<a href="/Data/delete/#(x.id)">删除</a>
            &nbsp;&nbsp;<a href="/Data/Ud/#(x.id)-#(x.name)">修改</a>
        </td>
    </tr>
    #end
    </tbody>
</table>

</body>
</html>