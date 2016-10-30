/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fengyunhe.school.modules.vacation.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.fengyunhe.school.modules.vacation.entity.ScTeacherVacation;

/**
 * 请假DAO接口
 * @author yangyan
 * @version 2016-10-30
 */
@MyBatisDao
public interface ScTeacherVacationDao extends CrudDao<ScTeacherVacation> {
	
}