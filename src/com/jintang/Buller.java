package com.jintang;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Buller {
    private static final int SPEED=10;
    private Dir dir;
    private int x,y;
    private int WIGHT=10,HIGHT=10;
    private TankFrame tf;
    public Buller(Dir dir, int x, int y,TankFrame tf) {

        this.dir = dir;
        this.x = x;
        this.y = y;
        this.tf=tf;
    }

    public void paint(Graphics g) {
//        Color c = g.getColor();
//        g.setColor(Color.red);
        move();
        switch(dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
        }
//        g.setColor(c);

    }
    private void move() {
//        if(!moving)return;
        switch (dir) {
            case LEFT:
                x-=SPEED;
                break;
            case UP:
                y-=SPEED;
                break;
            case RIGHT:

                x+=SPEED;
                break;
            case DOWN:
                y+=SPEED;
                break;
        }
        if(x<0||y<0||x>TankFrame.GAME_WIDTH||y>TankFrame.GAME_HEIGHT)tf.bullets.remove(this);
    }
}
