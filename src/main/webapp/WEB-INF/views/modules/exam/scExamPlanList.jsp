<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考试安排管理</title>
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
		<li class="active"><a href="${ctx}/exam/scExamPlan/">考试安排列表</a></li>
		<shiro:hasPermission name="exam:scExamPlan:edit"><li><a href="${ctx}/exam/scExamPlan/form">考试安排添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="scExamPlanVo" action="${ctx}/exam/scExamPlan/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>考试id：</label>
				<form:radiobuttons path="examId" items="${fns:getDictList('sc_exam')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li><label>学期id：</label>
				<form:select path="semesterId" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sc_semester')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>年级id：</label>
				<form:select path="gradeId" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sc_grade')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>学科id：</label>
				<form:select path="courseId" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sc_course')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>考试id</th>
				<th>学期id</th>
				<th>年级id</th>
				<th>学科id</th>
				<th>开始考试时间</th>
				<th>结束考试时间</th>
				<th>满分</th>
				<shiro:hasPermission name="exam:scExamPlan:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="vo">
			<tr>
				<td>
				<a href="${ctx}/exam/scExamPlan/show?id=${vo.id}">
					${fns:getDictLabel(vo.examId, 'sc_exam', '')}
				</a>
				</td>
				<td>
				
					${fns:getDictLabel(vo.semesterId, 'sc_semester', '')}
				
				</td>
				<td>
				
					${fns:getDictLabel(vo.gradeId, 'sc_grade', '')}
				
				</td>
				<td>
				
					${fns:getDictLabel(vo.courseId, 'sc_course', '')}
				
				</td>
				<td>
				
					<fmt:formatDate value="${vo.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				
				</td>
				<td>
				
					<fmt:formatDate value="${vo.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				
				</td>
				<td>
				
					${vo.maxScore}
				
				</td>
				<shiro:hasPermission name="exam:scExamPlan:edit"><td>
    				<a href="${ctx}/exam/scExamPlan/form?id=${vo.id}">修改</a>
					<a href="${ctx}/exam/scExamPlan/delete?id=${vo.id}" onclick="return confirmx('确认要删除该考试安排吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>