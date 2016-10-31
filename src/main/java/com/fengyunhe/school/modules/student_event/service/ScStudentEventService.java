/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fengyunhe.school.modules.student_event.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.fengyunhe.school.modules.student_event.entity.ScStudentEvent;
import com.fengyunhe.school.modules.student_event.vo.ScStudentEventVo;
import org.springframework.beans.BeanUtils;
import com.fengyunhe.school.modules.student_event.dao.ScStudentEventDao;

/**
 * 学生违纪事件Service
 * @author yangyan
 * @version 2016-10-31
 */
@Service
@Transactional(readOnly = true)
public class ScStudentEventService extends CrudService<ScStudentEventDao, ScStudentEvent> {

	public ScStudentEventVo get(String id) {
		ScStudentEvent po = super.get(id);
		return this.convertPoToVo(po);
	}
	
	public List<ScStudentEventVo> findList(ScStudentEventVo scStudentEvent) {
		List<ScStudentEvent> poList =  super.findList(scStudentEvent);
		return this.convertPoListToVoList(poList);
	}
	
	public Page<ScStudentEventVo> findPage(Page page, ScStudentEventVo test) {
        Page poPage = super.findPage(page, test);
        List<ScStudentEventVo> voList = this.convertPoListToVoList(poPage.getList());
        poPage.setList(voList);
        return poPage;
    }
	
	@Transactional(readOnly = false)
	public void save(ScStudentEventVo scStudentEvent) {
		super.save(scStudentEvent);
	}
	
	@Transactional(readOnly = false)
	public void delete(ScStudentEventVo scStudentEvent) {
		super.delete(scStudentEvent);
	}


	public List<ScStudentEventVo> convertPoListToVoList(List<ScStudentEvent> poList){
		if (poList != null) {
			List<ScStudentEventVo> voList = new ArrayList<ScStudentEventVo>();
			for (ScStudentEvent test : poList) {
				ScStudentEventVo vo = this.convertPoToVo(test);
				voList.add(vo);
			}
			return voList;
		}
		return new ArrayList<ScStudentEventVo>();
	}

	private ScStudentEventVo convertPoToVo(ScStudentEvent po) {
		if (po == null) {
			return null;
		}
		ScStudentEventVo vo = new ScStudentEventVo();
		BeanUtils.copyProperties(po, vo);
		//TODO 设置其他vo中的属性
		return vo;
	}
	
}