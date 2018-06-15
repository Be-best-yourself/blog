<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../../common/basePath.jsp"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分类管理</title>
<link rel="stylesheet" type="text/css"
	href="${basePath }/res/css/inc.css" />
<%@ include file="../../common/js/jquery.jsp"%>
<%@ include file="../../common/css/bootstrap_css.jsp"%>
<%@ include file="../../common/js/bootstrap_js.jsp"%>
<style type="text/css">
.title {
	width: 100%;
	height:25px;
	background: #eee;
	line-height: 2em;
	padding-left: 15px;
	font-family: cursive;
}
/* 目录区 */
.classifyList {
	float: left;
	width: 30%;
	border: 1px solid #eee;
	margin-left: 2%;
	margin-top: 2%;
	padding-left: 2%;
}

.classify {
	width: 100%;
	height: 30px;
	line-height: 2.5;
}

.classify>a {
	display: block;
	text-decoration: inherit;
}

/*目录详情区 */
.classify-dtl {
	width: 66%;
	min-height: 500px;
	float: left;
	border: 1px solid #eee;
	margin-left: 2%;
	margin-top: 2%;
}
/* 新建目录 */
.createInput {
	width: 95px;
	height: 18px;
	border: none;
	border-radius: 3px;
	border-bottom: 1px solid;
	display: none;
}

/*目录文章显示区*/
.dtl{
	padding-left: 15px;
    font-family: cursive;
}
.dtl>table{
    line-height: 2em;
    width: 100%;
}
.dtl>table>tbody>tr{
	cursor:pointer;
	color: #337ab7;
}
.dtl>table>tbody>tr:hover {
	background: #f0f8ff;
}
</style>
<script type="text/javascript">
/* 监听回车 */
$(document).keyup(function(event) {
	if (event.keyCode == 13) {
		if(!$("#enter").is(":hidden")){
			$("#enter").click();
		};
	}
});

function classifyFold(id) {
	$(".createInput").hide();
	$("#classifWork").show();
	$("#classifyEnter").hide();
	$("#tip").hide();
	$("#classifyLocation").show();
	$("#tip").find("span").text("");
	/* 目录折叠 */
	var aText = $("#" + id + " a").find("span:eq(0)");
	if (aText.text() == "+") {
		$.ajax({
			url : '${basePath }editor/classify/' + id,
			data : null,
			type : 'get',
			dataType : 'html',
			success : function(data) {
				$("#" + id).append(data);
				aText.text("-");
			}
		});
	} else {
		$("#" + id).find("div:gt(0)").remove();
		aText.text("+");
	}
	classifyTitle(id);
	classifyDtl(id);
}

/*目录title显示*/
function classifyTitle(id){
	$.ajax({
		url : '${basePath }editor/classify/getParent.json',
		data : {
			'id' : id
		},
		type : 'get',
		dataType : 'json',
		success : function(data) {
			$("#classifyLocation").find("span:eq(0)").text(
					data.classify.classifyPath + "/");
			$("#classifyLocation").find("span:eq(1)").text(
					data.classify.classifyLevel);
			$("#classifyLocation").find("span:eq(2)").text(
					data.classify.classifyBlogNum);
			$("#classifyLocation").find("span:eq(3)")
					.text(data.classify.id);
		}
	});
}

/* 目录详情区显示 */
function classifyDtl(id){
	$.ajax({
		url : '${basePath }editor/getChildClassifyAndBlogById',
		data : {'id':id},
		type : 'post',
		dataType : 'html',
		success : function(data) {
			$(".dtl").empty();
			$(".dtl").append(data);
		}
	});
}


/* 新建分类 */
function createClassify(classifyName, classifyParentId) {
	$.ajax({
		url : '${basePath }editor/classify.json',
		data : {
			'classifyParentId' : classifyParentId,
			'classifyName' : classifyName,
		},
		type : "post",
		dataType : "json",
		success : function(data) {
			if (data.classify.classifyParentId == 0) {
				location.reload();
			} else {
				classifyFold(data.classify.classifyParentId);
				classifyFold(data.classify.classifyParentId);
			}
		}
	});
}
/* 重命名分类 */
function renameClassify(classifyName, classifyId) {
	$.ajax({
		url : '${basePath }editor/classify/'+classifyId+'.json',
		data : {
			'classifyName' : classifyName,
		},
		type : "post",
		dataType : "json",
		success : function(data) {
			if (data.classify.classifyParentId == 0) {
				location.reload();
			} else {
				classifyFold(data.classify.classifyParentId);
				classifyFold(data.classify.classifyParentId);
			}
		}
	});
}
/* 删除分类 */
function deleteClassify(classifyId){
	$.ajax({
		url:'${basePath }editor/classify/'+classifyId+'.json',
		date:null,
		type:"delete",
		dataType : "json",
		success : function(data) {
			if (data.classify.classifyParentId == 0) {
				location.reload();
			} else {
				classifyFold(data.classify.classifyParentId);
				classifyFold(data.classify.classifyParentId);
			}
		}
	});
}

