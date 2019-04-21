<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
						<h2 style="text-align: center;height: 30px;margin-top:5px;">用户注册</h2>
					</div>
				</div>
				<form id="user" class="form-horizontal" accept-charset="utf-8">
					<div class="panel-body col-md-offset-2">
						<div class="col-md-9">
							<!--增大边距div-->
							<div class="form-group">
								<label></label>								
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label" style="float:left;font-size:15px">邮&nbsp;&nbsp;&nbsp;&nbsp;箱：</label>
								<div class="col-md-7">
									<input id="account" style="margin-left: -20px;" type="text" name="" class="form-control">		
								</div>		
								<button type="button" style="margin-left: -35px;" class="col-sm-2 btn btn-primary">验证邮箱</button>	
							</div>	
							<div class="form-group">
								<label></label>								
							</div>
							<!--加点颜色-->

							<div class="form-group">
								<label class="col-sm-3 control-label" style="font-size:15px">验证码：</label>
								<div class="col-md-9">
									<input id="vcode" style="margin-left: -20px ;width: 140px;" type="text" name="" class="form-control">
								</div>
							</div>

							<div class="form-group">
								<label></label>								
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label" style="font-size:15px">密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
								<div class="col-md-9">
									<input id="password" style="margin-left: -20px" type="text" name="" class="form-control">
								</div>
							</div>
				
							<div class="form-group">
								<!--换行div-->
								<div class="checkbox col-md-offset-3">
									<div style="margin-left: -5px">
									<label><input type="checkbox" name="" class="">我已同意服务条款</label>		
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-offset-3">
									<input style="margin-left: -5px" id="insertBtn" class="btn btn-primary" type="button" name="" value="注 册">
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
    $("#insertBtn").click(function(){		  
    	var account=$("#account").val();
    	if(account==""){
    		layer.msg("登陆账号不能为空", {time:2000, icon:5, shift:6}, function(){
			});
    		return;
    	}
    	var loadingIndex=null;
    	$.ajax({
    		type:"post",
    		url :"${APP_PATH}/user/insert",
    		data:{
    			"account":account,
    			"password" :$("#password").val(),
    			"vcode"    :$("#vcode").val()
    		},
    		beforeSend:function(){
    			loadingIndex=layer.msg("处理中",{icon:16});
    		},
    		success:function(result){
    			layer.close(loadingIndex);
    			if(result.success){
    				layer.msg(result.data, {time:1200, icon:6}, function(){
    					window.location.href="${APP_PATH}/user/index"
    				});
    			}
    			else{
    				layer.msg(result.data, {time:2000, icon:5, shift:6}, function(){
    				});
    			}
    		}
    	})
    })		    
});
</script>
</html>