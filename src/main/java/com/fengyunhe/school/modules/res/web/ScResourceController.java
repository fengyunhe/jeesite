/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fengyunhe.school.modules.res.web;

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
import com.fengyunhe.school.modules.res.vo.ScResourceVo;
import com.fengyunhe.school.modules.res.service.ScResourceService;

/**
 * 教学物资管理Controller
 * @author yangyan
 * @version 2016-10-23
 */
@Controller
@RequestMapping(value = "${adminPath}/res/scResource")
public class ScResourceController extends BaseController {

	@Autowired
	private ScResourceService scResourceService;
	
	@ModelAttribute
	public ScResourceVo get(@RequestParam(required=false) String id) {
		ScResourceVo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = scResourceService.get(id);
		}
		if (entity == null){
			entity = new ScResourceVo();
		}
		return entity;
	}
	
	@RequiresPermissions("res:scResource:view")
	@RequestMapping(value = {"list", ""})
	public String list(ScResourceVo scResourceVo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ScResourceVo> page = scResourceService.findPage(new Page<ScResourceVo>(request, response), scResourceVo);
		model.addAttribute("page", page);
		return "modules/res/scResourceList";
	}

	@RequiresPermissions("res:scResource:view")
	@RequestMapping(value = "form")
	public String form(ScResourceVo scResourceVo, Model model) {
		//model.addAttribute("scResource", scResourceVo);
		return "modules/res/scResourceForm";
	}

	@RequiresPermissions("res:scResource:view")
	@RequestMapping(value = "show")
	public String show(ScResourceVo scResourceVo, Model model) {
		//model.addAttribute("scResource", scResourceVo);
		return "modules/res/scResourceShow";
	}

	@RequiresPermissions("res:scResource:edit")
	@RequestMapping(value = "save")
	public String save(ScResourceVo scResourceVo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, scResourceVo)){
			return form(scResourceVo, model);
		}
		scResourceService.save(scResourceVo);
		addMessage(redirectAttributes, "保存教学物资成功");
		return "redirect:"+Global.getAdminPath()+"/res/scResource/?repage";
	}
	
	@RequiresPermissions("res:scResource:edit")
	@RequestMapping(value = "delete")
	public String delete(ScResourceVo scResourceVo, RedirectAttributes redirectAttributes) {
		scResourceService.delete(scResourceVo);
		addMessage(redirectAttributes, "删除教学物资成功");
		return "redirect:"+Global.getAdminPath()+"/res/scResource/?repage";
	}

}