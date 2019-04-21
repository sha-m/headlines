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
<jsp:include page="common/protaltop.jsp" flush="true" /> 

	<!--外层容器-->
	<div class="container">
		<div class="col-md-10 col-md-offset-1">
			<div class="panel panel-primary">

				<div class="panel-heading">
					<div class="panel-title">
						<h2 style="text-align: center;height: 30px;margin-top:5px;">用户登陆</h2>
					</div>
				</div>
				<form class="form-horizontal" accept-charset="utf-8">
					<div class="panel-body col-md-offset-3">
						<div class="col-md-9">
							<!--增大边距div-->
							<div class="form-group">
								<label></label>								
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label" style="font-size:15px">账&nbsp;&nbsp;&nbsp;&nbsp;号：</label>
								<div class="col-md-7">
									<input id="account" style="margin-left: -20px;" type="text" name="" class="form-control">		
								</div>		

							</div>	
							<div class="form-group">
								<label></label>								
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label" style="font-size:15px">密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
								<div class="col-md-7">
									<input id="password" style="margin-left: -20px" type="text" name="" class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label></label>								
							</div>
							<div class="form-group">
								<div class="col-md-9 col-md-offset-3">
									<input id="login" style="margin-left: -20px" class="btn btn-primary" type="button" name="" value="登 陆">
								</div>
							</div>
						</div>
					</div>
				</form>

			</div>
		</div>
	</div>
</body>
	<script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH}/script/docs.min.js"></script>
	<script src="${APP_PATH}/layer/layer.js"></script>
<script type="text/javascript">
$(function () {
    $("#login").click(function(){		
    //非空校验
    var account=$("#account").val();
    if(account==""){
    	//alert("用户名不能为空");   	
		layer.msg("用户名不能为空", {time:2000, icon:5, shift:6}, function(){
		});
    	return ;
    }  
    var password=$("#password").val();
    if(password==""){
    	//alert("密码不能为空");
    	layer.msg("密码不能为空", {time:2000, icon:5, shift:6}, function(){
		});
    	return ;
    }  
    //提交表单
    var loadingIndex=null;
    $.ajax({
    	type:"post",
    	url :"${APP_PATH}/user/loginacct",
    	data:{
    		"account":account,
    		"password" :password
    	},
    	beforeSend:function(){
    		//加载
			loadingIndex = layer.msg('处理中', {icon: 16});
    	},
    	success:function(result){
    		layer.close(loadingIndex);
    		if(result.success){
    			window.location.href="${APP_PATH}/user/index";
    		}else{
    			layer.msg("用户名或密码错误，请重新输入", {time:2000, icon:5, shift:6}, function(){
    			});
    		}
    	}
	});
})
})
</script>
</html>