/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fengyunhe.school.modules.exam.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.fengyunhe.school.modules.exam.entity.ScExamPlan;

/**
 * 考试安排DAO接口
 * @author yangyan
 * @version 2016-10-24
 */
@MyBatisDao
public interface ScExamPlanDao extends CrudDao<ScExamPlan> {
	
}