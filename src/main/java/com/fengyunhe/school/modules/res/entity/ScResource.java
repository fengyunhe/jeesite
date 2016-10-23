/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fengyunhe.school.modules.res.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 教学物资管理Entity
 * @author yangyan
 * @version 2016-10-23
 */
public class ScResource extends DataEntity<ScResource> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private Integer resourceType;		// 类型
	private Integer total;		// 数量
	private Integer beginTotal;		// 开始 数量
	private Integer endTotal;		// 结束 数量
	
	public ScResource() {
		super();
	}

	public ScResource(String id){
		super(id);
	}

	@Length(min=1, max=45, message="名称长度必须介于 1 和 45 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@NotNull(message="类型不能为空")
	public Integer getResourceType() {
		return resourceType;
	}

	public void setResourceType(Integer resourceType) {
		this.resourceType = resourceType;
	}
	
	@NotNull(message="数量不能为空")
	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	
	public Integer getBeginTotal() {
		return beginTotal;
	}

	public void setBeginTotal(Integer beginTotal) {
		this.beginTotal = beginTotal;
	}
	
	public Integer getEndTotal() {
		return endTotal;
	}

	public void setEndTotal(Integer endTotal) {
		this.endTotal = endTotal;
	}
		
}