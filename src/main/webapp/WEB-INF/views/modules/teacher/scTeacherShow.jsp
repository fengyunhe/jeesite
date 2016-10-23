<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>教师信息管理</title>
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
		<li><a href="${ctx}/teacher/scTeacher/">教师信息列表</a></li>
		<li class="active"><a href="${ctx}/teacher/scTeacher/form?id=${scTeacherVo.id}">教师信息查看</a></li>
	</ul><br/>
<table class="table">
		<tr>
			<td colspan="2"><input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/></td>
		</tr>
		<tr>
			<th>教师编号：</th>
			<td>
				${scTeacherVo.teNo}
			</td>
		</tr>
		<tr>
			<th>姓名：</th>
			<td>
				${scTeacherVo.name}
				<span class="help-inline"><font color="red">*</font> </span>
			</td>
		</tr>
		<tr>
			<th>性别：</th>
			<td>
				${fns:getDictLabel(scTeacherVo.gender,'gender' ,null)}
				<span class="help-inline"><font color="red">*</font> </span>
			</td>
		</tr>
		<tr>
			<th>头像：</th>
			<td>
				    <a href='${scTeacherVo.photoUrl}' target="_blank" class="imagelink" title=""><img src='${scTeacherVo.photoUrl}' /></a>
			</td>
		</tr>
		<tr>
			<th>手机号：</th>
			<td>
				${scTeacherVo.phone}
			</td>
		</tr>
		<tr>
			<th>邮箱：</th>
			<td>
				${scTeacherVo.email}
			</td>
		</tr>
		<tr>
			<th>住址：</th>
			<td>
				${scTeacherVo.address}
			</td>
		</tr>
		<tr>
			<th>QQ：</th>
			<td>
				${scTeacherVo.qq}
			</td>
		</tr>
		<tr>
			<th>微信号：</th>
			<td>
				${scTeacherVo.wechat}
			</td>
		</tr>
		<tr>
			<th>身份证号：</th>
			<td>
				${scTeacherVo.idNo}
			</td>
		</tr>
		<tr>
			<th>主要学科：</th>
			<td>
				${fns:getDictLabel(scTeacherVo.courseId,'sc_course' ,null)}
			</td>
		</tr>
		<tr>
			<th>职称等级：</th>
			<td>
				${fns:getDictLabel(scTeacherVo.levelId,'sc_teacher_level' ,null)}
			</td>
		</tr>
		<tr>
			<th>职务：</th>
			<td>
				${fns:getDictLabel(scTeacherVo.jobId,'sc_teacher_job' ,null)}
			</td>
		</tr>
		<tr>
			<th>生日：</th>
			<td>
				<fmt:formatDate value="${scTeacherVo.birthday}" pattern="yyyy-MM-dd"/>
			</td>
		</tr>
		<tr>
			<th>备注信息：</th>
			<td>
				${scTeacherVo.remarks}
			</td>
		</tr>
</body>
</html>