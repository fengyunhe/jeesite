package com.fengyunhe.school;

import com.fengyunhe.school.modules.student.dao.ScStudentDao;
import com.fengyunhe.school.modules.student.entity.ScStudent;
import com.fengyunhe.school.modules.teacher.dao.ScTeacherDao;
import com.fengyunhe.school.modules.teacher.entity.ScTeacher;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;

import java.util.List;

/**
 * Created by yangyan on 2016/10/30.
 */
public class SelectUtils {
    public static ScTeacherDao scTeacherDao = SpringContextHolder.getBean(ScTeacherDao.class);

    public static ScStudentDao scStudentDao = SpringContextHolder.getBean(ScStudentDao.class);

    public static List<ScTeacher> getTeacherList() {
        return scTeacherDao.findAllList(new ScTeacher());
    }

    public static List<ScStudent> getStudentList(String name) {
        ScStudent scStudent = new ScStudent();
        scStudent.setName(name);
        return scStudentDao.findList(scStudent);
    }

}
