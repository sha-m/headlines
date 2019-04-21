<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title></title>
	<link rel="stylesheet" href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
    <script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH}/script/docs.min.js"></script>
	<script src="${APP_PATH}/script/holder.min.js"></script>
	<script src="${APP_PATH}/layer/layer.js"></script>
	<style type="text/css" media="screen">
	.row{
		margin-bottom: 15px;
	}
	body
	{
		padding-top:30px;
	}
</style>
</head>
<body>
	<!--外层容器-->
	<div class="container" style="margin-bottom: 50px">
		<div class="container-fluid">

			<!-- <nav class="navbar navbar-default"> -->
				<!--反色,置顶,navbar-fixed-bottom置底部-->	
				<nav class="navbar navbar-inverse navbar-fixed-top">
					<!--限制置顶后不受约束的问题-->
					<div class="container">
						<div class="navbar-header ">
							<a href="" class="navbar-brand">
								<!--可写字，可画图-->	
								<img src="holder.js/100px100p" alt="">
							</a>
						</div>							
						<div id="mynavbar" id="collapse navbar-collapse">					
									<ul class="nav navbar-nav">
										<li class="active"><a href="">热销</a></li>
										<li><a href="">数码</a></li>
										<li><a href="">百货</a></li>
										<li><a href="">女装</a></li>
									</ul>
							<form class="navbar-form pull-left">
								<div class="form-group">
									<input type="text" id="content" class="form-control" placeholder="Search">
								</div>
								<button type="button" id="search" class="btn btn-warning"><span class="glyphicon glyphicon-search"></span></button>
							</form>
							<c:choose>
								<c:when test="${!empty name}">
									<ul class="nav navbar-nav pull-right">
									<!--下拉菜单-->
										<li class="dropdown">
											<a href="" class="dropdown-toggle" data-toggle="dropdown">
												<span>${name} <span class="caret"></span></span>
											</a>
											<ul class="dropdown-menu">
												<li><a href="">个人信息</a></li>
												<li><a href="">退出</a></li>
											</ul>
										</li>
									</ul>
								</c:when>
								<c:when test="${empty name}">
										<button style="margin-left: 10px" class="btn btn-warning navbar-btn pull-right">注册</button>
										<button class="btn btn-warning navbar-btn pull-right">登陆</button>
								</c:when>
							</c:choose>	
						</div>				
					</div>				
				</nav>
			</div>
		</div>

	</body>
	</html>