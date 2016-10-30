/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fengyunhe.school.modules.vacation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.fengyunhe.school.modules.vacation.entity.ScTeacherVacation;
import com.fengyunhe.school.modules.vacation.vo.ScTeacherVacationVo;
import org.springframework.beans.BeanUtils;
import com.fengyunhe.school.modules.vacation.dao.ScTeacherVacationDao;

/**
 * 请假Service
 * @author yangyan
 * @version 2016-10-30
 */
@Service
@Transactional(readOnly = true)
public class ScTeacherVacationService extends CrudService<ScTeacherVacationDao, ScTeacherVacation> {

	public ScTeacherVacationVo get(String id) {
		ScTeacherVacation po = super.get(id);
		return this.convertPoToVo(po);
	}
	
	public List<ScTeacherVacationVo> findList(ScTeacherVacationVo scTeacherVacation) {
		List<ScTeacherVacation> poList =  super.findList(scTeacherVacation);
		return this.convertPoListToVoList(poList);
	}
	
	public Page<ScTeacherVacationVo> findPage(Page page, ScTeacherVacationVo test) {
        Page poPage = super.findPage(page, test);
        List<ScTeacherVacationVo> voList = this.convertPoListToVoList(poPage.getList());
        poPage.setList(voList);
        return poPage;
    }
	
	@Transactional(readOnly = false)
	public void save(ScTeacherVacationVo scTeacherVacation) {
		super.save(scTeacherVacation);
	}
	
	@Transactional(readOnly = false)
	public void delete(ScTeacherVacationVo scTeacherVacation) {
		super.delete(scTeacherVacation);
	}


	public List<ScTeacherVacationVo> convertPoListToVoList(List<ScTeacherVacation> poList){
		if (poList != null) {
			List<ScTeacherVacationVo> voList = new ArrayList<ScTeacherVacationVo>();
			for (ScTeacherVacation test : poList) {
				ScTeacherVacationVo vo = this.convertPoToVo(test);
				voList.add(vo);
			}
			return voList;
		}
		return new ArrayList<ScTeacherVacationVo>();
	}

	private ScTeacherVacationVo convertPoToVo(ScTeacherVacation po) {
		if (po == null) {
			return null;
		}
		ScTeacherVacationVo vo = new ScTeacherVacationVo();
		BeanUtils.copyProperties(po, vo);
		//TODO 设置其他vo中的属性
		return vo;
	}
	
}