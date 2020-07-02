package com.jintang;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Tank  {
    int x=200,y=200;
    private final int SPEED=10;
    private Dir dir= Dir.LEFT;
    private boolean moving=false;
//    private Buller buller;
    private TankFrame frame;
    private final int MaxBullers=100;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Tank(int x, int y, Dir dir,TankFrame frame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.frame=frame;
    }


    public void paint(Graphics g) throws IOException {

//        Color c = g.getColor();
//        g.setColor(Color.BLUE);
        move();
//        g.drawImage(image,x,y,image.getWidth(),image.getHeight(),null);
        switch(dir) {
            case LEFT:
                g.drawImage(/*this.group == Group.GOOD? ResourceMgr.goodTankL : */ResourceMgr.badTankL, x, y, null);
                break;
            case UP:
                g.drawImage(/*this.group == Group.GOOD? ResourceMgr.goodTankU : */ResourceMgr.badTankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(/*this.group == Group.GOOD? ResourceMgr.goodTankR :*/ ResourceMgr.badTankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(/*this.group == Group.GOOD? ResourceMgr.goodTankD :*/ ResourceMgr.badTankD, x, y, null);
                break;
        }

//        g.fillRect(x,y,50,50);
//        g.setColor(c);



    }

    private void move() {
        if(!moving)return;
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
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void fire() {
        int bx = x,by=y;

        switch(dir) {
            case LEFT:
                by=this.getY()+(ResourceMgr.badTankL.getHeight()/2)-ResourceMgr.bulletU.getHeight()/2;
                break;
            case UP:
//                g.drawImage(/*this.group == Group.GOOD? ResourceMgr.goodTankU : */ResourceMgr.badTankU, x, y, null);
                bx=this.getX()+(ResourceMgr.badTankU.getWidth()/2)-ResourceMgr.bulletU.getHeight()/2;
                break;
            case RIGHT:
//                g.drawImage(/*this.group == Group.GOOD? ResourceMgr.goodTankR :*/ ResourceMgr.badTankR, x, y, null);
                by=this.getY()+(ResourceMgr.badTankR.getHeight()/2)-ResourceMgr.bulletU.getHeight()/2;
                break;
            case DOWN:
//                g.drawImage(/*this.group == Group.GOOD? ResourceMgr.goodTankD :*/ ResourceMgr.badTankD, x, y, null);
                bx=this.getX()+(ResourceMgr.badTankD.getWidth()/2)-ResourceMgr.bulletU.getHeight()/2;
                break;
        }
        System.out.println("by"+by);
        Buller buller=new Buller(dir,bx,by,frame);
      if(frame.bullets.size()<MaxBullers) frame.bullets.add(buller);

    }
}
