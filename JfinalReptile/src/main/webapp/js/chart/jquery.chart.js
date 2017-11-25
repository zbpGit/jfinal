/**
 * 类级插件
 */
$.jameChart = function(opts){
	var defaults={
		target :"",
		width:"100%",
		height:400,
		type:"column2d",
		dataType:"xml",
		data:{},
		callback:function(){
			
		}
	};
	var opts = $.extend({},defaults,opts);
	FusionCharts.ready(function(){
		var reveChart = new FusionCharts({
			"type":opts.type,
			"renderAt":opts.target,
			"width":opts.width,
			"height":opts.height,
			"dataFormat":opts.dataType,
			"dataSource":opts.data
		});
		reveChart.render();
	});
}