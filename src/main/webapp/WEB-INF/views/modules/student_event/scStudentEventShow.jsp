<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>学生违纪事件管理</title>
	<meta name="decorator" content="default"/>
	<!-- Add fancyBox -->
    <link rel="stylesheet" href="//cdn.bootcss.com/fancybox/2.1.5/jquery.fancybox.min.css" type="text/css" media="screen" />
    <script type="text/javascript" src="//cdn.bootcss.com/fancybox/2.1.5/jquery.fancybox.pack.js"></script>
    <style>
    	.imagelink img{
    		max-height:100px
    	}
    </style>
	<script type="text/javascript">
		$(document).ready(function() {
            $('.imagelink').fancybox({
                  helpers: {
                      title : {
                          type : 'float'
                      }
                  }
            });
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/student_event/scStudentEvent/">学生违纪事件列表</a></li>
		<li class="active"><a href="${ctx}/student_event/scStudentEvent/form?id=${scStudentEventVo.id}">学生违纪事件查看</a></li>
	</ul><br/>
<table class="table">
		<tr>
			<td colspan="2"><input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/></td>
		</tr>
		<tr>
			<th width="200">事件发生事件：</th>
			<td>
				<fmt:formatDate value="${scStudentEventVo.eventTime}" pattern="yyyy-MM-dd"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</td>
		</tr>
		<tr>
			<th width="200">事件类型：</th>
			<td>
				${fns:getDictLabel(scStudentEventVo.eventType,'sc_student_event_type' ,null)}
				<span class="help-inline"><font color="red">*</font> </span>
			</td>
		</tr>
		<tr>
			<th width="200">内容：</th>
			<td>
				${scStudentEventVo.content}
				<span class="help-inline"><font color="red">*</font> </span>
			</td>
		</tr>
		<tr>
			<th width="200">学生：</th>
			<td>
				${scStudentEventVo.studentId}
				<span class="help-inline"><font color="red">*</font> </span>
			</td>
		</tr>
		<tr>
			<th width="200">备注信息：</th>
			<td>
				${scStudentEventVo.remarks}
			</td>
		</tr>
</body>
</html>