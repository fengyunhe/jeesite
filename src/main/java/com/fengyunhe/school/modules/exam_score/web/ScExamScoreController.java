/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fengyunhe.school.modules.exam_score.web;

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
import com.fengyunhe.school.modules.exam_score.vo.ScExamScoreVo;
import com.fengyunhe.school.modules.exam_score.service.ScExamScoreService;

/**
 * 考试成绩Controller
 * @author yangyan
 * @version 2016-10-30
 */
@Controller
@RequestMapping(value = "${adminPath}/exam_score/scExamScore")
public class ScExamScoreController extends BaseController {

	@Autowired
	private ScExamScoreService scExamScoreService;
	
	@ModelAttribute
	public ScExamScoreVo get(@RequestParam(required=false) String id) {
		ScExamScoreVo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = scExamScoreService.get(id);
		}
		if (entity == null){
			entity = new ScExamScoreVo();
		}
		return entity;
	}
	
	@RequiresPermissions("exam_score:scExamScore:view")
	@RequestMapping(value = {"list", ""})
	public String list(ScExamScoreVo scExamScoreVo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ScExamScoreVo> page = scExamScoreService.findPage(new Page<ScExamScoreVo>(request, response), scExamScoreVo);
		model.addAttribute("page", page);
		return "modules/exam_score/scExamScoreList";
	}

	@RequiresPermissions("exam_score:scExamScore:view")
	@RequestMapping(value = "form")
	public String form(ScExamScoreVo scExamScoreVo, Model model) {
		//model.addAttribute("scExamScore", scExamScoreVo);
		return "modules/exam_score/scExamScoreForm";
	}

	@RequiresPermissions("exam_score:scExamScore:view")
	@RequestMapping(value = "show")
	public String show(ScExamScoreVo scExamScoreVo, Model model) {
		//model.addAttribute("scExamScore", scExamScoreVo);
		return "modules/exam_score/scExamScoreShow";
	}

	@RequiresPermissions("exam_score:scExamScore:edit")
	@RequestMapping(value = "save")
	public String save(ScExamScoreVo scExamScoreVo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, scExamScoreVo)){
			return form(scExamScoreVo, model);
		}
		scExamScoreService.save(scExamScoreVo);
		addMessage(redirectAttributes, "保存考试成绩成功");
		return "redirect:"+Global.getAdminPath()+"/exam_score/scExamScore/?repage";
	}
	
	@RequiresPermissions("exam_score:scExamScore:edit")
	@RequestMapping(value = "delete")
	public String delete(ScExamScoreVo scExamScoreVo, RedirectAttributes redirectAttributes) {
		scExamScoreService.delete(scExamScoreVo);
		addMessage(redirectAttributes, "删除考试成绩成功");
		return "redirect:"+Global.getAdminPath()+"/exam_score/scExamScore/?repage";
	}

}