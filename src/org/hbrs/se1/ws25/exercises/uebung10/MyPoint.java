package org.hbrs.se1.ws25.exercises.uebung10;

public class MyPoint {

    public double x1,y1;

    public MyPoint(double x1, double y1) {
        this.x1 = x1;
        this.y1 = y1;
    }


    @Override
    public boolean equals(Object p1) {
        if(p1 instanceof MyPoint) {
            return x1 == ((MyPoint) p1).x1 && y1 == ((MyPoint) p1).y1;
        }else{
            return false;
        }
    }
}