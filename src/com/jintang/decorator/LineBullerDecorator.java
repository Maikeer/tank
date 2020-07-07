package com.jintang.decorator;

import com.jintang.GameModel;
import com.jintang.GameObject;

import java.awt.*;
import java.io.IOException;

/**
 * 状态框
 */
public class LineBullerDecorator extends IDecorator {
    private GameObject go;

    public LineBullerDecorator(GameObject go) {
        this.go = go;
        GameModel.getInstance().add(this);
    }

    @Override
    public void paint(Graphics g) {
        try {

            go.paint(g);
            x=go.x;
            y=go.y;
            Color c = g.getColor();
            g.setColor(Color.MAGENTA);
            g.drawLine(go.x,go.y,go.x+go.getWIDTH(),go.y+go.getHEIGHT());
//            g.drawRect(go.x+2,go.y+2,go.getWIDTH()+2,go.getHEIGHT()+2);
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
