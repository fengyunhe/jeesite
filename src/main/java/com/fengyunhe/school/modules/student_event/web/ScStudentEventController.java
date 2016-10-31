/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fengyunhe.school.modules.student_event.web;

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
import com.fengyunhe.school.modules.student_event.vo.ScStudentEventVo;
import com.fengyunhe.school.modules.student_event.service.ScStudentEventService;

/**
 * 学生违纪事件Controller
 * @author yangyan
 * @version 2016-10-31
 */
@Controller
@RequestMapping(value = "${adminPath}/student_event/scStudentEvent")
public class ScStudentEventController extends BaseController {

	@Autowired
	private ScStudentEventService scStudentEventService;
	
	@ModelAttribute
	public ScStudentEventVo get(@RequestParam(required=false) String id) {
		ScStudentEventVo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = scStudentEventService.get(id);
		}
		if (entity == null){
			entity = new ScStudentEventVo();
		}
		return entity;
	}
	
	@RequiresPermissions("student_event:scStudentEvent:view")
	@RequestMapping(value = {"list", ""})
	public String list(ScStudentEventVo scStudentEventVo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ScStudentEventVo> page = scStudentEventService.findPage(new Page<ScStudentEventVo>(request, response), scStudentEventVo);
		model.addAttribute("page", page);
		return "modules/student_event/scStudentEventList";
	}

	@RequiresPermissions("student_event:scStudentEvent:view")
	@RequestMapping(value = "form")
	public String form(ScStudentEventVo scStudentEventVo, Model model) {
		//model.addAttribute("scStudentEvent", scStudentEventVo);
		return "modules/student_event/scStudentEventForm";
	}

	@RequiresPermissions("student_event:scStudentEvent:view")
	@RequestMapping(value = "show")
	public String show(ScStudentEventVo scStudentEventVo, Model model) {
		//model.addAttribute("scStudentEvent", scStudentEventVo);
		return "modules/student_event/scStudentEventShow";
	}

	@RequiresPermissions("student_event:scStudentEvent:edit")
	@RequestMapping(value = "save")
	public String save(ScStudentEventVo scStudentEventVo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, scStudentEventVo)){
			return form(scStudentEventVo, model);
		}
		scStudentEventService.save(scStudentEventVo);
		addMessage(redirectAttributes, "保存学生违纪事件成功");
		return "redirect:"+Global.getAdminPath()+"/student_event/scStudentEvent/?repage";
	}
	
	@RequiresPermissions("student_event:scStudentEvent:edit")
	@RequestMapping(value = "delete")
	public String delete(ScStudentEventVo scStudentEventVo, RedirectAttributes redirectAttributes) {
		scStudentEventService.delete(scStudentEventVo);
		addMessage(redirectAttributes, "删除学生违纪事件成功");
		return "redirect:"+Global.getAdminPath()+"/student_event/scStudentEvent/?repage";
	}

}