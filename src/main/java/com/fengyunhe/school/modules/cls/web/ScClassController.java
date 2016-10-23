/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fengyunhe.school.modules.cls.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.fengyunhe.school.modules.cls.vo.ScClassVo;
import com.fengyunhe.school.modules.cls.service.ScClassService;

/**
 * 班级管理Controller
 * @author yangyan
 * @version 2016-10-23
 */
@Controller
@RequestMapping(value = "${adminPath}/cls/scClass")
public class ScClassController extends BaseController {

	@Autowired
	private ScClassService scClassService;
	
	@ModelAttribute
	public ScClassVo get(@RequestParam(required=false) String id) {
		ScClassVo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = scClassService.get(id);
		}
		if (entity == null){
			entity = new ScClassVo();
		}
		return entity;
	}
	
	@RequiresPermissions("cls:scClass:view")
	@RequestMapping(value = {"list", ""})
	public String list(ScClassVo scClassVo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ScClassVo> page = scClassService.findPage(new Page<ScClassVo>(request, response), scClassVo);
		model.addAttribute("page", page);
		return "modules/cls/scClassList";
	}

	@RequiresPermissions("cls:scClass:view")
	@RequestMapping(value = "form")
	public String form(ScClassVo scClassVo, Model model) {
		//model.addAttribute("scClass", scClassVo);
		return "modules/cls/scClassForm";
	}

	@RequiresPermissions("cls:scClass:view")
	@RequestMapping(value = "show")
	public String show(ScClassVo scClassVo, Model model) {
		//model.addAttribute("scClass", scClassVo);
		return "modules/cls/scClassShow";
	}

	@RequiresPermissions("cls:scClass:edit")
	@RequestMapping(value = "save")
	public String save(ScClassVo scClassVo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, scClassVo)){
			return form(scClassVo, model);
		}
		scClassService.save(scClassVo);
		addMessage(redirectAttributes, "保存班级信息成功");
		return "redirect:"+Global.getAdminPath()+"/cls/scClass/?repage";
	}
	
	@RequiresPermissions("cls:scClass:edit")
	@RequestMapping(value = "delete")
	public String delete(ScClassVo scClassVo, RedirectAttributes redirectAttributes) {
		scClassService.delete(scClassVo);
		addMessage(redirectAttributes, "删除班级信息成功");
		return "redirect:"+Global.getAdminPath()+"/cls/scClass/?repage";
	}

}