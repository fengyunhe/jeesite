package com.fengyunhe.school;

import com.fengyunhe.school.modules.student.dao.ScStudentDao;
import com.fengyunhe.school.modules.student.entity.ScStudent;
import com.fengyunhe.school.modules.teacher.dao.ScTeacherDao;
import com.fengyunhe.school.modules.teacher.entity.ScTeacher;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;

/**
 * Created by yangyan on 2016/10/30.
 */
public class NameUtils {
    public static ScTeacherDao scTeacherDao = SpringContextHolder.getBean(ScTeacherDao.class);
    public static ScStudentDao scStudentDao = SpringContextHolder.getBean(ScStudentDao.class);

    public static String getTeacherName(int teacherId) {
        ScTeacher scTeacher = scTeacherDao.get(teacherId);
        if (scTeacher != null) {
            return scTeacher.getName();
        }
        return null;
    }

    public static String getStudentName(int studentId) {
        ScStudent scStudent = scStudentDao.get(studentId);
        if (scStudent != null) {
            return scStudent.getName();
        }
        return null;
    }


}
