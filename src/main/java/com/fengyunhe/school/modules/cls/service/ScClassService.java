/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fengyunhe.school.modules.cls.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fengyunhe.school.modules.course_plan.dao.ScCoursePlanDetailDao;
import com.fengyunhe.school.modules.course_plan.entity.ScCoursePlanDetail;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.fengyunhe.school.modules.cls.entity.ScClass;
import com.fengyunhe.school.modules.cls.vo.ScClassVo;
import org.springframework.beans.BeanUtils;
import com.fengyunhe.school.modules.cls.dao.ScClassDao;

/**
 * 班级管理Service
 * @author yangyan
 * @version 2016-10-23
 */
@Service
@Transactional(readOnly = true)
public class ScClassService extends CrudService<ScClassDao, ScClass> {


	@Autowired
	private ScCoursePlanDetailDao scCoursePlanDetailDao;

	public ScClassVo get(String id) {
		ScClass po = super.get(id);
		return this.convertPoToVo(po);
	}
	
	public List<ScClassVo> findList(ScClassVo scClass) {
		List<ScClass> poList =  super.findList(scClass);
		return this.convertPoListToVoList(poList);
	}
	
	public Page<ScClassVo> findPage(Page page, ScClassVo test) {
        Page poPage = super.findPage(page, test);
        List<ScClassVo> voList = this.convertPoListToVoList(poPage.getList());
        poPage.setList(voList);
        return poPage;
    }
	
	@Transactional(readOnly = false)
	public void save(ScClassVo scClass) {
		super.save(scClass);
	}
	
	@Transactional(readOnly = false)
	public void delete(ScClassVo scClass) {
		super.delete(scClass);
	}


	public List<ScClassVo> convertPoListToVoList(List<ScClass> poList){
		if (poList != null) {
			List<ScClassVo> voList = new ArrayList<ScClassVo>();
			for (ScClass test : poList) {
				ScClassVo vo = this.convertPoToVo(test);
				voList.add(vo);
			}
			return voList;
		}
		return new ArrayList<ScClassVo>();
	}

	private ScClassVo convertPoToVo(ScClass po) {
		if (po == null) {
			return null;
		}
		ScClassVo vo = new ScClassVo();
		BeanUtils.copyProperties(po, vo);
		//TODO 设置其他vo中的属性
		return vo;
	}


	@Transactional
	public void updateCourseByGrade(String gradeId, List<ScCoursePlanDetail> list) {
		ScCoursePlanDetail deleteWhere = new ScCoursePlanDetail();
		deleteWhere.setGradeId(gradeId);
		scCoursePlanDetailDao.delete(deleteWhere);

		for (ScCoursePlanDetail planDetail : list) {
			planDetail.preInsert();
			scCoursePlanDetailDao.insert(planDetail);
		}
	}

	public List<ScCoursePlanDetail> getAllCoursePlan() {
		return scCoursePlanDetailDao.findAllList(new ScCoursePlanDetail());
	}
}