/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fengyunhe.school.modules.vacation.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.fengyunhe.school.modules.teacher.entity.ScTeacher;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 请假Entity
 * @author yangyan
 * @version 2016-10-30
 */
public class ScTeacherVacation extends DataEntity<ScTeacherVacation> {
	
	private static final long serialVersionUID = 1L;
	private Date requestDate;		// 申请请假时间
	private Date beginTime;		// 请假起始时间
	private Date endTime;		// 请假结束时间
	private Integer vacationType;		// 请假类型
	private String remark;		// 备注
	private Date realEndTime;		// 销假时间
	private Integer status;		// 状态
	private String procInstId;		// 流程引擎对应的id
	private Integer realDates;		// 实际请假天数
	private Integer teacherId;		// 教师id
	
	public ScTeacherVacation() {
		super();
	}

	public ScTeacherVacation(String id){
		super(id);
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="请假起始时间不能为空")
	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="请假结束时间不能为空")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@NotNull(message="请假类型不能为空")
	public Integer getVacationType() {
		return vacationType;
	}

	public void setVacationType(Integer vacationType) {
		this.vacationType = vacationType;
	}
	
	@Length(min=0, max=100, message="备注长度必须介于 0 和 100 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRealEndTime() {
		return realEndTime;
	}

	public void setRealEndTime(Date realEndTime) {
		this.realEndTime = realEndTime;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Length(min=1, max=20, message="流程引擎对应的id长度必须介于 1 和 20 之间")
	public String getProcInstId() {
		return procInstId;
	}

	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}
	
	public Integer getRealDates() {
		return realDates;
	}

	public void setRealDates(Integer realDates) {
		this.realDates = realDates;
	}
	
	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	
}