package com.amore.spring5.pattern.factory.abstractfactory;

import com.amore.spring5.pattern.factory.ICourse;

/**
 * 抽象工厂：聚合多个产品---如果新增产品，不满足开闭原则
 * 分为：产品等级结构（横向）+产品族（纵向）
 *  产品族：指不同的产品工厂
 *  等级结构：指同一个工厂，会生产多种产品
 */
public interface ICourseFactory {

    ICourse createCourse();

    INode createNode();

    IVedio createVedio();
}
