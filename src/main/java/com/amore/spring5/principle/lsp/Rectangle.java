package com.amore.spring5.principle.lsp;

/**
 * Liskov Substitution Principle，LSP(里氏替换原则:子类可以扩展父类的功能，但不能改变父类原有的功能。)
 */
public class Rectangle {

    private int height;
    private int width;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
