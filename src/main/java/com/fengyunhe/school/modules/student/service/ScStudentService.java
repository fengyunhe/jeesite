/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fengyunhe.school.modules.student.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.fengyunhe.school.modules.student.entity.ScStudent;
import com.fengyunhe.school.modules.student.vo.ScStudentVo;
import org.springframework.beans.BeanUtils;
import com.fengyunhe.school.modules.student.dao.ScStudentDao;

/**
 * 学生信息管理Service
 * @author yangyan
 * @version 2016-10-23
 */
@Service
@Transactional(readOnly = true)
public class ScStudentService extends CrudService<ScStudentDao, ScStudent> {

	public ScStudentVo get(String id) {
		ScStudent po = super.get(id);
		return this.convertPoToVo(po);
	}
	
	public List<ScStudentVo> findList(ScStudentVo scStudent) {
		List<ScStudent> poList =  super.findList(scStudent);
		return this.convertPoListToVoList(poList);
	}
	
	public Page<ScStudentVo> findPage(Page page, ScStudentVo test) {
        Page poPage = super.findPage(page, test);
        List<ScStudentVo> voList = this.convertPoListToVoList(poPage.getList());
        poPage.setList(voList);
        return poPage;
    }
	
	@Transactional(readOnly = false)
	public void save(ScStudentVo scStudent) {
		super.save(scStudent);
	}
	
	@Transactional(readOnly = false)
	public void delete(ScStudentVo scStudent) {
		super.delete(scStudent);
	}


	public List<ScStudentVo> convertPoListToVoList(List<ScStudent> poList){
		if (poList != null) {
			List<ScStudentVo> voList = new ArrayList<ScStudentVo>();
			for (ScStudent test : poList) {
				ScStudentVo vo = this.convertPoToVo(test);
				voList.add(vo);
			}
			return voList;
		}
		return new ArrayList<ScStudentVo>();
	}

	private ScStudentVo convertPoToVo(ScStudent po) {
		if (po == null) {
			return null;
		}
		ScStudentVo vo = new ScStudentVo();
		BeanUtils.copyProperties(po, vo);
		//TODO 设置其他vo中的属性
		return vo;
	}
	
}