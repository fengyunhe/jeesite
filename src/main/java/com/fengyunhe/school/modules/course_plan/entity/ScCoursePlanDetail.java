/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fengyunhe.school.modules.course_plan.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 课程安排Entity
 * @author yangyan
 * @version 2016-11-06
 */
public class ScCoursePlanDetail extends DataEntity<ScCoursePlanDetail> {
	
	private static final long serialVersionUID = 1L;
	private String semesterId;		// 学期
	private Integer classId;		// 班级
	private String weekday;		// 星期中的某一天，1=星期一，2=星期二，以此类推，字典中有
	private String courseId;		// 学科
	private Integer teacherId;		// 代课老师
	private Integer seq;		// 课表顺序
	private String gradeId;		// 年级
	
	public ScCoursePlanDetail() {
		super();
	}

	public ScCoursePlanDetail(String id){
		super(id);
	}

	@Length(min=0, max=11, message="学期长度必须介于 0 和 11 之间")
	public String getSemesterId() {
		return semesterId;
	}

	public void setSemesterId(String semesterId) {
		this.semesterId = semesterId;
	}
	
	@Length(min=0, max=11, message="班级长度必须介于 0 和 11 之间")
	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	
	@Length(min=0, max=1, message="星期中的某一天，1=星期一，2=星期二，以此类推，字典中有长度必须介于 0 和 1 之间")
	public String getWeekday() {
		return weekday;
	}

	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}
	
	@Length(min=0, max=11, message="学科长度必须介于 0 和 11 之间")
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
	@Length(min=0, max=11, message="代课老师长度必须介于 0 和 11 之间")
	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	
	@Length(min=0, max=11, message="课表顺序长度必须介于 0 和 11 之间")
	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	
	@Length(min=0, max=30, message="年级长度必须介于 0 和 30 之间")
	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	
}