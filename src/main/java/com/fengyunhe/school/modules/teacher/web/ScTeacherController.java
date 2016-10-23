/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fengyunhe.school.modules.teacher.web;

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
import com.fengyunhe.school.modules.teacher.vo.ScTeacherVo;
import com.fengyunhe.school.modules.teacher.service.ScTeacherService;

/**
 * 教师信息管理Controller
 * @author yangyan
 * @version 2016-10-23
 */
@Controller
@RequestMapping(value = "${adminPath}/teacher/scTeacher")
public class ScTeacherController extends BaseController {

	@Autowired
	private ScTeacherService scTeacherService;
	
	@ModelAttribute
	public ScTeacherVo get(@RequestParam(required=false) String id) {
		ScTeacherVo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = scTeacherService.get(id);
		}
		if (entity == null){
			entity = new ScTeacherVo();
		}
		return entity;
	}
	
	@RequiresPermissions("teacher:scTeacher:view")
	@RequestMapping(value = {"list", ""})
	public String list(ScTeacherVo scTeacherVo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ScTeacherVo> page = scTeacherService.findPage(new Page<ScTeacherVo>(request, response), scTeacherVo);
		model.addAttribute("page", page);
		return "modules/teacher/scTeacherList";
	}

	@RequiresPermissions("teacher:scTeacher:view")
	@RequestMapping(value = "form")
	public String form(ScTeacherVo scTeacherVo, Model model) {
		//model.addAttribute("scTeacher", scTeacherVo);
		return "modules/teacher/scTeacherForm";
	}

	@RequiresPermissions("teacher:scTeacher:view")
	@RequestMapping(value = "show")
	public String show(ScTeacherVo scTeacherVo, Model model) {
		//model.addAttribute("scTeacher", scTeacherVo);
		return "modules/teacher/scTeacherShow";
	}

	@RequiresPermissions("teacher:scTeacher:edit")
	@RequestMapping(value = "save")
	public String save(ScTeacherVo scTeacherVo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, scTeacherVo)){
			return form(scTeacherVo, model);
		}
		scTeacherService.save(scTeacherVo);
		addMessage(redirectAttributes, "保存教师信息成功");
		return "redirect:"+Global.getAdminPath()+"/teacher/scTeacher/?repage";
	}
	
	@RequiresPermissions("teacher:scTeacher:edit")
	@RequestMapping(value = "delete")
	public String delete(ScTeacherVo scTeacherVo, RedirectAttributes redirectAttributes) {
		scTeacherService.delete(scTeacherVo);
		addMessage(redirectAttributes, "删除教师信息成功");
		return "redirect:"+Global.getAdminPath()+"/teacher/scTeacher/?repage";
	}

}