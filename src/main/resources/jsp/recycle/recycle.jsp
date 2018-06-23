<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../../common/basePath.jsp"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>回收站</title>
<link rel="stylesheet" type="text/css"
	href="${basePath }/res/css/inc.css" />
<%@ include file="../../common/js/jquery.jsp"%>
<%@ include file="../../common/css/bootstrap_css.jsp"%>
<%@ include file="../../common/js/bootstrap_js.jsp"%>
<%@ include file="../../common/select2/select2.jsp"%>
<style type="text/css">
.dtl {
	font-family: cursive;
}

.dtl>table {
	line-height: 2em;
	width: 100%;
}

.dtl>table>thead {
	background: #eee;
}

.dtl>table>tbody>tr {
	cursor: pointer;
	color: #337ab7;
}

.dtl>table>tbody>tr:hover {
	background: #f0f8ff;
}

.pop {
	background-image: -webkit-radial-gradient(center center, ellipse cover, rgba(0, 0, 0, 0.4)
		0px, rgba(0, 0, 0, 0.5) 100%);
	z-index: 999;
	top: 0;
	bottom: 0;
	left: 0;
	right: 0;
	position: fixed;
	opacity: 1;
	display: none;
}

.pop .dialog {
	border: 1px solid rgba(0, 0, 0, .2);
	border-radius: 6px;
	z-index: 990;
	width: 330px;
	height: 300px;
	margin-left: calc(100%/ 2 - 165px);
	background: #fff;
	position: absolute;
	top: 10%;
}

.pop .dialogTitle {
	font-size: 18px;
	margin-left: 310px;
	height: 20px;
}

.pop .dialogBody {
	height: 200px;
	border-top: 1px solid #eee;
	padding-top: 15px;
	padding-left: 15px;
}

.pop .dialogFooter {
	border-top: 1px solid #eee;
	height: 80px;
	text-align: center;
	padding-top: 20px;
}

.dialogBody>form>input {
	padding: 0 16px;
	border: none;
	background-color: #efefef;
	color: #4f4f4f;
	width: 300px;
	line-height: 40px;
	margin-top: 10px;
}
</style>
<script type="text/javascript">
	function getChildRecycle() {
		$.ajax({
			url : "${basePath}recycle/getChildRecycle",
			data : null,
			type : 'post',
			dataType : 'html',
			success : function(data) {
				$(".dtl").empty();
				$(".dtl").html(data);
			}
		});
	}
	function deleteBlog(id){
		$.ajax({
			url : '${basePath}blog/' + id+ '.json',
			data :null,
			type : 'delete',
			dataType : 'json',
			success : function(data) {
				window.location.reload();
			}
		});
	}
	$(document).ready(function() {
		getChildRecycle();
		$("#releaseBtn,#saveBtn").on("click", function() {
			if ($('#blogClassifyId').val() == ""||$('#blogClassifyId').val() ==null) {
				$('#blogClassifyId').focus();
				return;
			}
			var blogStatus = 0;
			if ($(this).attr("id") == "saveBtn") {
				blogStatus = 2;
			}
			$.ajax({
				url : '${basePath}blog/' + $("#blogId").val() + '.json',
				data : {
					'blogName' : $('#blogName').val(),
					'blogClassifyId' : $("#blogClassifyId").val(),
					'blogStatus' : blogStatus,
					'blogKeyword' : $("#blogKeyword").val()
				},
				type : 'post',
				dataType : 'json',
				success : function(data) {
					window.location.reload();
				}
			});

		});
	});
</script>
</head>
<body>
	<div class="body">
		<c:set var="submenu" value="recycleMenu"></c:set>
		<%@include file="../include/header.jsp"%>
		<div class="content">
			<%@include file="../include/left.jsp"%>
			<div class="container-right">
				<div class="dtl"></div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function popClose() {
			$(".pop").hide();
		}
		function popShow(id) {
			$(".pop").show();
			var select2Obj = $("#blogClassifyId").select2({
				tags : false,
				placeholder : "请选择一个分类",
				allowClear : false,
			});
			$.ajax({
				url : '${basePath}blog/' + id + '.json',
				data : null,
				type : 'get',
				dataType : 'json',
				success : function(data) {
					$("#blogId").val(data.blog.id);
					$("#blogName").val(data.blog.blogName);
					$("#blogKeyword").val(data.blog.blogKeyword);
					//select2默认选中事件
					select2Obj.val(data.blog.blogClassifyId + "").trigger(
							"change");
				}
			});
		}
	</script>
	<div class="pop">
		<div class="dialog">
			<div class="dialogTitle">
				<span><a href="javascript:void(0)" onclick="popClose()">×</a></span>
			</div>
			<div class="dialogBody">
				<form id="saveForm">
					<input type="text" id="blogId" style="display: none"> <input
						type="text" id="blogName" placeholder="请输入标题" name="blogName">
					<input type="text" id="blogKeyword" placeholder="文章标签"
						name="blogKeyWord">
					<div style="width: 300px; margin-top: 10px">
						<select id="blogClassifyId"
							class="js-example-placeholder-single js-states form-control">
							<option></option>
							<option value="0">/</option>
							<c:forEach items="${classifys }" var="classify">
								<option value="${classify.id }">${classify.classifyName }</option>
							</c:forEach>
						</select>
					</div>
				</form>
			</div>
			<div class="dialogFooter">
				<button type="button" id="saveBtn" class="btn btn-lg">移至草稿</button>
				<button type="button" id="releaseBtn" class="btn btn-primary btn-lg">直接发布</button>
			</div>
		</div>
	</div>
</body>
</html>