package com.jintang;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Buller  extends GameObject{
    private static final int SPEED=10;
    private Dir dir;

    private Rectangle rect=new Rectangle(0,0,0,0);
    public static int WIDTH = ResourceMgr.bulletD.getWidth();
    public boolean isLving=true;
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();
    Group group;

    public Rectangle getRect() {
        return rect;
    }

    public Buller(Dir dir, int x, int y, Group group) {

        this.dir = dir;
        this.x = x;
        this.y = y;
        rect.x=x;
        rect.y=y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
        this.group=group;
//        GameModel.getInstance().add(this);
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

    @Override
    public int getWIDTH() {
        return WIDTH;
    }

    @Override
    public int getHEIGHT() {
        return HEIGHT;
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
        rect.x=x;
        rect.y=y;
        if(x<0||y<0||x>TankFrame.GAME_WIDTH||y>TankFrame.GAME_HEIGHT||!isLving)GameModel.getInstance().remove(this);
    }

    public boolean collideWith(Tank tank) {
        if(this.group==Group.BAD&&tank.getGroup()==Group.BAD)return true;
        if(tank.getGroup()==Group.GOOD)return true;
        if(this.rect.intersects(tank.rect)){
            tank.die();
            this.die();
            return false;
        }
        return true;
    }

    public void die() {
        isLving=false;
    }
}
