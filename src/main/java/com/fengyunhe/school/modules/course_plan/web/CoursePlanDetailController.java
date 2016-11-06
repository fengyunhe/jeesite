package com.fengyunhe.school.modules.course_plan.web;

import com.fengyunhe.school.modules.cls.entity.ScClass;
import com.fengyunhe.school.modules.cls.service.ScClassService;
import com.fengyunhe.school.modules.course_plan.entity.ScCoursePlanDetail;
import com.google.common.collect.ImmutableMap;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.Dict;
import com.thinkgem.jeesite.modules.sys.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by yangyan on 2016/10/31.
 */

@Controller
@RequestMapping("${adminPath}/course/plan")
public class CoursePlanDetailController extends BaseController {

    @Autowired
    DictService dictService;

    @Autowired
    ScClassService scClassService;

    @RequestMapping("/list")
    public String list(ModelMap modelMap) {
        //年级
        Dict grade = new Dict();
        grade.setType("sc_grade");
        List<Dict> gradeDictList = dictService.findList(grade);


        Dict course = new Dict();
        course.setType("sc_course");
        List<Dict> courseDictList = dictService.findList(course);


        List<ImmutableMap> gradeList = new ArrayList<ImmutableMap>();


        Map<String, List<ImmutableMap<Object, Object>>> gradeClassMap = new HashMap<String, List<ImmutableMap<Object, Object>>>();

        //年级下的班级
        ScClass scClass = new ScClass();


        Page<ScClass> page = new Page<ScClass>();
        page.setOrderBy(" grade_id,seq");
        scClass.setPage(page);
        List<ScClass> classList = scClassService.findList(scClass);
        //班级的课程安排
        List<ScCoursePlanDetail> classCourseList = this.scClassService.getAllCoursePlan();


        Map<String, String> courseMap = new HashMap<String, String>();

        if (classCourseList != null) {
            for (ScCoursePlanDetail scCoursePlanDetail : classCourseList) {
                String key = String.format("course_%s_%s_%s", scCoursePlanDetail.getClassId(),
                        scCoursePlanDetail.getWeekday(),
                        scCoursePlanDetail.getSeq());
                courseMap.put(key, scCoursePlanDetail.getCourseId());
            }
        }


        if (classList != null) {
            for (ScClass aClass : classList) {
                List<ImmutableMap<Object, Object>> l = gradeClassMap.get(aClass.getGradeId());
                if (l == null) {
                    l = new ArrayList<ImmutableMap<Object, Object>>();
                    gradeClassMap.put(
                            aClass.getGradeId(),
                            l
                    );
                }
                l.add(ImmutableMap.builder()
                        .put("classId", aClass.getId())
                        .put("className", aClass.getName())
                        .build());
            }
        }

        if (gradeDictList != null) {
            for (Dict dict : gradeDictList) {
                List<ImmutableMap<Object, Object>> v2 = gradeClassMap.get(dict.getValue());
                if (v2 == null) {
                    v2 = Collections.EMPTY_LIST;
                }
                gradeList.add(ImmutableMap.of(
                        "gradeId", dict.getValue(),
                        "gradeName", dict.getLabel(),
                        "classList", v2
                ));
            }
        }


        modelMap.put("gradeList", gradeList);
        modelMap.put("courseDictList", courseDictList);
        modelMap.put("courseMap", courseMap);
        modelMap.put("perWeekDates", 5);
        modelMap.put("perDayClass", 8);

        return "modules/course_plan/scCoursePlan";
    }

    /**
     * 更新课程安排
     *
     * @param request
     * @return
     */
    @RequestMapping("/update")
    public String update(ModelMap map, HttpServletRequest request) {

        String gradeId = request.getParameter("gradeId");


        List<ScCoursePlanDetail> list = new ArrayList<ScCoursePlanDetail>();
        for (Object key : request.getParameterMap().keySet()) {
            String paramName = key
                    .toString();
            if (paramName.startsWith("course_")) {
                String[] split = paramName.split("_"); //value_${x}_${c.classId}_${courseSeq}
                if (split.length == 4) {
                    String weekDay = split[1];
                    String classId = split[2];
                    String seq = split[3];
                    String value = request.getParameter(paramName);
                    ScCoursePlanDetail planDetail = new ScCoursePlanDetail();
                    planDetail.setGradeId(gradeId);
                    planDetail.setClassId(Integer.valueOf(classId));
                    planDetail.setSeq(Integer.valueOf(seq));
                    planDetail.setCourseId(value);
                    planDetail.setWeekday(weekDay);
                    list.add(planDetail);
//                    planDetail.setSemesterId();
                }
            }

        }


        this.scClassService.updateCourseByGrade(gradeId, list);
        return "redirect:./list";
    }
}
