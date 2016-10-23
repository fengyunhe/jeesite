<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>学生信息管理</title>
	<meta name="decorator" content="default"/>
	<!-- Add fancyBox -->
    <link rel="stylesheet" href="//cdn.bootcss.com/fancybox/2.1.5/jquery.fancybox.min.css" type="text/css" media="screen" />
    <script type="text/javascript" src="//cdn.bootcss.com/fancybox/2.1.5/jquery.fancybox.pack.js"></script>
    <style>
    	.imagelink img{
    		max-height:100px
    	}
    </style>
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/student/scStudent/">学生信息列表</a></li>
		<li class="active"><a href="${ctx}/student/scStudent/form?id=${scStudentVo.id}">学生信息查看</a></li>
	</ul><br/>
<table class="table">
		<tr>
			<td colspan="2"><input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/></td>
		</tr>
		<tr>
			<th width="200">姓名：</th>
			<td>
				${scStudentVo.name}
				<span class="help-inline"><font color="red">*</font> </span>
			</td>
		</tr>
		<tr>
			<th width="200">学号：</th>
			<td>
				${scStudentVo.stNo}
				<span class="help-inline"><font color="red">*</font> </span>
			</td>
		</tr>
		<tr>
			<th width="200">照片：</th>
			<td>
				    <a href='${scStudentVo.photoUrl}' target="_blank" class="imagelink" title=""><img src='${scStudentVo.photoUrl}' /></a>
			</td>
		</tr>
		<tr>
			<th width="200">性别：</th>
			<td>
				${fns:getDictLabel(scStudentVo.gender,'gender' ,null)}
				<span class="help-inline"><font color="red">*</font> </span>
			</td>
		</tr>
		<tr>
			<th width="200">身份证号码：</th>
			<td>
				${scStudentVo.idNo}
			</td>
		</tr>
		<tr>
			<th width="200">生日：</th>
			<td>
				<fmt:formatDate value="${scStudentVo.birthday}" pattern="yyyy-MM-dd"/>
			</td>
		</tr>
		<tr>
			<th width="200">微信：</th>
			<td>
				${scStudentVo.wechat}
			</td>
		</tr>
		<tr>
			<th width="200">QQ：</th>
			<td>
				${scStudentVo.qq}
			</td>
		</tr>
		<tr>
			<th width="200">父亲姓名：</th>
			<td>
				${scStudentVo.fatherName}
			</td>
		</tr>
		<tr>
			<th width="200">母亲姓名：</th>
			<td>
				${scStudentVo.motherName}
			</td>
		</tr>
		<tr>
			<th width="200">父亲电话：</th>
			<td>
				${scStudentVo.fatherPhone}
			</td>
		</tr>
		<tr>
			<th width="200">母亲电话：</th>
			<td>
				${scStudentVo.motherPhone}
			</td>
		</tr>
		<tr>
			<th width="200">父亲职业：</th>
			<td>
				${scStudentVo.fatherJob}
			</td>
		</tr>
		<tr>
			<th width="200">母亲职业：</th>
			<td>
				${scStudentVo.motherJob}
			</td>
		</tr>
		<tr>
			<th width="200">备注信息：</th>
			<td>
				${scStudentVo.remarks}
			</td>
		</tr>
</body>
</html>