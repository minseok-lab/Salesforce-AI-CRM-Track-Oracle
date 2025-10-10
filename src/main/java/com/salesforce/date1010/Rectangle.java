package com.salesforce.date1010;

public class Rectangle {
    private int width;
    private int height;
    
    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getArea() {
        return width * height;
    }

    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } if (this.getArea() == ((Rectangle)obj).getArea()) {
            return true;
        } else {
            return false;
        }
    }

    // 구해지는 면적이 같으면 같은 사각형으로 처리
    // @Override
    // public boolean equals(Object obj) {
    //     if (obj instanceof Rectangle) {
    //         Rectangle r = (Rectangle) obj;
    //         return this.width * this.height == r.width * r.height;
    //     }
    //     return false;
    // }
}
