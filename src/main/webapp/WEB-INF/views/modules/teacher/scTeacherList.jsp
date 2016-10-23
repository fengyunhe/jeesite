<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>教师信息管理</title>
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
		<li class="active"><a href="${ctx}/teacher/scTeacher/">教师信息列表</a></li>
		<shiro:hasPermission name="teacher:scTeacher:edit"><li><a href="${ctx}/teacher/scTeacher/form">教师信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="scTeacherVo" action="${ctx}/teacher/scTeacher/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>教师编号：</label>
				<form:input path="teNo" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li><label>性别：</label>
				<form:radiobuttons path="gender" items="${fns:getDictList('gender')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li><label>手机号：</label>
				<form:input path="phone" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>邮箱：</label>
				<form:input path="email" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>住址：</label>
				<form:input path="address" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>身份证号：</label>
				<form:input path="idNo" htmlEscape="false" maxlength="18" class="input-medium"/>
			</li>
			<li><label>主要学科：</label>
				<form:select path="courseId" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sc_course')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>职称等级：</label>
				<form:select path="levelId" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sc_teacher_level')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>职务：</label>
				<form:select path="jobId" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sc_teacher_job')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>生日：</label>
				<input name="beginBirthday" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${scTeacherVo.beginBirthday}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> -
				<input name="endBirthday" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${scTeacherVo.endBirthday}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>ID</th>
				<th>教师编号</th>
				<th>姓名</th>
				<th>性别</th>
				<th>头像</th>
				<th>手机号</th>
				<th>邮箱</th>
				<th>QQ</th>
				<th>身份证号</th>
				<th>主要学科</th>
				<th>职称等级</th>
				<th>职务</th>
				<th>创建时间</th>
				<shiro:hasPermission name="teacher:scTeacher:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="vo">
			<tr>
				<td>
				<a href="${ctx}/teacher/scTeacher/show?id=${vo.id}">
					${vo.id}
				</a>
				</td>
				<td>
				
					${vo.teNo}
				
				</td>
				<td>
				
					${vo.name}
				
				</td>
				<td>
				
					${fns:getDictLabel(vo.gender, 'gender', '')}
				
				</td>
				<td>
				
				    <a href='${vo.photoUrl}' target="_blank" class="imagelink" title="">图片</a>
				
				</td>
				<td>
				
					${vo.phone}
				
				</td>
				<td>
				
					${vo.email}
				
				</td>
				<td>
				
					${vo.qq}
				
				</td>
				<td>
				
					${vo.idNo}
				
				</td>
				<td>
				
					${fns:getDictLabel(vo.courseId, 'sc_course', '')}
				
				</td>
				<td>
				
					${fns:getDictLabel(vo.levelId, 'sc_teacher_level', '')}
				
				</td>
				<td>
				
					${fns:getDictLabel(vo.jobId, 'sc_teacher_job', '')}
				
				</td>
				<td>
				
					<fmt:formatDate value="${vo.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				
				</td>
				<shiro:hasPermission name="teacher:scTeacher:edit"><td>
    				<a href="${ctx}/teacher/scTeacher/form?id=${vo.id}">修改</a>
					<a href="${ctx}/teacher/scTeacher/delete?id=${vo.id}" onclick="return confirmx('确认要删除该教师信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>