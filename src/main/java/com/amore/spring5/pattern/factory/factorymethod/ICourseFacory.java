package com.amore.spring5.pattern.factory.factorymethod;

/**
 * 工厂方法：满足开闭原则，但随着时间推移，类库会很庞大
 */
public interface ICourseFacory<T> {
    T create();
}
