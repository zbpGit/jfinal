<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>2017年51JOB网Java人才招聘情况</title>
</head>
<body>
	<input type="text" style="width:500px" id="url"/><button id="btn">点击统计</button>
	<div id="chart"></div>
	<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="js/chart/fusioncharts.js"></script>
	<script type="text/javascript" src="js/chart/fusioncharts.theme.fint.js"></script>
	<script type="text/javascript" src="js/chart/jquery.chart.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#btn").click(function(){
				var url = $("#url").val();
				$.ajax({
					type:"post",
					url:"/getHtml",
					data:{url:url},
					success:function(data){
						console.log(data);
						var objs = data;
						var html ="";
						for(var tmp in objs){
							html+="<set label='"+tmp+"' value='"+objs[tmp]+"'/>"
						}
						$.jameChart({
							target:"chart",
							type:"pie3d",
							data:"<chart caption='2017年51job Java行业招聘月薪范围统计报表' yaxisname='招聘职位个数' yaxismaxvalue='25000' theme='fint'>"+
							html+
						"</chart>"
						});
					}
				});
			});
			
		});
	</script>
</body>
</html>