$(document).ready(function() {
	/* 刚加载页面时，由于处于根目录，根目录不允许操作，禁用操作按钮 	//TODO	*/

	
	var classifyPath;
	$("#createClassify").on("click", function() {
		$(".createInput").show();
		$(".createInput").focus();
		$(".createInput").val("");
		$("#classifWork").hide();
		$("#classifyEnter").show();
		$("#enter").attr("data-type", "create");
	});
	$("#renameClassify").on("click",function() {
		$(".createInput").show();
		$(".createInput").focus();
		$("#classifWork").hide();
		$("#classifyEnter").show();
		$("#enter").attr("data-type","rename");
		var classfyname = $("#classifyLocation").find("span:eq(0)");
		classifyPath = classfyname.text();
		var classifyNames = classfyname.text().split("/");

		var classifyrename = classifyNames[classifyNames.length - 2];
		classfyname.text(classfyname.text().replace(classifyrename+ "/", ""));
		$(".createInput").val(classifyrename);

	});
	$("#deleteClassify").on("click", function() {
		$("#classifWork").hide();
		$("#classifyEnter").show();
		$("#enter").attr("data-type", "delete");
		
		$("#classifyLocation").hide();
		$("#tip").show();
		var classifyId = $("#classifyLocation").find("span:eq(3)").text();
		$.get('${basePath }editor/classify/' + classifyId+'.json', function(data) {
			if (data.classifys.length!=0||$("#classifyLocation").find("span:eq(2)").text()!=0) {
				$("#tip").find("span").text("此分类下有子分类或有文章,子分类和文章将移至回收站");
			}else{
				$("#tip").find("span").text("是否删除当前分类?");
			}
		});
	});

	$("#enter").on("click",function() {
		/* 删除 */
		if ($(this).attr("data-type") == "delete") {
			var classifyId = $("#classifyLocation").find("span:eq(3)").text();
			deleteClassify(classifyId);
			return;
		}
		var createClassifyName = $(".createInput").val();
		var reg = new RegExp("^[A-Za-z0-9\u4e00-\u9fa5]+$");
		if (!reg.test(createClassifyName)) {
			alert("文件夹名，只能是中英文或数字");
			return;
		}
		/* 新建 */
		if ($(this).attr("data-type") == "create") {
			var classifyParentId = $("#classifyLocation").find("span:eq(3)").text();
			createClassify(createClassifyName,classifyParentId);
			return;
		}
		/* 重命名 */
		if ($(this).attr("data-type") == "rename") {
			var classifyId = $("#classifyLocation").find("span:eq(3)").text();
			renameClassify(createClassifyName,classifyId);
			return;
		}
	});
	$("#cancel").on("click",function() {
		$(".createInput").hide();
		$("#classifWork").show();
		$("#classifyEnter").hide();
		$("#classifyLocation").find("span:eq(0)").text(classifyPath);
		
		$("#tip").hide();
		$("#classifyLocation").show();
		$("#tip").find("span").text("");
	});

});
</script>
</head>
<body>
	<div class="body">
		<c:set var="submenu" value="classifyMenu"></c:set>

		<%@include file="../include/header.jsp"%>

		<div class="content">
			<%@include file="../include/left.jsp"%>

			<div class="container-right">

				<div class="title">分类管理</div>
				<!-- 目录区 -->
				<div class="classifyList">
					<c:forEach items="${classifys }" var="classify">
						<div id="${classify.id }">
							<div class="classify">
								<a href="javascript:void(0)"
									onclick="classifyFold('${classify.id}')"><span>+</span> <span>${classify.classifyName }</span>
								</a>
							</div>
						</div>
					</c:forEach>
				</div>

				<!-- 目录详情区 -->
				<div class="classify-dtl">
					<!-- 目录title -->
					<div class="title">
						<div id="classifyLocation" style="float:left">
							当前位置:<span>/</span><input class="createInput" type="text" /> 
							级别:<span>0</span>
							文章数:<span>0</span>
							<span style="display: none">0</span>
						</div>
						<div id="tip" style="float:left;display:none;color: red;-webkit-animation: bounce-up 1.4s linear infinite;">
							<span></span>
						</div>
						<div id="classifWork" style="float: right; margin-right: 10px">
							<span><a href="javascript:void(0)" id="createClassify">创建</a></span>
							<span><a href="javascript:void(0)" id="renameClassify">重命名</a></span>
							<span><a href="javascript:void(0)" id="deleteClassify">删除</a></span>
						</div>

						<div id="classifyEnter"
							style="float: right; margin-right: 10px; display: none">
							<span><a href="javascript:void(0)" id="enter" data-type="">确认</a></span> <span><a
								href="javascript:void(0)" id="cancel">取消</a></span>
						</div>
					</div>
					<div class="clear"></div>
					<!-- 目录详情 -->
					<div class="dtl">
						<table>
							<thead>
								<tr>
									<td  style="width: 50%">名称</td>
									<td  style="width: 30%">类形</td>
									<td  style="width: 20%">创建时间</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${classifys }" var="classify">
									<tr onclick="classifyFold('${classify.id}')">
										<td>${classify.classifyName }</td>
										<td>文件夹</td>
										<td><fmt:formatDate value="${classify.classifyCreateTime }" type="date"
												pattern="yyyy-MM-dd HH:mm" /></td>
									</tr>
								</c:forEach>
								<c:forEach items="${blogs }" var="blog">
									<tr onclick="javascript:void(0)">
										<td>${blog.blogName }</td>
										<td>文章</td>
										<td><fmt:formatDate value="${blog.blogCreateTime }"
												type="date" pattern="yyyy-MM-dd HH:mm" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>

		</div>

	</div>
	<%@include file="../../common/loading.jsp" %>

</body>
</html>