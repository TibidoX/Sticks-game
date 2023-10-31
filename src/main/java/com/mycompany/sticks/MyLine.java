/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sticks;

/**
 *
 * @author vovab
 */
public class MyLine {
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
}
