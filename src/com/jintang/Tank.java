package com.jintang;
import com.jintang.web.Client;
import com.jintang.web.TankDieMsg;
import com.jintang.web.TankJoinMsg;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Tank  extends GameObject {
    int x=200,y=200;
    private final int SPEED=5;
    private UUID id =UUID.randomUUID();
    private Dir dir= Dir.LEFT;
    private boolean moving=false;
//    private Buller buller;
    int oldx=200,oldy=200;
    private Group group;
    public boolean isLving=true;
    public static final int Tank_WIDTH=ResourceMgr.badTankL.getWidth(),Tank_HEIGHT=ResourceMgr.badTankL.getHeight();

    public Tank(TankJoinMsg tankJoinMsg) {
        this.x = tankJoinMsg.x;
        this.y = tankJoinMsg.y;
        this.dir = tankJoinMsg.dir;
        this.id=tankJoinMsg.id;
        this.group=tankJoinMsg.group;
        rect.x=tankJoinMsg.x;
        rect.y=tankJoinMsg.y;
        rect.width=Tank_WIDTH;
        rect.height=Tank_HEIGHT;
//        fireStrategy=this.group==Group.GOOD?new QiangFireStrategy():new DefaultStrategy();
        fireStrategy=new DefaultStrategy();
        GameModel.getInstance().add(this);
    }

    public int getX() {
        return x;
    }
    public Rectangle rect=new Rectangle(0,0,0,0);
    public void setX(int x) {
        this.x = x;
    }
    private boolean isCollider=false;
    private Dir colliderDir;

    public boolean isCollider() {
        return isCollider;
    }

    public void setCollider(boolean collider) {
        isCollider = collider;
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
    @Override
    public int getWIDTH() {
        return Tank_WIDTH;
    }

    @Override
    public int getHEIGHT() {
        return Tank_HEIGHT;
    }

    public Dir getDir() {
        return dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public boolean isLving() {
        return isLving;
    }

    public void setLving(boolean lving) {
        isLving = lving;
    }
    private FireStrategy fireStrategy;
    public Tank(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;

        this.group=group;
        rect.x=x;
        rect.y=y;
        rect.width=Tank_WIDTH;
        rect.height=Tank_HEIGHT;
//        fireStrategy=this.group==Group.GOOD?new QiangFireStrategy():new DefaultStrategy();
        fireStrategy=new DefaultStrategy();
        GameModel.getInstance().add(this);
    }


    @Override
    public void paint(Graphics g) throws IOException {
//        if(!isLving){
//            frame.badTanks.remove(this);
//            return;
//        }
//        Color c = g.getColor();
//        g.setColor(Color.BLUE);
        randomDir();
        move();
        checkBorder();

//        g.drawImage(image,x,y,image.getWidth(),image.getHeight(),null);
        switch(dir) {
            case LEFT:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
                break;
        }

//        g.fillRect(x,y,50,50);
//        g.setColor(c);



    }

    private void checkBorder() {
        if(x<2)x=2;
        if(y<Tank_HEIGHT/2+2)y=Tank_HEIGHT/2+2;
        if(x>TankFrame.GAME_WIDTH-Tank_WIDTH-2)x=TankFrame.GAME_WIDTH-Tank_WIDTH-2;
        if(y>TankFrame.GAME_HEIGHT-Tank_HEIGHT-2)y=TankFrame.GAME_HEIGHT-Tank_HEIGHT-2;
//        for (int i = 0; i <frame.badTanks.size() ; i++) {
//            boolean intersects = frame.badTanks.get(i).rect.intersects(rect);
//            if(intersects){
//
//            }
//        }
    }

    private void randomDir() {
        if(group==Group.BAD&&random.nextInt(100)>80){
            dir=Dir.values()[random.nextInt(4)];
        }
    }
    public void back(boolean flag){
        if(flag&&group==Group.GOOD){
            if(!isCollider){
                isCollider=true;
                colliderDir=dir;
            }

        }else{
            x=oldx;
            y=oldy;
        }
    }
    private Random random=new Random();
    private void move() {
        if(!moving||(isCollider&&colliderDir==dir))return;
//        isCollider=false;
        oldx=x;
        oldy=y;
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
        if(group==Group.BAD&&random.nextInt(100)>95)
            fire();
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void fire() {
        fireStrategy.fire(this);
    }
    public void procUnintersect(Wall wall){
        if(this.wall==wall){
            isCollider=false;
        }
    };
    public void die() {
        isLving=false;
        int bx = x,by=y;
        bx=this.getX()+(ResourceMgr.badTankU.getWidth()/2)-Explode.WIDTH/2;
        by=this.getY()+(ResourceMgr.badTankL.getHeight()/2)-Explode.HEIGHT/2;
        Explode explode = new Explode(bx, by);
        Tank myTank = GameModel.getInstance().getMyTank();
        if(myTank!=null&&myTank.getId()!=id){
            Client.INSTANCE.send(new TankDieMsg(this));
        }

//        GameModel.getInstance().add(explode);
    }
    private  Wall wall;
    public void setWall(Wall wall) {
        this.wall = wall;
    }
}
