package com.amore.spring5.principle.lsp;

public class Square extends Rectangle {
    private int length;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public int getHeight() {
        return this.getLength();
    }

    @Override
    public void setHeight(int height) {
        this.setLength(height);
    }

    @Override
    public int getWidth() {
        return this.getLength();
    }

    @Override
    public void setWidth(int width) {
        this.setLength(width);
    }
}
