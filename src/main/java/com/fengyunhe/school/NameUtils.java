package com.fengyunhe.school;

import com.fengyunhe.school.modules.teacher.dao.ScTeacherDao;
import com.fengyunhe.school.modules.teacher.entity.ScTeacher;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;

/**
 * Created by yangyan on 2016/10/30.
 */
public class NameUtils {
    public static ScTeacherDao scTeacherDao = SpringContextHolder.getBean(ScTeacherDao.class);

    public static String getTeacherName(int teacherId) {
        ScTeacher scTeacher = scTeacherDao.get(teacherId);
        if (scTeacher != null) {
            return scTeacher.getName();
        }
        return null;
    }
}
