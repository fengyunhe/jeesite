/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fengyunhe.school.modules.student.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 学生信息管理Entity
 * @author yangyan
 * @version 2016-10-23
 */
public class ScStudent extends DataEntity<ScStudent> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 姓名
	private String stNo;		// 学号
	private String photoUrl;		// 照片
	private String gender;		// 性别
	private String idNo;		// 身份证号码
	private Date birthday;		// 生日
	private String wechat;		// 微信
	private String qq;		// QQ
	private String fatherName;		// 父亲姓名
	private String motherName;		// 母亲姓名
	private String fatherPhone;		// 父亲电话
	private String motherPhone;		// 母亲电话
	private String fatherJob;		// 父亲职业
	private String motherJob;		// 母亲职业
	
	public ScStudent() {
		super();
	}

	public ScStudent(String id){
		super(id);
	}

	@Length(min=1, max=30, message="姓名长度必须介于 1 和 30 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=30, message="学号长度必须介于 1 和 30 之间")
	public String getStNo() {
		return stNo;
	}

	public void setStNo(String stNo) {
		this.stNo = stNo;
	}
	
	@Length(min=0, max=200, message="照片长度必须介于 0 和 200 之间")
	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
	@Length(min=1, max=1, message="性别长度必须介于 1 和 1 之间")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Length(min=0, max=18, message="身份证号码长度必须介于 0 和 18 之间")
	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Length(min=0, max=45, message="微信长度必须介于 0 和 45 之间")
	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	
	@Length(min=0, max=12, message="QQ长度必须介于 0 和 12 之间")
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}
	
	@Length(min=0, max=45, message="父亲姓名长度必须介于 0 和 45 之间")
	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	
	@Length(min=0, max=45, message="母亲姓名长度必须介于 0 和 45 之间")
	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	
	@Length(min=0, max=11, message="父亲电话长度必须介于 0 和 11 之间")
	public String getFatherPhone() {
		return fatherPhone;
	}

	public void setFatherPhone(String fatherPhone) {
		this.fatherPhone = fatherPhone;
	}
	
	@Length(min=0, max=11, message="母亲电话长度必须介于 0 和 11 之间")
	public String getMotherPhone() {
		return motherPhone;
	}

	public void setMotherPhone(String motherPhone) {
		this.motherPhone = motherPhone;
	}
	
	@Length(min=0, max=45, message="父亲职业长度必须介于 0 和 45 之间")
	public String getFatherJob() {
		return fatherJob;
	}

	public void setFatherJob(String fatherJob) {
		this.fatherJob = fatherJob;
	}
	
	@Length(min=0, max=45, message="母亲职业长度必须介于 0 和 45 之间")
	public String getMotherJob() {
		return motherJob;
	}

	public void setMotherJob(String motherJob) {
		this.motherJob = motherJob;
	}
	
}