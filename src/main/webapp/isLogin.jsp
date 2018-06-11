<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
function isLogin() {
	$.ajax({
		url : '/isSessionTimeOut.json',
		data : null,
		type : "get",
		dataType : "json",
		success : function(data) {
			//已过期
			if (!data.result) {
				
			}else{
				console.log("未过期");
			}
		}
	});
}
</script>
