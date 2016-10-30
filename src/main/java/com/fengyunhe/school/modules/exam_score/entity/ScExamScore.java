/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fengyunhe.school.modules.exam_score.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 考试成绩Entity
 * @author yangyan
 * @version 2016-10-30
 */
public class ScExamScore extends DataEntity<ScExamScore> {
	
	private static final long serialVersionUID = 1L;
	private String studentId;		// 学生
	private String score;		// 成绩
	private String examType;		// 考试类型
	private String semesterId;		// 学期
	private String gradeId;		// 年级
	private String courseId;		// 学科
	private String beginScore;		// 开始 成绩
	private String endScore;		// 结束 成绩
	
	public ScExamScore() {
		super();
	}

	public ScExamScore(String id){
		super(id);
	}

	@Length(min=0, max=11, message="学生长度必须介于 0 和 11 之间")
	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
	@Length(min=0, max=11, message="成绩长度必须介于 0 和 11 之间")
	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	
	@Length(min=0, max=11, message="考试类型长度必须介于 0 和 11 之间")
	public String getExamType() {
		return examType;
	}

	public void setExamType(String examType) {
		this.examType = examType;
	}
	
	@Length(min=0, max=11, message="学期长度必须介于 0 和 11 之间")
	public String getSemesterId() {
		return semesterId;
	}

	public void setSemesterId(String semesterId) {
		this.semesterId = semesterId;
	}
	
	@Length(min=0, max=11, message="年级长度必须介于 0 和 11 之间")
	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	
	@Length(min=0, max=11, message="学科长度必须介于 0 和 11 之间")
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
	public String getBeginScore() {
		return beginScore;
	}

	public void setBeginScore(String beginScore) {
		this.beginScore = beginScore;
	}
	
	public String getEndScore() {
		return endScore;
	}

	public void setEndScore(String endScore) {
		this.endScore = endScore;
	}
		
}