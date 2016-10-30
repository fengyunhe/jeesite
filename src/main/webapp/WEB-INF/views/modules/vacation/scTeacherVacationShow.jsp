<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>请假申请管理</title>
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
		<li><a href="${ctx}/vacation/scTeacherVacation/">请假申请列表</a></li>
		<li class="active"><a href="${ctx}/vacation/scTeacherVacation/form?id=${scTeacherVacationVo.id}">请假申请查看</a></li>
	</ul><br/>
<table class="table">
		<tr>
			<td colspan="2"><input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/></td>
		</tr>
		<tr>
			<th width="200">申请请假时间：</th>
			<td>
				<fmt:formatDate value="${scTeacherVacationVo.requestDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</td>
		</tr>
		<tr>
			<th width="200">请假起始时间：</th>
			<td>
				<fmt:formatDate value="${scTeacherVacationVo.beginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</td>
		</tr>
		<tr>
			<th width="200">请假结束时间：</th>
			<td>
				<fmt:formatDate value="${scTeacherVacationVo.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</td>
		</tr>
		<tr>
			<th width="200">请假类型：</th>
			<td>
				${fns:getDictLabel(scTeacherVacationVo.vacationType,'sc_vacation_type' ,null)}
				<span class="help-inline"><font color="red">*</font> </span>
			</td>
		</tr>
		<tr>
			<th width="200">备注：</th>
			<td>
				${scTeacherVacationVo.remark}
			</td>
		</tr>
		<tr>
			<th width="200">销假时间：</th>
			<td>
				<fmt:formatDate value="${scTeacherVacationVo.realEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
		</tr>
		<tr>
			<th width="200">状态：</th>
			<td>
				${scTeacherVacationVo.status}
				<span class="help-inline"><font color="red">*</font> </span>
			</td>
		</tr>
		<tr>
			<th width="200">流程引擎对应的id：</th>
			<td>
				${scTeacherVacationVo.procInstId}
				<span class="help-inline"><font color="red">*</font> </span>
			</td>
		</tr>
		<tr>
			<th width="200">实际请假天数：</th>
			<td>
				${scTeacherVacationVo.realDates}
			</td>
		</tr>
		<tr>
			<th width="200">教师id：</th>
			<td>
				${scTeacherVacationVo.teacherId}
			</td>
		</tr>
		<tr>
			<th width="200">备注信息：</th>
			<td>
				${scTeacherVacationVo.remarks}
			</td>
		</tr>
</body>
</html>