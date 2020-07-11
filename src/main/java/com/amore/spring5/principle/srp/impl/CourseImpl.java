package com.amore.spring5.principle.srp.impl;

import com.amore.spring5.principle.srp.ICourseInfo;
import com.amore.spring5.principle.srp.ICourseManager;

public class CourseImpl implements ICourseInfo,ICourseManager {
    @Override
    public String getCourseName() {
        return null;
    }

    @Override
    public byte[] getCourseVideo() {
        return new byte[0];
    }

    @Override
    public void studyCourse() {

    }

    @Override
    public void refundCourse() {

    }
}
