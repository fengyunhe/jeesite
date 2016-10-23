/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fengyunhe.school.modules.student.web;

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
import com.fengyunhe.school.modules.student.vo.ScStudentVo;
import com.fengyunhe.school.modules.student.service.ScStudentService;

/**
 * 学生信息管理Controller
 * @author yangyan
 * @version 2016-10-23
 */
@Controller
@RequestMapping(value = "${adminPath}/student/scStudent")
public class ScStudentController extends BaseController {

	@Autowired
	private ScStudentService scStudentService;
	
	@ModelAttribute
	public ScStudentVo get(@RequestParam(required=false) String id) {
		ScStudentVo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = scStudentService.get(id);
		}
		if (entity == null){
			entity = new ScStudentVo();
		}
		return entity;
	}
	
	@RequiresPermissions("student:scStudent:view")
	@RequestMapping(value = {"list", ""})
	public String list(ScStudentVo scStudentVo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ScStudentVo> page = scStudentService.findPage(new Page<ScStudentVo>(request, response), scStudentVo);
		model.addAttribute("page", page);
		return "modules/student/scStudentList";
	}

	@RequiresPermissions("student:scStudent:view")
	@RequestMapping(value = "form")
	public String form(ScStudentVo scStudentVo, Model model) {
		//model.addAttribute("scStudent", scStudentVo);
		return "modules/student/scStudentForm";
	}

	@RequiresPermissions("student:scStudent:view")
	@RequestMapping(value = "show")
	public String show(ScStudentVo scStudentVo, Model model) {
		//model.addAttribute("scStudent", scStudentVo);
		return "modules/student/scStudentShow";
	}

	@RequiresPermissions("student:scStudent:edit")
	@RequestMapping(value = "save")
	public String save(ScStudentVo scStudentVo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, scStudentVo)){
			return form(scStudentVo, model);
		}
		scStudentService.save(scStudentVo);
		addMessage(redirectAttributes, "保存学生信息成功");
		return "redirect:"+Global.getAdminPath()+"/student/scStudent/?repage";
	}
	
	@RequiresPermissions("student:scStudent:edit")
	@RequestMapping(value = "delete")
	public String delete(ScStudentVo scStudentVo, RedirectAttributes redirectAttributes) {
		scStudentService.delete(scStudentVo);
		addMessage(redirectAttributes, "删除学生信息成功");
		return "redirect:"+Global.getAdminPath()+"/student/scStudent/?repage";
	}

}