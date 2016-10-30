/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fengyunhe.school.modules.vacation.web;

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
import com.fengyunhe.school.modules.vacation.vo.ScTeacherVacationVo;
import com.fengyunhe.school.modules.vacation.service.ScTeacherVacationService;

/**
 * 请假Controller
 * @author yangyan
 * @version 2016-10-30
 */
@Controller
@RequestMapping(value = "${adminPath}/vacation/scTeacherVacation")
public class ScTeacherVacationController extends BaseController {

	@Autowired
	private ScTeacherVacationService scTeacherVacationService;
	
	@ModelAttribute
	public ScTeacherVacationVo get(@RequestParam(required=false) String id) {
		ScTeacherVacationVo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = scTeacherVacationService.get(id);
		}
		if (entity == null){
			entity = new ScTeacherVacationVo();
		}
		return entity;
	}
	
	@RequiresPermissions("vacation:scTeacherVacation:view")
	@RequestMapping(value = {"list", ""})
	public String list(ScTeacherVacationVo scTeacherVacationVo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ScTeacherVacationVo> page = scTeacherVacationService.findPage(new Page<ScTeacherVacationVo>(request, response), scTeacherVacationVo);
		model.addAttribute("page", page);
		return "modules/vacation/scTeacherVacationList";
	}

	@RequiresPermissions("vacation:scTeacherVacation:view")
	@RequestMapping(value = "form")
	public String form(ScTeacherVacationVo scTeacherVacationVo, Model model) {
		//model.addAttribute("scTeacherVacation", scTeacherVacationVo);
		return "modules/vacation/scTeacherVacationForm";
	}

	@RequiresPermissions("vacation:scTeacherVacation:view")
	@RequestMapping(value = "show")
	public String show(ScTeacherVacationVo scTeacherVacationVo, Model model) {
		//model.addAttribute("scTeacherVacation", scTeacherVacationVo);
		return "modules/vacation/scTeacherVacationShow";
	}

	@RequiresPermissions("vacation:scTeacherVacation:edit")
	@RequestMapping(value = "save")
	public String save(ScTeacherVacationVo scTeacherVacationVo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, scTeacherVacationVo)){
			return form(scTeacherVacationVo, model);
		}
		scTeacherVacationService.save(scTeacherVacationVo);
		addMessage(redirectAttributes, "保存请假申请成功");
		return "redirect:"+Global.getAdminPath()+"/vacation/scTeacherVacation/?repage";
	}
	
	@RequiresPermissions("vacation:scTeacherVacation:edit")
	@RequestMapping(value = "delete")
	public String delete(ScTeacherVacationVo scTeacherVacationVo, RedirectAttributes redirectAttributes) {
		scTeacherVacationService.delete(scTeacherVacationVo);
		addMessage(redirectAttributes, "删除请假申请成功");
		return "redirect:"+Global.getAdminPath()+"/vacation/scTeacherVacation/?repage";
	}

}