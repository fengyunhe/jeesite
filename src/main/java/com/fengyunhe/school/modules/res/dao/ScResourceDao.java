/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fengyunhe.school.modules.res.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.fengyunhe.school.modules.res.entity.ScResource;

/**
 * 教学物资管理DAO接口
 * @author yangyan
 * @version 2016-10-23
 */
@MyBatisDao
public interface ScResourceDao extends CrudDao<ScResource> {
	
}