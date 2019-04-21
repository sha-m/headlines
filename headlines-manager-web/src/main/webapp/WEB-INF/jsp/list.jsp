<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title></title>
	<link rel="stylesheet" href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH}/pagination/pagination.css">	
	<style type="text/css" media="screen">
</style>
</head>
<body>
<jsp:include page="common/backstage.jsp" flush="true" /> 

	<div class="container">
		<!--面板-->
		<div class="panel panel-primary">
			<div class="panel-heading" style="height: 50px">
				<div class="panel-title ">
					<h4 style="margin-top:6px;"><span class="glyphicon glyphicon-th-large" style="font-size: 15px"></span> 数据列表</h4>
				</div>
			</div>
			<!--搜索框-->
			<div class="">
				<form class="navbar-form pull-left">
					<div class="form-group">
						<input id="queryText" type="text" class="form-control" placeholder="Search">
					</div>
					<button id="queryBtn" type="button" class="btn btn-warning"><span class="glyphicon glyphicon-search"></span></button>
				</form>
				<div class="" style="padding-top:8px;padding-right:15px;">
					<button type="button" onclick="deleteUsers()" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
					<button type="button" class="btn btn-primary" style="float:right;"><i class="glyphicon glyphicon-plus"></i> 新增</button>
				</div>
			</div>
			<hr style="clear:both;margin-bottom: 3px;">
			<div class="panel-body">
			<form id="userForm" accept-charset="utf-8">
				<table class="table table-striped table-bordered table-hover">
					<!--设置颜色-->
					<tr class="info">
						<th width="30">#</th>
						<th width="30"><input type="checkbox" id="allSelBox">
							<th>账号</th>
							<th>邮箱</th>
							<th width="100">操作</th>
						</tr>
						
						<tbody id="userData">
						<!-- 遍历 -->
						</tbody>
						
						<tfoot>
			     			<tr>
				     			<td colspan="6" align="center">
									<div id="Pagination" class="pagination">
									<!-- 页码 -->									
									</div>
								</td>
							</tr>
			  			</tfoot>								
						
					</table>
				</form>
			</div>
			<div class="panel-footer">		
			</div>
		</div>
	</div>
</body>
	<script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH}/script/docs.min.js"></script>
	<script src="${APP_PATH}/layer/layer.js"></script>
	<script src="${APP_PATH}/pagination/jquery.pagination.js"></script>
<script type="text/javascript">
var likeflg=false;
$(function () {
	pageQuery(0);
	$("#queryBtn").click(function(){
		var queryText=$("#queryText").val();
		if(queryText==""){
			likeflg=false;
		}else{
			likeflg=true;
		}
		pageQuery(0);			
	})	
	$("#allSelBox").click(function(){
		var flg=this.checked;
		$("#userData :checkbox").each(function(){
			this.checked=flg;			    			
		})
	})
})

function pageQuery(pageIndex){
	var pageno=pageIndex+1;
	var loadingIndex=null;
	//pageno:开始页数，pagesize:一页10行
	var jsonData = {"pageno" : pageno};
	if(likeflg==true){
		jsonData.queryText=$("#queryText").val();
	}
	$.ajax({
		type:"POST",
		url :"${APP_PATH}/user/querylist",
		data: jsonData,  
		beforeSend:function(){
    		//加载
			loadingIndex = layer.msg('处理中', {icon: 16});
    	},
    	success:function(result){
    		layer.close(loadingIndex);
    		if(result.success){	
    			//局部刷新页面数据
    			var tableContent="";
    			var pageContent="";
    			//用户信息集合
    			var userPage=result.data;
    			//用户信息对象
    			var users=userPage.datas;       			         			
    			$.each(users,function(i,user){
    		tableContent+='		<tr>';
            tableContent+='       <td>'+(i+1)+'</td>';
  			tableContent+='	  	  <td><input type="checkbox" name="userid" value="'+user.id+'"></td>';
            tableContent+='       <td>'+user.name+'</td>';
            tableContent+='       <td>'+user.account+'</td>';
            tableContent+='       <td>';
  			tableContent+='	      <button type="button" onclick="goAssignPage('+user.id+')" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>';
  			tableContent+='	      <button type="button" onclick="goUpdatePage('+user.id+')" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>';
  			tableContent+='		  <button type="button" onclick="deleteUser('+user.id+',\''+user.account+'\')" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>';
  			tableContent+='	  	</td>';
            tableContent+='     </tr>';
    			});
    			
    			$("#userData").html(tableContent);
    			
    			$("#Pagination").pagination(userPage.count, {
    				num_edge_entries: 2, //边缘页数
    				num_display_entries: 4, //主体页数
    				callback: pageQuery,
    				items_per_page:10, //每页显示10项
    				current_page:(userPage.pageno-1),
    				prev_text:"上一页",
    				next_text:"下一页"
    			});
    			
    		}else{
    			layer.msg("分页信息查询错误", {time:1200, icon:5, shift:6}, function(){
    			});
    		}
    	}
	})
}  


function deleteUser(id,account){
	layer.confirm("删除用户信息【"+account+"】,是否继续",  {icon: 3, title:'提示'}, function(cindex){
	    //删除用户信息
	    $.ajax({
	    	type:"post",
	    	url :"${APP_PATH}/user/delete",
	    	data:{id:id},
	    	success:function(result){
	    		if(result.success){
	    			layer.msg("用户信息删除成功", {time:1200, icon:6}, function(){
	    				pageQuery(0);
	    			});
	    		}else{
	    			layer.msg("用户信息删除失败", {time:1200, icon:5, shift:6}, function(){
        			});
	    		}
	    	}
	    })
		layer.close(cindex);
	}, function(cindex){
	    layer.close(cindex);
	});
}  


function deleteUsers(){
	var boxes=$("#userData :checked");
	if (boxes.length==0) {
		layer.msg("请选择需要删除的信息", {time:1000, icon:5, shift:6}, function(){});
	}else{
		layer.confirm("删除所选的用户信息,是否继续",  {icon: 3, title:'提示'}, function(cindex){
		    //删除用户信息
		    $.ajax({
		    	type:"post",
		    	url :"${APP_PATH}/user/deletes",
		    	data:$("#userForm").serialize(),
		    	success:function(result){
		    		if(result.success){
		    			pageQuery(0);
		    		}else{
		    			layer.msg("用户信息删除失败", {time:1000, icon:5, shift:6}, function(){
            			});
		    		}
		    	}
		    })
    		layer.close(cindex);
		}, function(cindex){
		    layer.close(cindex);
		});
    }
}

</script>
</html>
