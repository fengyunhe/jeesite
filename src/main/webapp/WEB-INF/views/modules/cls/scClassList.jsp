<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>班级信息管理</title>
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
		<li class="active"><a href="${ctx}/cls/scClass/">班级信息列表</a></li>
		<shiro:hasPermission name="cls:scClass:edit"><li><a href="${ctx}/cls/scClass/form">班级信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="scClassVo" action="${ctx}/cls/scClass/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>年级：</label>
				<form:select path="gradeId" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sc_grade')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>班级：</label>
				<form:input path="name" htmlEscape="false" maxlength="45" class="input-medium"/>
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
				<th>年级</th>
				<th>班级</th>
				<th>顺序</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<shiro:hasPermission name="cls:scClass:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="vo">
			<tr>
				<td>
				<a href="${ctx}/cls/scClass/show?id=${vo.id}">
					${vo.id}
				</a>
				</td>
				<td>
				
					${fns:getDictLabel(vo.gradeId, 'sc_grade', '')}
				
				</td>
				<td>
				
					${vo.name}
				
				</td>
				<td>
				
					${vo.seq}
				
				</td>
				<td>
				
					<fmt:formatDate value="${vo.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				
				</td>
				<td>
				
					<fmt:formatDate value="${vo.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				
				</td>
				<shiro:hasPermission name="cls:scClass:edit"><td>
    				<a href="${ctx}/cls/scClass/form?id=${vo.id}">修改</a>
					<a href="${ctx}/cls/scClass/delete?id=${vo.id}" onclick="return confirmx('确认要删除该班级信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>