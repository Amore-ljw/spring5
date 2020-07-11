package com.amore.spring5.principle.lod;

import java.util.ArrayList;
import java.util.List;

public class TeamLeader {

//    public void checkNumberOfCourse(List<Course> list) {
//        System.out.println("目前已经发布的课程数量：" + list == null ? 0 : list.size());
//    }

    public void checkNumberOfCourse() {
        List<Course> list = new ArrayList<>();
        for (int i=0;i<20;i++) {
            list.add(new Course());
        }
        System.out.println("目前已经发布的课程数量：" + list.size());
    }
}
