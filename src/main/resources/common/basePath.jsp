<%@page import="com.alibaba.druid.sql.visitor.functions.If"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String scheme= request.getScheme();
int port= request.getServerPort();
String basePath = scheme+"://"+request.getServerName()+":"+port+path+"/";
request.setAttribute("basePath", basePath);
pageContext.setAttribute("titileName", "我要秀点技术");
%>