package com.amore.spring5.pattern.simple;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExamPaper {
    private String examinationPaperId;//试卷主键
    private String leavTime;//剩余时间
    private String organizationId;//单位主键
    private String id;//考试主键
    private String examRoomId;//考场主键
    private String userId;//用户主键
    private String specialtyCode;//专业代码
    private String postionCode;//报考岗位
    private String gradeCode;//报考等级
    private String examStartTime;//考试开始时间
    private String examEndTime;//考试结束时间
    private String singleSelectionImpCount;//单选选题重要数量
    private String multiSelectionImpCount;//多选题重要数量
    private String judgementImpCount;//判断题重要数量
    private String examTime;//考试时长
    private String fullScore;//总分
    private String passScore;//及格分
    private String userName;//学员姓名
    private String score;//考试得分
    private String resut;//是否及格
    private String singleOkCount;//单选题答对数量
    private String multiOkCount;//多选题答对数量
    private String judgementOkCount;//判断题答对数量

    public static void main(String[] args) {
        //原型+工厂模式
        ExamPaper jsonObject = JSONObject.parseObject("json String", ExamPaper.class);
    }
}
