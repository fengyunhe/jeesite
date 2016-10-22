<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>教师信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {

		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/teacher/scTeacher/">教师信息列表</a></li>
		<li class="active"><a href="${ctx}/teacher/scTeacher/form?id=${scTeacher.id}">教师信息<shiro:hasPermission name="teacher:scTeacher:edit">${not empty scTeacher.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="teacher:scTeacher:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
<table class="table">

		<tr>
			<th>教师编号：</th>
			<td>
				${scTeacher.teNo}
			</td>
		</tr>
		<tr>
			<th>姓名：</th>
			<td>
				${scTeacher.name}
				<span class="help-inline"><font color="red">*</font> </span>
			</td>
		</tr>
		<tr>
			<th>性别：</th>
			<td>
				${fns:getDictLabel(scTeacher.gender,'gender' ,null)}
				<span class="help-inline"><font color="red">*</font> </span>
			</td>
		</tr>
		<tr>
			<th>头像：</th>
			<td>
				    <c:forEach var ="f" items="${fn:split(scTeacher.photoUrl,'|')}" varStatus="current">
				    	<c:if test="${!empty f}">
				    	<a href='${f}' target="_blank">文件 ${current.index+1}</a>
				    	</c:if>
				    </c:forEach>
			</td>
		</tr>
		<tr>
			<th>手机号：</th>
			<td>
				${scTeacher.phone}
			</td>
		</tr>
		<tr>
			<th>邮箱：</th>
			<td>
				${scTeacher.email}
			</td>
		</tr>
		<tr>
			<th>住址：</th>
			<td>
				${scTeacher.address}
			</td>
		</tr>
		<tr>
			<th>QQ：</th>
			<td>
				${scTeacher.qq}
			</td>
		</tr>
		<tr>
			<th>微信号：</th>
			<td>
				${scTeacher.wechat}
			</td>
		</tr>
		<tr>
			<th>身份证号：</th>
			<td>
				${scTeacher.idNo}
			</td>
		</tr>
		<tr>
			<th>备注信息：</th>
			<td>
				${scTeacher.remarks}
			</td>
		</tr>
		<tr>
			<td colspan="2"><input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/></td>
		</tr>
</body>
</html>