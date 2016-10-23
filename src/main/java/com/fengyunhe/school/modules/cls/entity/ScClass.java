/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fengyunhe.school.modules.cls.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 班级管理Entity
 * @author yangyan
 * @version 2016-10-23
 */
public class ScClass extends DataEntity<ScClass> {
	
	private static final long serialVersionUID = 1L;
	private String gradeId;		// 年级
	private String name;		// 班级
	private String seq;		// 顺序
	
	public ScClass() {
		super();
	}

	public ScClass(String id){
		super(id);
	}

	@Length(min=0, max=11, message="年级长度必须介于 0 和 11 之间")
	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	
	@Length(min=1, max=45, message="班级长度必须介于 1 和 45 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=10, message="顺序长度必须介于 0 和 10 之间")
	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}
	
}