/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fengyunhe.school.modules.student.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.fengyunhe.school.modules.student.entity.ScStudent;

/**
 * 学生信息管理DAO接口
 * @author yangyan
 * @version 2016-10-23
 */
@MyBatisDao
public interface ScStudentDao extends CrudDao<ScStudent> {
	
}