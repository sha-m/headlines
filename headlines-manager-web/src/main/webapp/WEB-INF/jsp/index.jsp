<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title></title>
	<link rel="stylesheet" href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
	<style type="text/css" media="screen">
</style>
</head>
<body>

<jsp:include page="common/backstage.jsp" flush="true" /> 
<div class="container"> 
	<h3>数据导入</h3>
	<button type="button" class="col-sm-2 btn btn-primary" onclick="data()">execute</button>	
</div>
</body>
	<script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH}/script/docs.min.js"></script>
	<script src="${APP_PATH}/layer/layer.js"></script>
<script type="text/javascript">
function data(){
	$.ajax({
		type:"POST",
		url :"${APP_PATH}/data/import",
		beforeSend:function(){
    		//加载
			loadingIndex = layer.msg('处理中', {icon: 16});
    	},
    	success:function(result){
    		layer.close(loadingIndex);
    		if(result.success){	
    			layer.msg("数据导入成功", {time:1200, icon:6,}, function(){
    			});
    		}else{
    			layer.msg("数据导入错误", {time:1200, icon:5, shift:6}, function(){
    			});
    		}
    	}
	})
}  

</script>
</html>
