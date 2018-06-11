<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="container-right">
	<!-- 加载编辑器的容器 -->
	<script id="editor" style="width: 100%; height: 100%" name="content"
		type="text/plain"> <span style="font-family: &quot;comic sans ms&quot;; color: rgb(216, 216, 216);">别压抑自己，请开始你的表演……</span></script>
	<!-- 配置文件 -->
	<%@include file="../../common/ueditor/ueditor.jsp"%>
	<!-- 实例化编辑器 -->
	<script type="text/javascript">
		var ue = UE.getEditor('editor', {
			//focus时自动清空初始化时的内容
			autoClearinitialContent : true,
			initialFrameHeight : 500,
		});
	</script>
</div>