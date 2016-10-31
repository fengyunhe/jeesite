/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fengyunhe.school.modules.student_event.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.fengyunhe.school.modules.student_event.entity.ScStudentEvent;

/**
 * 学生违纪事件DAO接口
 * @author yangyan
 * @version 2016-10-31
 */
@MyBatisDao
public interface ScStudentEventDao extends CrudDao<ScStudentEvent> {
	
}