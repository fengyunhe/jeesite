/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fengyunhe.school.modules.cls.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.fengyunhe.school.modules.cls.entity.ScClass;

/**
 * 班级管理DAO接口
 * @author yangyan
 * @version 2016-10-23
 */
@MyBatisDao
public interface ScClassDao extends CrudDao<ScClass> {
	
}