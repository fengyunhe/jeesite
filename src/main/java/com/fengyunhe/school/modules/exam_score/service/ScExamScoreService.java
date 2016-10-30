/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fengyunhe.school.modules.exam_score.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.fengyunhe.school.modules.exam_score.entity.ScExamScore;
import com.fengyunhe.school.modules.exam_score.vo.ScExamScoreVo;
import org.springframework.beans.BeanUtils;
import com.fengyunhe.school.modules.exam_score.dao.ScExamScoreDao;

/**
 * 考试成绩Service
 * @author yangyan
 * @version 2016-10-30
 */
@Service
@Transactional(readOnly = true)
public class ScExamScoreService extends CrudService<ScExamScoreDao, ScExamScore> {

	public ScExamScoreVo get(String id) {
		ScExamScore po = super.get(id);
		return this.convertPoToVo(po);
	}
	
	public List<ScExamScoreVo> findList(ScExamScoreVo scExamScore) {
		List<ScExamScore> poList =  super.findList(scExamScore);
		return this.convertPoListToVoList(poList);
	}
	
	public Page<ScExamScoreVo> findPage(Page page, ScExamScoreVo test) {
        Page poPage = super.findPage(page, test);
        List<ScExamScoreVo> voList = this.convertPoListToVoList(poPage.getList());
        poPage.setList(voList);
        return poPage;
    }
	
	@Transactional(readOnly = false)
	public void save(ScExamScoreVo scExamScore) {
		super.save(scExamScore);
	}
	
	@Transactional(readOnly = false)
	public void delete(ScExamScoreVo scExamScore) {
		super.delete(scExamScore);
	}


	public List<ScExamScoreVo> convertPoListToVoList(List<ScExamScore> poList){
		if (poList != null) {
			List<ScExamScoreVo> voList = new ArrayList<ScExamScoreVo>();
			for (ScExamScore test : poList) {
				ScExamScoreVo vo = this.convertPoToVo(test);
				voList.add(vo);
			}
			return voList;
		}
		return new ArrayList<ScExamScoreVo>();
	}

	private ScExamScoreVo convertPoToVo(ScExamScore po) {
		if (po == null) {
			return null;
		}
		ScExamScoreVo vo = new ScExamScoreVo();
		BeanUtils.copyProperties(po, vo);
		//TODO 设置其他vo中的属性
		return vo;
	}
	
}