<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title></title>
	<link rel="stylesheet" href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
	<style type="text/css" media="screen">
		.row{
			margin-bottom: 15px;
		}
	</style>
</head>
<body>
<jsp:include page="common/protaltop.jsp" flush="true" /> 
	<!--外层容器-->
	<div class="container">

		<div class="row">
			<div class="col-sm-12 col-md-12">
				<div class="col-sm-6 col-md-1">					
				</div>	
				<div class="col-sm-6 col-md-5">					
					<img style="padding-left:100px;max-height:300px"" src="${goods.gimg }" 
						alt="通用的占位符缩略图">
				</div>			
				<div class="col-sm-6 col-md-5">
					<div class="caption">
						<p style=" font-size: 22px;">${goods.gname }</p>
						<p style="color: red;font-size: 20px;">${goods.gprice }</p>
						<p>简介：${goods.ginfo }</p>
						<p>款式：默认</p>
						<div style="">
							<button class="btn btn-warning" style="width: 120px;">立即购买</button>
							<button class="btn btn-default" style="width: 120px;">加入购物车</button>
						</div>
					</div>				
				</div>
			</div>
		</div>
	</div>
</body>
	<script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH}/script/docs.min.js"></script>
	<script src="${APP_PATH}/layer/layer.js"></script>
	
<script type="text/javascript">
</script>
</html>