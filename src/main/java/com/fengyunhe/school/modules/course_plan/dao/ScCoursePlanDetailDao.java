/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fengyunhe.school.modules.course_plan.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.fengyunhe.school.modules.course_plan.entity.ScCoursePlanDetail;

/**
 * 课程安排DAO接口
 * @author yangyan
 * @version 2016-11-06
 */
@MyBatisDao
public interface ScCoursePlanDetailDao extends CrudDao<ScCoursePlanDetail> {
	
}