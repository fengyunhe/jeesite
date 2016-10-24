/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fengyunhe.school.modules.exam.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 考试安排Entity
 * @author yangyan
 * @version 2016-10-24
 */
public class ScExamPlan extends DataEntity<ScExamPlan> {
	
	private static final long serialVersionUID = 1L;
	private Integer examId;		// 考试id
	private Integer semesterId;		// 学期id
	private Integer gradeId;		// 年级id
	private Integer courseId;		// 学科id
	private Date startTime;		// 开始考试时间
	private Date endTime;		// 结束考试时间
	private Integer teacher1Id;		// 监考老师
	private Integer teacher2Id;		// 监考老师2
	private Integer maxScore;		// 满分
	
	public ScExamPlan() {
		super();
	}

	public ScExamPlan(String id){
		super(id);
	}

	public Integer getExamId() {
		return examId;
	}

	public void setExamId(Integer examId) {
		this.examId = examId;
	}
	
	public Integer getSemesterId() {
		return semesterId;
	}

	public void setSemesterId(Integer semesterId) {
		this.semesterId = semesterId;
	}
	
	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
	
	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public Integer getTeacher1Id() {
		return teacher1Id;
	}

	public void setTeacher1Id(Integer teacher1Id) {
		this.teacher1Id = teacher1Id;
	}
	
	public Integer getTeacher2Id() {
		return teacher2Id;
	}

	public void setTeacher2Id(Integer teacher2Id) {
		this.teacher2Id = teacher2Id;
	}
	
	public Integer getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(Integer maxScore) {
		this.maxScore = maxScore;
	}
	
}