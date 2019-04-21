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
		.row{
			margin-bottom: 15px;
		}
		p{
			margin-bottom: 15px;
			overflow:hidden; 
			text-overflow:ellipsis;
			display:-webkit-box; 
			-webkit-box-orient:vertical;
			-webkit-line-clamp:2; 
		}
	</style>
</head>
<body>
	<jsp:include page="common/protaltop.jsp" flush="true" /> 

	<!--外层容器-->
	<div class="container">
			
		<div id="search-goods" class="row" style="margin-bottom: 40px;">				

		</div>
		<center>
			<div id="Pagination" class="pagination" style="margin-bottom: 80px">
				<!-- 页码 -->									
			</div>	
		</center>
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
	list(0);
	$("#search").click(function(){	
		var queryText=$("#content").val();
		if(queryText==""){
			likeflg=false;
		}else{
			likeflg=true;
		}
		list(0);			
	})
})


function list(pageIndex){
	var pageno=pageIndex+1;
	var loadingIndex=null;
	var jsonData = {"pageno" : pageno};
 	if(likeflg==true){
		jsonData.content=$("#content").val();
	}
	$.ajax({
		type:"POST",
		url :"${APP_PATH}/search/list",
		data: jsonData,  
		beforeSend:function(){
			loadingIndex = layer.msg('处理中', {icon: 16});
    	},
    	success:function(result){
    		layer.close(loadingIndex);
    		var tableContent=""; 
    		if(result.success){	
    			var page=result.data;	
    			var goodsList=page.datas;	
    			$.each(goodsList,function(i,goods){
    		tableContent+='		<a href="${APP_PATH}/search/deatil/'+goods.id+'">';
    		tableContent+='		<div class="col-sm-6 col-md-3">';
            tableContent+='       <div class="thumbnail">';
  			tableContent+='	  	  <img src='+goods.gimg+' alt=""> ';
            tableContent+='       <div class="caption">';
            tableContent+='       	<p style="float: left; font-size: 16px;">'+goods.gname+'</p>';
            tableContent+='       	<p style="float: right; color: red;font-size: 16px;">￥'+goods.gprice+'</p>';
  			tableContent+='	      	<p style="clear:both;">'+goods.ginfo+'</p>';
  			tableContent+='	  	  </div>';
            tableContent+='    	  </div>';
            tableContent+='    </div>';
            tableContent+='    </a>';
    			});
    			
    			$("#search-goods").html(tableContent);
    			
     			$("#Pagination").pagination(page.count, {
    				num_edge_entries: 2, //边缘页数
    				num_display_entries: 4, //主体页数
    				callback: list,
    				items_per_page:50, //每页显示10项
    				current_page:(page.pageno-1),
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

</script>
</html>
