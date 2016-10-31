package com.fengyunhe.school.modules.course_plan.web;

import com.thinkgem.jeesite.common.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yangyan on 2016/10/31.
 */

@Controller
@RequestMapping("${adminPath}/course/plan")
public class CoursePlanDetailController extends BaseController {

    @RequestMapping("/list")
    public String list(){
        return "modules/course_plan/scCoursePlan";
    }
}
