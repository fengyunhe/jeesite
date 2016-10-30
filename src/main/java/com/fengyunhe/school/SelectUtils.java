package com.fengyunhe.school;

import com.fengyunhe.school.modules.teacher.dao.ScTeacherDao;
import com.fengyunhe.school.modules.teacher.entity.ScTeacher;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;

import java.util.List;

/**
 * Created by yangyan on 2016/10/30.
 */
public class SelectUtils {
    public static ScTeacherDao scTeacherDao = SpringContextHolder.getBean(ScTeacherDao.class);

    public static List<ScTeacher> getTeacherList(){
        return scTeacherDao.findAllList(new ScTeacher());
    };
}
