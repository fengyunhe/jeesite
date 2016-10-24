/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fengyunhe.school.modules.exam.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.sys.service.SystemService;
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
import com.fengyunhe.school.modules.exam.vo.ScExamPlanVo;
import com.fengyunhe.school.modules.exam.service.ScExamPlanService;

/**
 * 考试安排Controller
 *
 * @author yangyan
 * @version 2016-10-24
 */
@Controller
@RequestMapping(value = "${adminPath}/exam/scExamPlan")
public class ScExamPlanController extends BaseController {

    @Autowired
    private ScExamPlanService scExamPlanService;

    @Autowired
    private SystemService systemService;

    @ModelAttribute
    public ScExamPlanVo get(@RequestParam(required = false) String id) {
        ScExamPlanVo entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = scExamPlanService.get(id);
        }
        if (entity == null) {
            entity = new ScExamPlanVo();
        } else {
            if (entity.getTeacher1Id() != null) {
                entity.setTeacher1Name(systemService.getNameById(entity.getTeacher1Id()));
            }

            if (entity.getTeacher2Id() != null) {
                entity.setTeacher2Name(systemService.getNameById(entity.getTeacher2Id()));
            }
        }
        return entity;
    }

    @RequiresPermissions("exam:scExamPlan:view")
    @RequestMapping(value = {"list", ""})
    public String list(ScExamPlanVo scExamPlanVo, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<ScExamPlanVo> page = scExamPlanService.findPage(new Page<ScExamPlanVo>(request, response), scExamPlanVo);
        model.addAttribute("page", page);
        return "modules/exam/scExamPlanList";
    }

    @RequiresPermissions("exam:scExamPlan:view")
    @RequestMapping(value = "form")
    public String form(ScExamPlanVo scExamPlanVo, Model model) {
        //model.addAttribute("scExamPlan", scExamPlanVo);
        return "modules/exam/scExamPlanForm";
    }

    @RequiresPermissions("exam:scExamPlan:view")
    @RequestMapping(value = "show")
    public String show(ScExamPlanVo scExamPlanVo, Model model) {
        //model.addAttribute("scExamPlan", scExamPlanVo);
        return "modules/exam/scExamPlanShow";
    }

    @RequiresPermissions("exam:scExamPlan:edit")
    @RequestMapping(value = "save")
    public String save(ScExamPlanVo scExamPlanVo, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, scExamPlanVo)) {
            return form(scExamPlanVo, model);
        }
        scExamPlanService.save(scExamPlanVo);
        addMessage(redirectAttributes, "保存考试安排成功");
        return "redirect:" + Global.getAdminPath() + "/exam/scExamPlan/?repage";
    }

    @RequiresPermissions("exam:scExamPlan:edit")
    @RequestMapping(value = "delete")
    public String delete(ScExamPlanVo scExamPlanVo, RedirectAttributes redirectAttributes) {
        scExamPlanService.delete(scExamPlanVo);
        addMessage(redirectAttributes, "删除考试安排成功");
        return "redirect:" + Global.getAdminPath() + "/exam/scExamPlan/?repage";
    }

}