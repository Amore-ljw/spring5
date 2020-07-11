package com.amore.spring5.pattern.factory.simplefactory;

/**
 * 简单工厂，创建型设计模式(不属于23种设计模式)
 * @param <T>
 */
public class CourseFactory<T> {

    public static <T> T create(Class<? extends T> clazz){
        if (clazz!=null) {
            try {
                return clazz.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
