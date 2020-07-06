package com.jintang;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameModel {
    private Tank myTank;
    public List<Tank> badTanks=new ArrayList();
    List<Explode> explodes = new ArrayList<>();
    List<Buller> bullets =new ArrayList<Buller>();
    private Dir dir= Dir.LEFT;
    private int maxBadTank=5;
    int x=200,y=200;

    public Tank getMyTank() {
        return myTank;
    }

    public void setMyTank(Tank myTank) {
        this.myTank = myTank;
    }

    public List<Tank> getBadTanks() {
        return badTanks;
    }

    public void setBadTanks(List<Tank> badTanks) {
        this.badTanks = badTanks;
    }

    public List<Explode> getExplodes() {
        return explodes;
    }

    public void setExplodes(List<Explode> explodes) {
        this.explodes = explodes;
    }

    public List<Buller> getBullets() {
        return bullets;
    }

    public void setBullets(List<Buller> bullets) {
        this.bullets = bullets;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

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

    public GameModel() {
        myTank=new Tank(x,y,dir,Group.GOOD,this);
        myTank.setMoving(false);
        for (int i = 0; i <maxBadTank ; i++) {
            Tank tank = new Tank(30 + i * 100, 30, Dir.DOWN, Group.BAD, this);
            badTanks.add(tank);
        }
    }
    public void paint(Graphics g) {
//        super.paint(g);
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("bullets:" + bullets.size(), 10, 60);
        g.drawString("tanks:" + badTanks.size(), 10, 80);
//        g.drawString("explodes" + explodes.size(), 10, 100);
        g.setColor(c);
        try {
            if(myTank!=null)
                myTank.paint(g);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i <bullets.size() ; i++) {
            bullets.get(i).paint(g);
        }
        Iterator<Tank> iterator = badTanks.iterator();
        while (iterator.hasNext()){
            if(!iterator.next().isLving){
                iterator.remove();
            }
        }
//        System.out.println("badTanks.size()"+badTanks.size());
        for (int i = 0; i <badTanks.size() ; i++) {
            try {
                badTanks.get(i).paint(g);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i <bullets.size() ; i++) {
            for (int j = 0; j <badTanks.size() ; j++) {

                bullets.get(i).collideWith(badTanks.get(j));

            }
        }
        for (int i = 0; i <explodes.size() ; i++) {
            explodes.get(i).paint(g);

        }
//        y+=10;
    }
}
