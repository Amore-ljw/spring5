package com.amore.spring5.principle.lsp.modify.impl;

import com.amore.spring5.principle.lsp.modify.Quadrangle;

public class Square implements Quadrangle {

    private int length;

    @Override
    public int getWidth() {
        return length;
    }

    @Override
    public int getHeight() {
        return length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
