<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>学生违纪事件管理</title>
	<meta name="decorator" content="default"/>
	<!-- Add fancyBox -->
    <link rel="stylesheet" href="//cdn.bootcss.com/fancybox/2.1.5/jquery.fancybox.min.css" type="text/css" media="screen" />
    <script type="text/javascript" src="//cdn.bootcss.com/fancybox/2.1.5/jquery.fancybox.pack.js"></script>
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
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/student_event/scStudentEvent/">学生违纪事件列表</a></li>
		<shiro:hasPermission name="student_event:scStudentEvent:edit"><li><a href="${ctx}/student_event/scStudentEvent/form">学生违纪事件添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="scStudentEventVo" action="${ctx}/student_event/scStudentEvent/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>发生时间：</label>
				<input name="beginEventTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${scStudentEventVo.beginEventTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> -
				<input name="endEventTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${scStudentEventVo.endEventTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>事件类型：</label>
				<form:select path="eventType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sc_student_event_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>内容：</label>
				<form:input path="content" htmlEscape="false" maxlength="500" class="input-medium"/>
			</li>
			<li><label>学生：</label>
				<form:input path="studentId" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>编号</th>
				<th>事件发生事件</th>
				<th>事件类型</th>
				<th>内容</th>
				<th>学生</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="student_event:scStudentEvent:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="vo">
			<tr>
				<td>
				<a href="${ctx}/student_event/scStudentEvent/show?id=${vo.id}">
					${vo.id}
				</a>
				</td>
				<td>
				
					<fmt:formatDate value="${vo.eventTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				
				</td>
				<td>
				
					${fns:getDictLabel(vo.eventType, 'sc_student_event_type', '')}
				
				</td>
				<td>
				
					${vo.content}
				
				</td>
				<td>
				
					${fns:getStudentName(vo.studentId)}
				
				</td>
				<td>
				
					<fmt:formatDate value="${vo.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				
				</td>
				<td>
				
					${vo.remarks}
				
				</td>
				<shiro:hasPermission name="student_event:scStudentEvent:edit"><td>
    				<a href="${ctx}/student_event/scStudentEvent/form?id=${vo.id}">修改</a>
					<a href="${ctx}/student_event/scStudentEvent/delete?id=${vo.id}" onclick="return confirmx('确认要删除该学生违纪事件吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>