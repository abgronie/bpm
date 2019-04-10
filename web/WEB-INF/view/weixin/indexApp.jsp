<%@page import="com.hotent.platform.model.system.GlobalType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
    <%@include file="/commons/include/get.jsp" %>
    <script type="text/javascript">
        $(function(){
            var todo = $("#todo").val();
            if (todo == "myToDo") { //我的待办
                window.location.href='../weixin/bpm/myToDo.html';
            } else if (todo == "myAlreadyMatters") { //我的已办
                window.location.href='../weixin/bpm/myAlreadyMatters.html';
            } else if (todo == "myCompletedMatters") { //办结事宜
                window.location.href='../weixin/bpm/myCompletedMatters.html';
            } else if (todo == "startFlowList") { //发起流程
                window.location.href='../weixin/bpm/startFlowList.html';
            } else if (todo == "myRequestList") { //我发起的流程
                window.location.href='../weixin/bpm/myRequestList.html';
            } else if (todo == "myDraftList") { //我的草稿
                window.location.href='../weixin/bpm/myDraftList.html';
            } else if (todo == "index") { //首页
                window.location.href='../weixin/bpm/index.html';
            } else {
                alert(todo);
                window.location.href='../error.jsp';
            }
        });
    </script>
</head>
<input type="hidden" id="todo" value="${todo}">
</html>