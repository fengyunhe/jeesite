/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fengyunhe.school.modules.student_event.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 学生违纪事件Entity
 * @author yangyan
 * @version 2016-10-31
 */
public class ScStudentEvent extends DataEntity<ScStudentEvent> {
	
	private static final long serialVersionUID = 1L;
	private Date eventTime;		// 事件发生事件
	private String eventType;		// 事件类型
	private String content;		// 内容
	private String studentId;		// 学生
	private Date beginEventTime;		// 开始 事件发生事件
	private Date endEventTime;		// 结束 事件发生事件
	
	public ScStudentEvent() {
		super();
	}

	public ScStudentEvent(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="事件发生事件不能为空")
	public Date getEventTime() {
		return eventTime;
	}

	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}
	
	@Length(min=1, max=11, message="事件类型长度必须介于 1 和 11 之间")
	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	
	@Length(min=1, max=500, message="内容长度必须介于 1 和 500 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=1, max=11, message="学生长度必须介于 1 和 11 之间")
	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
	public Date getBeginEventTime() {
		return beginEventTime;
	}

	public void setBeginEventTime(Date beginEventTime) {
		this.beginEventTime = beginEventTime;
	}
	
	public Date getEndEventTime() {
		return endEventTime;
	}

	public void setEndEventTime(Date endEventTime) {
		this.endEventTime = endEventTime;
	}
		
}