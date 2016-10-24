/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fengyunhe.school.modules.exam.vo;

import com.fengyunhe.school.modules.exam.entity.ScExamPlan;

/**
 * 考试安排EntityVo
 * @author yangyan
 * @version 2016-10-24
 */
public class ScExamPlanVo extends ScExamPlan {

    private String teacher1Name;
    private String teacher2Name;


    public String getTeacher1Name() {
        return teacher1Name;
    }

    public void setTeacher1Name(String teacher1Name) {
        this.teacher1Name = teacher1Name;
    }

    public String getTeacher2Name() {
        return teacher2Name;
    }

    public void setTeacher2Name(String teacher2Name) {
        this.teacher2Name = teacher2Name;
    }
}