<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>教学物资管理</title>
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
		<li class="active"><a href="${ctx}/res/scResource/">教学物资列表</a></li>
		<shiro:hasPermission name="res:scResource:edit"><li><a href="${ctx}/res/scResource/form">教学物资添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="scResourceVo" action="${ctx}/res/scResource/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="45" class="input-medium"/>
			</li>
			<li><label>类型：</label>
				<form:select path="resourceType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sc_resource_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>数量：</label>
				<form:input path="total" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>类型</th>
				<th>数量</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="res:scResource:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="vo">
			<tr>
				<td>
				<a href="${ctx}/res/scResource/show?id=${vo.id}">
					${vo.name}
				</a>
				</td>
				<td>
				
					${fns:getDictLabel(vo.resourceType, 'sc_resource_type', '')}
				
				</td>
				<td>
				
					${vo.total}
				
				</td>
				<td>
				
					<fmt:formatDate value="${vo.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				
				</td>
				<td>
				
					${vo.remarks}
				
				</td>
				<shiro:hasPermission name="res:scResource:edit"><td>
    				<a href="${ctx}/res/scResource/form?id=${vo.id}">修改</a>
					<a href="${ctx}/res/scResource/delete?id=${vo.id}" onclick="return confirmx('确认要删除该教学物资吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>