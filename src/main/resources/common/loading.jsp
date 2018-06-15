<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
.loading {
	z-index: 99;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	position: fixed;
	display:none;
	opacity: 1;
}

.loading>img {
	width: 50px;
	height: 50px;
	margin-left: calc(100%/ 2 - 25px);
	margin-top: 200px;
}
</style>
<script type="text/javascript">
	/* $(document).on("ajaxStart ajaxStop",function(){
	 $("#loading").toggle();
	 }); */
	$(document).ajaxStart(function() {
		$("#loading").show();
	}).ajaxSuccess(function() {
		$("#loading").hide();
	})
</script>
<div id="loading" class="loading">
	<img alt="" src="${basePath }/res/img/loading.gif">
</div>