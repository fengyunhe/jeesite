/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fengyunhe.school.modules.teacher.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.fengyunhe.school.modules.teacher.entity.ScTeacher;
import com.fengyunhe.school.modules.teacher.vo.ScTeacherVo;
import org.springframework.beans.BeanUtils;
import com.fengyunhe.school.modules.teacher.dao.ScTeacherDao;

/**
 * 教师信息管理Service
 * @author yangyan
 * @version 2016-10-23
 */
@Service
@Transactional(readOnly = true)
public class ScTeacherService extends CrudService<ScTeacherDao, ScTeacher> {

	public ScTeacherVo get(String id) {
		ScTeacher po = super.get(id);
		return this.convertPoToVo(po);
	}
	
	public List<ScTeacherVo> findList(ScTeacherVo scTeacher) {
		List<ScTeacher> poList =  super.findList(scTeacher);
		return this.convertPoListToVoList(poList);
	}
	
	public Page<ScTeacherVo> findPage(Page page, ScTeacherVo test) {
        Page poPage = super.findPage(page, test);
        List<ScTeacherVo> voList = this.convertPoListToVoList(poPage.getList());
        poPage.setList(voList);
        return poPage;
    }
	
	@Transactional(readOnly = false)
	public void save(ScTeacherVo scTeacher) {
		super.save(scTeacher);
	}
	
	@Transactional(readOnly = false)
	public void delete(ScTeacherVo scTeacher) {
		super.delete(scTeacher);
	}


	public List<ScTeacherVo> convertPoListToVoList(List<ScTeacher> poList){
		if (poList != null) {
			List<ScTeacherVo> voList = new ArrayList<ScTeacherVo>();
			for (ScTeacher test : poList) {
				ScTeacherVo vo = this.convertPoToVo(test);
				voList.add(vo);
			}
			return voList;
		}
		return new ArrayList<ScTeacherVo>();
	}

	private ScTeacherVo convertPoToVo(ScTeacher po) {
		if (po == null) {
			return null;
		}
		ScTeacherVo vo = new ScTeacherVo();
		BeanUtils.copyProperties(po, vo);
		//TODO 设置其他vo中的属性
		return vo;
	}
	
}