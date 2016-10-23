/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fengyunhe.school.modules.teacher.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 教师信息管理Entity
 * @author yangyan
 * @version 2016-10-23
 */
public class ScTeacher extends DataEntity<ScTeacher> {
	
	private static final long serialVersionUID = 1L;
	private String teNo;		// 教师编号
	private String name;		// 姓名
	private String gender;		// 性别
	private String photoUrl;		// 头像
	private String phone;		// 手机号
	private String email;		// 邮箱
	private String address;		// 住址
	private String qq;		// QQ
	private String wechat;		// 微信号
	private String idNo;		// 身份证号
	private Integer courseId;		// 主要学科
	private Integer levelId;		// 职称等级
	private Integer jobId;		// 职务
	private Date birthday;		// 生日
	private Date beginBirthday;		// 开始 生日
	private Date endBirthday;		// 结束 生日
	
	public ScTeacher() {
		super();
	}

	public ScTeacher(String id){
		super(id);
	}

	@Length(min=0, max=10, message="教师编号长度必须介于 0 和 10 之间")
	public String getTeNo() {
		return teNo;
	}

	public void setTeNo(String teNo) {
		this.teNo = teNo;
	}
	
	@Length(min=1, max=30, message="姓名长度必须介于 1 和 30 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=1, message="性别长度必须介于 1 和 1 之间")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Length(min=0, max=2000, message="头像长度必须介于 0 和 2000 之间")
	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
	@Length(min=0, max=11, message="手机号长度必须介于 0 和 11 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=100, message="邮箱长度必须介于 0 和 100 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=0, max=200, message="住址长度必须介于 0 和 200 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=12, message="QQ长度必须介于 0 和 12 之间")
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}
	
	@Length(min=0, max=30, message="微信号长度必须介于 0 和 30 之间")
	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	
	@Length(min=0, max=18, message="身份证号长度必须介于 0 和 18 之间")
	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	
	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	
	public Integer getLevelId() {
		return levelId;
	}

	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}
	
	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public Date getBeginBirthday() {
		return beginBirthday;
	}

	public void setBeginBirthday(Date beginBirthday) {
		this.beginBirthday = beginBirthday;
	}
	
	public Date getEndBirthday() {
		return endBirthday;
	}

	public void setEndBirthday(Date endBirthday) {
		this.endBirthday = endBirthday;
	}
		
}