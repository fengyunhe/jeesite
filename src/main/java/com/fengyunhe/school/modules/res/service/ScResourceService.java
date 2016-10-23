/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fengyunhe.school.modules.res.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.fengyunhe.school.modules.res.entity.ScResource;
import com.fengyunhe.school.modules.res.vo.ScResourceVo;
import org.springframework.beans.BeanUtils;
import com.fengyunhe.school.modules.res.dao.ScResourceDao;

/**
 * 教学物资管理Service
 * @author yangyan
 * @version 2016-10-23
 */
@Service
@Transactional(readOnly = true)
public class ScResourceService extends CrudService<ScResourceDao, ScResource> {

	public ScResourceVo get(String id) {
		ScResource po = super.get(id);
		return this.convertPoToVo(po);
	}
	
	public List<ScResourceVo> findList(ScResourceVo scResource) {
		List<ScResource> poList =  super.findList(scResource);
		return this.convertPoListToVoList(poList);
	}
	
	public Page<ScResourceVo> findPage(Page page, ScResourceVo test) {
        Page poPage = super.findPage(page, test);
        List<ScResourceVo> voList = this.convertPoListToVoList(poPage.getList());
        poPage.setList(voList);
        return poPage;
    }
	
	@Transactional(readOnly = false)
	public void save(ScResourceVo scResource) {
		super.save(scResource);
	}
	
	@Transactional(readOnly = false)
	public void delete(ScResourceVo scResource) {
		super.delete(scResource);
	}


	public List<ScResourceVo> convertPoListToVoList(List<ScResource> poList){
		if (poList != null) {
			List<ScResourceVo> voList = new ArrayList<ScResourceVo>();
			for (ScResource test : poList) {
				ScResourceVo vo = this.convertPoToVo(test);
				voList.add(vo);
			}
			return voList;
		}
		return new ArrayList<ScResourceVo>();
	}

	private ScResourceVo convertPoToVo(ScResource po) {
		if (po == null) {
			return null;
		}
		ScResourceVo vo = new ScResourceVo();
		BeanUtils.copyProperties(po, vo);
		//TODO 设置其他vo中的属性
		return vo;
	}
	
}