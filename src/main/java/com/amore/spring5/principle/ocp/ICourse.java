package com.amore.spring5.principle.ocp;

/**
 * 8种设计基本原则：
 * Open-Closed Principle，OCP（开闭原则:对扩展开放，对修改关闭）
 * 三：Simple Responsibility Pinciple，SRP(是指不要存在多于一个导致类变更的原因)
 */
public interface ICourse {
    Integer getId();
    String getName();
    Double getPrice();
}
