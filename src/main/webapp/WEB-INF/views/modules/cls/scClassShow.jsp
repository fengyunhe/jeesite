<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>班级信息管理</title>
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
		<li><a href="${ctx}/cls/scClass/">班级信息列表</a></li>
		<li class="active"><a href="${ctx}/cls/scClass/form?id=${scClassVo.id}">班级信息查看</a></li>
	</ul><br/>
<table class="table">
		<tr>
			<td colspan="2"><input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/></td>
		</tr>
		<tr>
			<th width="200">年级：</th>
			<td>
				${fns:getDictLabel(scClassVo.gradeId,'sc_grade' ,null)}
			</td>
		</tr>
		<tr>
			<th width="200">班级：</th>
			<td>
				${scClassVo.name}
				<span class="help-inline"><font color="red">*</font> </span>
			</td>
		</tr>
		<tr>
			<th width="200">顺序：</th>
			<td>
				${scClassVo.seq}
			</td>
		</tr>
		<tr>
			<th width="200">备注信息：</th>
			<td>
				${scClassVo.remarks}
			</td>
		</tr>
</body>
</html>