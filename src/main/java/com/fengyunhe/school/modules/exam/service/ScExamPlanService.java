/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fengyunhe.school.modules.exam.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.fengyunhe.school.modules.exam.entity.ScExamPlan;
import com.fengyunhe.school.modules.exam.vo.ScExamPlanVo;
import org.springframework.beans.BeanUtils;
import com.fengyunhe.school.modules.exam.dao.ScExamPlanDao;

/**
 * 考试安排Service
 * @author yangyan
 * @version 2016-10-24
 */
@Service
@Transactional(readOnly = true)
public class ScExamPlanService extends CrudService<ScExamPlanDao, ScExamPlan> {

	public ScExamPlanVo get(String id) {
		ScExamPlan po = super.get(id);
		return this.convertPoToVo(po);
	}
	
	public List<ScExamPlanVo> findList(ScExamPlanVo scExamPlan) {
		List<ScExamPlan> poList =  super.findList(scExamPlan);
		return this.convertPoListToVoList(poList);
	}
	
	public Page<ScExamPlanVo> findPage(Page page, ScExamPlanVo test) {
        Page poPage = super.findPage(page, test);
        List<ScExamPlanVo> voList = this.convertPoListToVoList(poPage.getList());
        poPage.setList(voList);
        return poPage;
    }
	
	@Transactional(readOnly = false)
	public void save(ScExamPlanVo scExamPlan) {
		super.save(scExamPlan);
	}
	
	@Transactional(readOnly = false)
	public void delete(ScExamPlanVo scExamPlan) {
		super.delete(scExamPlan);
	}


	public List<ScExamPlanVo> convertPoListToVoList(List<ScExamPlan> poList){
		if (poList != null) {
			List<ScExamPlanVo> voList = new ArrayList<ScExamPlanVo>();
			for (ScExamPlan test : poList) {
				ScExamPlanVo vo = this.convertPoToVo(test);
				voList.add(vo);
			}
			return voList;
		}
		return new ArrayList<ScExamPlanVo>();
	}

	private ScExamPlanVo convertPoToVo(ScExamPlan po) {
		if (po == null) {
			return null;
		}
		ScExamPlanVo vo = new ScExamPlanVo();
		BeanUtils.copyProperties(po, vo);
		//TODO 设置其他vo中的属性
		return vo;
	}
	
}