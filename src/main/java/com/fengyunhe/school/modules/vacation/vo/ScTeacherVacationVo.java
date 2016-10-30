/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fengyunhe.school.modules.vacation.vo;

import com.fengyunhe.school.modules.vacation.entity.ScTeacherVacation;

import java.util.Date;

/**
 * 请假EntityVo
 *
 * @author yangyan
 * @version 2016-10-30
 */
public class ScTeacherVacationVo extends ScTeacherVacation {

    private String teacherName;

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    @Override
    public void preInsert() {
        super.preInsert();
        setStatus(1);
        setRequestDate(new Date());
    }
}