<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>请假申请管理</title>
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
		<li class="active"><a href="${ctx}/vacation/scTeacherVacation/">请假申请列表</a></li>
		<shiro:hasPermission name="vacation:scTeacherVacation:edit"><li><a href="${ctx}/vacation/scTeacherVacation/form">请假申请添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="scTeacherVacationVo" action="${ctx}/vacation/scTeacherVacation/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>请假类型：</label>
				<form:select path="vacationType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sc_vacation_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>状态：</label>
				<form:input path="status" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>教师姓名：</label>
				<form:input path="teacherName" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>申请请假时间</th>
				<th>请假起始时间</th>
				<th>请假结束时间</th>
				<th>请假类型</th>
				<th>销假时间</th>
				<th>状态</th>
				<th>实际请假天数</th>
				<th>教师姓名</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="vacation:scTeacherVacation:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="vo">
			<tr>
				<td>
				<a href="${ctx}/vacation/scTeacherVacation/show?id=${vo.id}">
					<fmt:formatDate value="${vo.requestDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a>
				</td>
				<td>
				
					<fmt:formatDate value="${vo.beginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				
				</td>
				<td>
				
					<fmt:formatDate value="${vo.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				
				</td>
				<td>
				
					${fns:getDictLabel(vo.vacationType, 'sc_vacation_type', '')}
				
				</td>
				<td>
				
					<fmt:formatDate value="${vo.realEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				
				</td>
				<td>
				
					${fns:getDictLabel(vo.status,'sc_vacation_status','')}
				
				</td>
				<td>
				
					${vo.realDates}
				
				</td>
				<td>
				
					${fns:getTeacherName(vo.teacherId)}
				
				</td>
				<td>
				
					<fmt:formatDate value="${vo.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				
				</td>
				<td>
				
					${vo.remarks}
				
				</td>
				<shiro:hasPermission name="vacation:scTeacherVacation:edit"><td>
    				<a href="${ctx}/vacation/scTeacherVacation/form?id=${vo.id}">修改</a>
					<a href="${ctx}/vacation/scTeacherVacation/delete?id=${vo.id}" onclick="return confirmx('确认要删除该请假申请吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>