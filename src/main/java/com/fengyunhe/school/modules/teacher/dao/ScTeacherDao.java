/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fengyunhe.school.modules.teacher.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.fengyunhe.school.modules.teacher.entity.ScTeacher;

/**
 * 教师信息管理DAO接口
 * @author yangyan
 * @version 2016-10-23
 */
@MyBatisDao
public interface ScTeacherDao extends CrudDao<ScTeacher> {
	
}