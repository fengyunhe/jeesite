/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fengyunhe.school.modules.course_plan.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 课程安排Entity
 * @author yangyan
 * @version 2016-10-31
 */
public class ScCoursePlanDetail extends DataEntity<ScCoursePlanDetail> {
	
	private static final long serialVersionUID = 1L;
	private String planId;		// 课程表
	private String semesterId;		// 学期
	private String classId;		// 班级
	private String courseId;		// 学科
	private Date studyDate;		// 日期
	private String teacherId;		// 代课老师
	private String seq;		// 课表顺序
	
	public ScCoursePlanDetail() {
		super();
	}

	public ScCoursePlanDetail(String id){
		super(id);
	}

	@Length(min=1, max=11, message="课程表长度必须介于 1 和 11 之间")
	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}
	
	@Length(min=0, max=11, message="学期长度必须介于 0 和 11 之间")
	public String getSemesterId() {
		return semesterId;
	}

	public void setSemesterId(String semesterId) {
		this.semesterId = semesterId;
	}
	
	@Length(min=0, max=11, message="班级长度必须介于 0 和 11 之间")
	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}
	
	@Length(min=0, max=11, message="学科长度必须介于 0 和 11 之间")
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStudyDate() {
		return studyDate;
	}

	public void setStudyDate(Date studyDate) {
		this.studyDate = studyDate;
	}
	
	@Length(min=0, max=11, message="代课老师长度必须介于 0 和 11 之间")
	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	
	@Length(min=0, max=11, message="课表顺序长度必须介于 0 和 11 之间")
	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}
	
}