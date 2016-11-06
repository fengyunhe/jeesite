<%--
  Created by IntelliJ IDEA.
  User: yangyan
  Date: 2016/10/31
  Time: 下午9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>

<html>
<head>
    <title>课程表设计</title>
    <meta name="decorator" content="default"/>

    <style>
        .scClass > td {
            text-align: center;
        }

        .course_table tr {
            height: 30px;
        }

        .course_table td {
            padding: 0px;
            margin: 0px;
            display: table-cell;
            vertical-align: middle;
        }

        .course_table td select {
            width: 100%;
            /*margin-top:10px;*/
            margin-bottom: 0px;

        }
    </style>
</head>
<body>

<div>
    <c:forEach items="${gradeList}" var="g" varStatus="status">
        <c:if test="${not empty g.classList }">
            <form action="${ctx}/course/plan/update" method="post">
                <h2 style="margin:10px auto;">${g.gradeName} 课程安排：</h2>
                <input type="hidden" name="gradeId" value="${g.gradeId}" />
                <table class="course_table table table-striped table-bordered table-condensed">
                    <thead>
                    <tr>
                        <th width="30" colspan="2" rowspan="2">顺序</th>
                        <c:forEach begin="1" end="${perWeekDates}" var="x">
                            <th colspan="4">星期${x}</th>
                        </c:forEach>
                    </tr>
                    <tr class="scClass" style="background: #d3f5ff">
                        <c:forEach begin="1" end="${perWeekDates}" var="x">
                            <c:forEach items="${g.classList}" var="c">
                                <td>${c.className}</td>
                            </c:forEach>
                        </c:forEach>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach begin="1" end="${perDayClass}" var="courseSeq">
                        <tr>
                            <c:if test="${courseSeq==1}">
                                <th rowspan="4" width="20">上午</th>
                            </c:if>
                            <c:if test="${perDayClass/2-courseSeq==-1}">
                                <th rowspan="4">下午</th>
                            </c:if>
                            <th width="10">${courseSeq}</th>
                            <c:forEach begin="1" end="${perWeekDates}" var="x">
                                <c:forEach items="${g.classList}" var="c">
                                    <td>
                                        <select class="native courseSelect" name="course_${x}_${c.classId}_${courseSeq}">
                                            <c:forEach items="${courseDictList}" var="cou">
                                                <option value="${cou.value}">${cou.label}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </c:forEach>
                            </c:forEach>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div>
                    <button class="btn btn-primary save">保存</button>
                </div>
            </form>
        </c:if>
    </c:forEach>
</div>
</body>
</html>
