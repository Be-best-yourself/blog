<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
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
	width: 450px;
	height: 300px;
	margin-left: calc(100%/ 2 - 225px);
	background: #fff;
	position: absolute;
	top: 10%;
}

.pop .dialogTitle {
	font-size: 18px;
	margin-left: 430px;
	height: 20px;
}

.pop .dialogBody {
	height:200px;
	border-top: 1px solid #eee;
	padding-top: 15px;
	padding-left: 15px;
}
.pop .dialogFooter{
	border-top: 1px solid #eee;
	height: 80px;
	text-align: center;
    line-height: 5;
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
<div class="container-right">
	<!-- 加载编辑器的容器 -->
	<script id="editor" style="width: 100%; height: 100%" name="content"
		type="text/plain"> 
		<span style="font-family: &quot;comic sans ms&quot;; color: rgb(216, 216, 216);">别压抑自己，请开始你的表演……</span>
	</script>
</div>
<script type="text/javascript">
	var ue = UE.getEditor('editor', {
		labelMap : {
			'save' : '保存'
		},

		//focus时自动清空初始化时的内容
		autoClearinitialContent : true,
		initialFrameHeight : 450,
		enableAutoSave :true,
		maximumWords :100000
	});
	/* 开启本地保存 */
	ue.ready(function() {  
	    ue.execCommand( 'drafts' ); 
	}); 

	UE.commands['save'] = {
		execCommand : function() {
			if (!hasContent()) {
				alert("您还没开始表演呢!");
			}else {
				popShow();
				$(".js-example-placeholder-single").select2({
					tags : false,
					placeholder : "请选择一个分类",
					allowClear : false
				});
			}
		}
	};
	$(document).ready(function() {
		//保存发布博客
		$("#releaseBtn,#saveBtn").on("click", function() {
			if ($('#blogName').val() == "") {
				$('#blogNameTip').hide(100);
				$('#blogNameTip').show(100);
				$('#blogName').focus();
				return;
			}
			if ($('#blogClassifyId').val() == "") {
				$('#blogClassifyId').focus();
				return;
			}
			var blogStatus=0;
			if($(this).attr("id")=="saveBtn"){
				blogStatus=2;
			}
			$.ajax({
				url : '${basePath}blog.json',
				data : {'blogId':$("#blogId").val(),
						'blogName':$('#blogName').val(),
						'blogTitle':$('#blogName').val(),
						'blogText':UE.getEditor('editor').getContent(),
						'blogClassifyId':$("#blogClassifyId").val(),
						'blogStatus':blogStatus,
						'blogKeyWord':$("#blogKeyWord").val()
						},
				type : 'post',
				dataType : 'json',
				success : function(data) {
					if (data.result) {
						popClose();
						alert("操作成功");
					}
				}
			});
			
		});
		
	});
	if ('${blog.id}'!=0) {
		setTimeout(function () {
			ue.execCommand('cleardoc');
	        ue.execCommand('insertHtml', $("#editornone").html())
		}, 200);
	} 
	//是否有内容
	function hasContent() {
        return UE.getEditor('editor').hasContents();
    }
	
	//上传配置
	UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
	UE.Editor.prototype.getActionUrl = function(action) {
		//判断路径   这里是config.json 中设置执行上传的action名称
		if (action == 'uploadimage' || action == 'uploadvideo'
				|| action == 'uploadfile') {
			return '${basePath}upload/file.json';
		} else if (action == 'uploadscrawl') {
			return '${basePath}upload/scrawl.json';
		} else if (action == 'listimage') {
			return '${basePath}upload/list.json?fileType=0';
		} else if (action == 'listfile') {
			return '${basePath}upload/list.json?fileType=2';
		}else if (action == 'catchimage') {
			return '${basePath}upload/catchimage.json';
		} else {
			return this._bkGetActionUrl.call(this, action);
		}
	}
	
	
</script>

<script type="text/javascript">
	function popClose() {
		$(".pop").hide();
	}
	function popShow() {
		$(".pop").show();
	}
</script>
<div class="pop">
	<div class="dialog">
		<div class="dialogTitle">
			<span><a href="javascript:void(0)" onclick="popClose()">×</a></span>
		</div>
		<div class="dialogBody">
			<form id="saveForm">
				<input type="text" id="blogId" value="${blog.id }" style="display: none">
				<input type="text" id="blogName" placeholder="请输入标题" value="${blog.blogName }" name="blogName"><span id="blogNameTip" style="color:red;margin-left:10px">*标题不能为空</span> 
				<input type="text" id="blogKeyWord" placeholder="文章标签" value="${blog.blogKeyword }" name="blogKeyWord"><span id="blogKeyWordTip" style="color:red;margin-left:10px">建议输入文章标签</span> 
				<div style="width: 300px;margin-top:10px">
					<select id="blogClassifyId" class="js-example-placeholder-single js-states form-control">
						<option></option>
						<option value="0">/</option>
						<c:forEach items="${classifys }" var="classify">
							<option  <c:if test="${blog.blogClassifyId eq classify.id}">selected</c:if> value="${classify.id }">${classify.classifyName }</option>
						</c:forEach>
					</select>
				</div>
			</form>
		</div>
		<div class="dialogFooter">
			<button type="button" id="releaseBtn" class="btn btn-primary btn-lg">发布文章</button>&#12288;&#12288;&#12288;&#12288;
			<button type="button" id="saveBtn" class="btn btn-default btn-lg">保存草稿</button>
		</div>
	</div>
</div>