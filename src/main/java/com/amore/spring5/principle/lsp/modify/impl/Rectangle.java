package com.amore.spring5.principle.lsp.modify.impl;

import com.amore.spring5.principle.lsp.modify.Quadrangle;

public class Rectangle implements Quadrangle {

    private int width;

    private int height;

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
