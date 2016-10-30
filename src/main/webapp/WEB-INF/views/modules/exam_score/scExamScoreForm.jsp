<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考试成绩管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/exam_score/scExamScore/">考试成绩列表</a></li>
		<li class="active"><a href="${ctx}/exam_score/scExamScore/form?id=${scExamScore.id}">考试成绩<shiro:hasPermission name="exam_score:scExamScore:edit">${not empty scExamScore.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="exam_score:scExamScore:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="scExamScoreVo" action="${ctx}/exam_score/scExamScore/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">学生：</label>
			<div class="controls">
				<form:select path="studentId" cssClass="input-xlarge">
					<form:options items="${fns:getStudentList('')}" itemValue="id" itemLabel="name"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">成绩：</label>
			<div class="controls">
				<form:input path="score" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">考试类型：</label>
			<div class="controls">
				<form:select path="examType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sc_exam')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">学期：</label>
			<div class="controls">
				<form:select path="semesterId" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sc_semester')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">年级：</label>
			<div class="controls">
				<form:select path="gradeId" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sc_grade')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">学科：</label>
			<div class="controls">
				<form:select path="courseId" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sc_course')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="exam_score:scExamScore:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>