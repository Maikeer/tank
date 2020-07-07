package com.jintang;

import java.awt.*;
import java.io.IOException;

public class Wall extends GameObject {
    private int x,y,w,h;
    private Rectangle rect;
    @Override
    public int getWIDTH() {
        return w;
    }

    @Override
    public int getHEIGHT() {
        return h;
    }
    public Rectangle getRect() {
        return rect;
    }

    public Wall(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        rect=new Rectangle(x,y,w,h);
    }

    @Override
    public void paint(Graphics g) throws IOException {
        Color color = g.getColor();
        g.setColor(Color.BLUE);
        g.fillRect(x,y,w,h);
        g.setColor(color);
    }
}
