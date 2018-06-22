<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--为方便查找修改，将css文件碎片化-->
<script type="text/javascript">
	/* 左侧菜单滚动事件 */
	$(function() {
		var navH = $(".container-left").offset().top;
		$(window).scroll(function() {
			var scroH = $(this).scrollTop();
			if (scroH >= navH) {
				$(".container-left").css({
					"position" : "fixed",
					"top" : 0
				});
			} else if (scroH < navH) {
				$(".container-left").css({
					"position" : "static"
				});
			}
		})
	})
</script>
<div class="container-left">
	<ul>
		<li <c:if test="${submenu eq 'writeMenu'}">class="active"</c:if>><a
			href="${basePath }editor/toUEditor">写文章</a></li>
		<li <c:if test="${submenu eq 'classifyMenu'}">class="active"</c:if>><a
			href="${basePath }classify/toClassify">分类管理</a></li>
		<li><a href="#">草稿箱</a></li>
		<li <c:if test="${submenu eq 'recycleMenu'}">class="active"</c:if>><a href="${basePath }recycle/toRecycle">回收站</a></li>
		
	</ul>
</div>