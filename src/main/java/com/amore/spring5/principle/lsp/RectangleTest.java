package com.amore.spring5.principle.lsp;

public class RectangleTest {

    public static void resize(Rectangle rectangle) {
        //转变成正方形：如果宽大于高的时候 高+1
        while (rectangle.getWidth() >= rectangle.getHeight()) {
            rectangle.setHeight(rectangle.getHeight() + 1);
            System.out.println("height: " + rectangle.getHeight() + "  width: " + rectangle.getWidth());
        }
        System.err.println("height: " + rectangle.getHeight() + "  width: " + rectangle.getWidth());
    }

    public static void main(String[] args) {
//        Rectangle rectangle = new Rectangle();
//        rectangle.setWidth(15);
//        rectangle.setHeight(10);
//        resize(rectangle);
        //子类替换父类之后不满足里氏替换原则,陷入死循环(约束继承泛滥)
        Square square = new Square();
        square.setLength(10);
        resize(square);
    }
}
