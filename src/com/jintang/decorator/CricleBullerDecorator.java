package com.jintang.decorator;

import com.jintang.GameModel;
import com.jintang.GameObject;

import java.awt.*;
import java.io.IOException;

/**
 * 状态框
 */
public class CricleBullerDecorator extends IDecorator {
    private GameObject go;

    public CricleBullerDecorator(GameObject go) {
        this.go = go;
//        GameModel.getInstance().add(this);
    }

    @Override
    public void paint(Graphics g) {
        try {
            go.paint(g);
            Color c = g.getColor();
            g.setColor(Color.CYAN);
            x=go.x+2;
            y=go.y+2;
            g.drawRect(x,y,go.getWIDTH()+2,go.getHEIGHT()+2);
            g.setColor(c);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getWIDTH() {
        return go.getWIDTH();
    }

    @Override
    public int getHEIGHT() {
        return go.getHEIGHT();
    }
}
