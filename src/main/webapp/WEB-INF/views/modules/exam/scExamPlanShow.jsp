<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考试安排管理</title>
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
		<li><a href="${ctx}/exam/scExamPlan/">考试安排列表</a></li>
		<li class="active"><a href="${ctx}/exam/scExamPlan/form?id=${scExamPlanVo.id}">考试安排查看</a></li>
	</ul><br/>
<table class="table">
		<tr>
			<td colspan="2"><input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/></td>
		</tr>
		<tr>
			<th width="200">考试id：</th>
			<td>
				${fns:getDictLabel(scExamPlanVo.examId,'sc_exam' ,null)}
			</td>
		</tr>
		<tr>
			<th width="200">学期id：</th>
			<td>
				${fns:getDictLabel(scExamPlanVo.semesterId,'sc_semester' ,null)}
			</td>
		</tr>
		<tr>
			<th width="200">年级id：</th>
			<td>
				${fns:getDictLabel(scExamPlanVo.gradeId,'sc_grade' ,null)}
			</td>
		</tr>
		<tr>
			<th width="200">学科id：</th>
			<td>
				${fns:getDictLabel(scExamPlanVo.courseId,'sc_course' ,null)}
			</td>
		</tr>
		<tr>
			<th width="200">开始考试时间：</th>
			<td>
				<fmt:formatDate value="${scExamPlanVo.startTime}" pattern="yyyy-MM-dd"/>
			</td>
		</tr>
		<tr>
			<th width="200">结束考试时间：</th>
			<td>
				<fmt:formatDate value="${scExamPlanVo.endTime}" pattern="yyyy-MM-dd"/>
			</td>
		</tr>
		<tr>
			<th width="200">监考老师：</th>
			<td>

			${fns:getUserById(scExamPlanVo.teacher1Id).name}

			</td>
		</tr>
		<tr>
			<th width="200">监考老师2：</th>
			<td>

			${fns:getUserById(scExamPlanVo.teacher2Id).name}

			</td>
		</tr>
		<tr>
			<th width="200">满分：</th>
			<td>
				${scExamPlanVo.maxScore}
			</td>
		</tr>
</body>
</html>