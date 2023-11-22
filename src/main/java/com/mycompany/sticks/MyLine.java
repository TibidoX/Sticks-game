/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sticks;

import java.io.Serializable;

/**
 *
 * @author vovab
 */
public class MyLine implements Serializable{
    private int x = 0, y = 0;
    private Positions pos = Positions.HORIZONTAL;
    
    MyLine(int x, int y, Positions pos) {
        this.x = x;
        this.y = y;
        this.pos = pos;
    }
    int getX() {
        return x;
    }
    
    int getY() {
        return y;
    }
    
    Positions getPos() {
        return pos;
    }
    
    @Override
    public boolean equals(Object o) {
        MyLine line = (MyLine) o;
        return (x == line.getX() && y == line.getY() && pos == line.getPos());
    }
}
