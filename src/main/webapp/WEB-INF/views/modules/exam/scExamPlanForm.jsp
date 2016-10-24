<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考试安排管理</title>
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
		<li><a href="${ctx}/exam/scExamPlan/">考试安排列表</a></li>
		<li class="active"><a href="${ctx}/exam/scExamPlan/form?id=${scExamPlan.id}">考试安排<shiro:hasPermission name="exam:scExamPlan:edit">${not empty scExamPlan.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="exam:scExamPlan:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="scExamPlanVo" action="${ctx}/exam/scExamPlan/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">考试id：</label>
			<div class="controls">
				<form:radiobuttons path="examId" items="${fns:getDictList('sc_exam')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">学期id：</label>
			<div class="controls">
				<form:select path="semesterId" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sc_semester')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">年级id：</label>
			<div class="controls">
				<form:select path="gradeId" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sc_grade')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">学科id：</label>
			<div class="controls">
				<form:select path="courseId" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sc_course')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开始考试时间：</label>
			<div class="controls">
				<input name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${scExamPlanVo.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结束考试时间：</label>
			<div class="controls">
				<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${scExamPlanVo.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">监考老师：</label>
			<div class="controls">
				<sys:treeselect id="teacher1Id" name="teacher1Id" value="${scExamPlanVo.teacher1Id}" labelName="" labelValue="${scExamPlanVo.teacher1Name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">监考老师2：</label>
			<div class="controls">
				<sys:treeselect id="teacher2Id" name="teacher2Id" value="${scExamPlanVo.teacher2Id}" labelName="" labelValue="${scExamPlanVo.teacher2Name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">满分：</label>
			<div class="controls">
				<form:input path="maxScore" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="exam:scExamPlan:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>