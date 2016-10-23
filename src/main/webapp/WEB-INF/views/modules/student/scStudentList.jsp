<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>学生信息管理</title>
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
		<li class="active"><a href="${ctx}/student/scStudent/">学生信息列表</a></li>
		<shiro:hasPermission name="student:scStudent:edit"><li><a href="${ctx}/student/scStudent/form">学生信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="scStudentVo" action="${ctx}/student/scStudent/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li><label>学号：</label>
				<form:input path="stNo" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li><label>性别：</label>
				<form:radiobuttons path="gender" items="${fns:getDictList('gender')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li><label>身份证号码：</label>
				<form:input path="idNo" htmlEscape="false" maxlength="18" class="input-medium"/>
			</li>
			<li><label>生日：</label>
				<input name="birthday" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${scStudentVo.birthday}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>微信：</label>
				<form:input path="wechat" htmlEscape="false" maxlength="45" class="input-medium"/>
			</li>
			<li><label>QQ：</label>
				<form:input path="qq" htmlEscape="false" maxlength="12" class="input-medium"/>
			</li>
			<li><label>父亲姓名：</label>
				<form:input path="fatherName" htmlEscape="false" maxlength="45" class="input-medium"/>
			</li>
			<li><label>母亲姓名：</label>
				<form:input path="motherName" htmlEscape="false" maxlength="45" class="input-medium"/>
			</li>
			<li><label>父亲电话：</label>
				<form:input path="fatherPhone" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>母亲电话：</label>
				<form:input path="motherPhone" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>姓名</th>
				<th>学号</th>
				<th>性别</th>
				<th>身份证号码</th>
				<th>生日</th>
				<th>微信</th>
				<th>QQ</th>
				<th>父亲姓名</th>
				<th>母亲姓名</th>
				<th>父亲电话</th>
				<th>母亲电话</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="student:scStudent:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="vo">
			<tr>
				<td>
				<a href="${ctx}/student/scStudent/show?id=${vo.id}">
					${vo.name}
				</a>
				</td>
				<td>
				
					${vo.stNo}
				
				</td>
				<td>
				
					${fns:getDictLabel(vo.gender, 'gender', '')}
				
				</td>
				<td>
				
					${vo.idNo}
				
				</td>
				<td>
				
					<fmt:formatDate value="${vo.birthday}" pattern="yyyy-MM-dd HH:mm:ss"/>
				
				</td>
				<td>
				
					${vo.wechat}
				
				</td>
				<td>
				
					${vo.qq}
				
				</td>
				<td>
				
					${vo.fatherName}
				
				</td>
				<td>
				
					${vo.motherName}
				
				</td>
				<td>
				
					${vo.fatherPhone}
				
				</td>
				<td>
				
					${vo.motherPhone}
				
				</td>
				<td>
				
					<fmt:formatDate value="${vo.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				
				</td>
				<td>
				
					<fmt:formatDate value="${vo.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				
				</td>
				<td>
				
					${vo.remarks}
				
				</td>
				<shiro:hasPermission name="student:scStudent:edit"><td>
    				<a href="${ctx}/student/scStudent/form?id=${vo.id}">修改</a>
					<a href="${ctx}/student/scStudent/delete?id=${vo.id}" onclick="return confirmx('确认要删除该学生信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>