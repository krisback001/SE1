package org.hbrs.se1.ws25.exercises.uebung10;

public class MyPrettyRectangle {

    private double x1, y1;
    private double x2, y2;

    public MyPrettyRectangle(double v, double v1, double v2, double v3) {
        this.x1 = v;
        this.y1 = v1;
        this.x2 = v2;
        this.y2 = v3;
    }

    public boolean contains(MyPrettyRectangle rectangle2) {
        //Pruefe ob jeder Punkt in einem Anderen liegt
        return rectangle2.x1 <= this.x1
                && rectangle2.y1 <= this.y1
                && rectangle2.x2 >= this.x2
                && rectangle2.y2 >= this.y2;
    }

    public void MyPoint(double x, double y) {

    }

    public MyPoint getCenter() {

        return new MyPoint(
                (double)(x1 + x2)/2,
                (double)(y1+ y2)/2);
    }

    public double getArea() {
        double laengeA = x2 - x1;
        double laengeB = y2 - y1;
        return laengeA*laengeB;
    }
}
