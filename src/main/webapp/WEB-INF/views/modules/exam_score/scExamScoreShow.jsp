<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考试成绩管理</title>
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
		<li><a href="${ctx}/exam_score/scExamScore/">考试成绩列表</a></li>
		<li class="active"><a href="${ctx}/exam_score/scExamScore/form?id=${scExamScoreVo.id}">考试成绩查看</a></li>
	</ul><br/>
<table class="table">
		<tr>
			<td colspan="2"><input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/></td>
		</tr>
		<tr>
			<th width="200">学生：</th>
			<td>
				${scExamScoreVo.studentId}
			</td>
		</tr>
		<tr>
			<th width="200">成绩：</th>
			<td>
				${scExamScoreVo.score}
			</td>
		</tr>
		<tr>
			<th width="200">考试类型：</th>
			<td>
				${fns:getDictLabel(scExamScoreVo.examType,'sc_exam' ,null)}
			</td>
		</tr>
		<tr>
			<th width="200">学期：</th>
			<td>
				${fns:getDictLabel(scExamScoreVo.semesterId,'sc_semester' ,null)}
			</td>
		</tr>
		<tr>
			<th width="200">年级：</th>
			<td>
				${fns:getDictLabel(scExamScoreVo.gradeId,'sc_grade' ,null)}
			</td>
		</tr>
		<tr>
			<th width="200">学科：</th>
			<td>
				${fns:getDictLabel(scExamScoreVo.courseId,'sc_course' ,null)}
			</td>
		</tr>
		<tr>
			<th width="200">备注信息：</th>
			<td>
				${scExamScoreVo.remarks}
			</td>
		</tr>
</body>
</html>