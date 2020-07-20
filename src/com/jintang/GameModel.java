package com.jintang;

import com.jintang.collider.*;
import com.jintang.observer.FireObserverImpl;
import com.jintang.observer.IObserver;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class GameModel {

   private static GameModel instance=new GameModel();
    public static GameModel getInstance(){
        return instance;
    }
    private Tank myTank;
//    public List<Tank> badTanks=new ArrayList();
//    List<Explode> explodes = new ArrayList<>();
//    List<Buller> bullets =new ArrayList<Buller>();
protected List<GameObject> objs=new ArrayList();
   private List<IObserver<Tank>> observers=new ArrayList<>();

    public List<IObserver<Tank>> getObservers() {
        return observers;
    }

    private ColliderChain colliderChain;
   public void add(GameObject obj){

        objs.add(obj);
    }
    public void remove(GameObject obj){
        objs.remove(obj);
    }
    private Dir dir= Dir.LEFT;
    private int maxBadTank=5;
    int x=200,y=200;

    public Tank getMyTank() {
        return myTank;
    }

    public void setMyTank(Tank myTank) {
        this.myTank = myTank;
    }
    private ConcurrentHashMap<UUID,Tank> badTanks= new ConcurrentHashMap<>();
    public Tank findTankByUUID(UUID id) {
        return badTanks.get(id);
    }

    public void addTank(Tank t) {
        badTanks.put(t.getId(),t);
    }
//
//    public List<Tank> getBadTanks() {
//        return badTanks;
//    }
//
//    public void setBadTanks(List<Tank> badTanks) {
//        this.badTanks = badTanks;
//    }
//
//    public List<Explode> getExplodes() {
//        return explodes;
//    }
//
//    public void setExplodes(List<Explode> explodes) {
//        this.explodes = explodes;
//    }
//
//    public List<Buller> getBullets() {
//        return bullets;
//    }
//
//    public void setBullets(List<Buller> bullets) {
//        this.bullets = bullets;
//    }

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
    public void init(){
        myTank=new Tank(x,y,dir,Group.GOOD);
//        myTank.setMoving(false);
   /*     for (int i = 0; i <maxBadTank ; i++) {
            Tank tank = new Tank(30 + i * 100, 30, Dir.DOWN, Group.BAD);
//            badTanks.add(tank);
        }*/
        // 初始化墙
        add(new Wall(150, 150, 200, 50));
        add(new Wall(550, 150, 200, 50));
        add(new Wall(300, 300, 50, 200));
        add(new Wall(550, 300, 50, 200));
        observers.add(new FireObserverImpl());
        colliderChain=new ColliderChain();
        colliderChain.add(new BullerTankColliderImpl());
        colliderChain.add(new TankTankColliderImpl());
        colliderChain.add(new BullerWallColliderImpl());
        colliderChain.add(new TankWallColliderImpl());
    }
    private GameModel() {

    }
    public void paint(Graphics g) {
//        super.paint(g);
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("bullets:" + objs.size(), 10, 60);
        g.drawString("tanks:" + objs.size(), 10, 80);
//        g.drawString("explodes" + explodes.size(), 10, 100);
        g.setColor(c);
        try {
            if(myTank!=null)
                myTank.paint(g);
        } catch (IOException e) {
            e.printStackTrace();
        }


//        System.out.println("badTanks.size()"+badTanks.size());
//        for (int i = 0; i <badTanks.size() ; i++) {
//            try {
//                badTanks.get(i).paint(g);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        for (int i = 0; i <bullets.size() ; i++) {
//            for (int j = 0; j <badTanks.size() ; j++) {
//
//                bullets.get(i).collideWith(badTanks.get(j));
//
//            }
//        }
        for (int i = 0; i < objs.size() ; i++) {
            try {
                objs.get(i).paint(g);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < objs.size() ; i++) {
            for (int j = i+1; j < objs.size() ; j++) {
                colliderChain.compartor(objs.get(i),objs.get(j));
            }
        }
        Iterator<GameObject> iterator = objs.iterator();
        while (iterator.hasNext()){
            GameObject next = iterator.next();
            if(next instanceof Tank &&((Tank) next).getGroup()==Group.BAD&&!((Tank) next).isLving){
                iterator.remove();
            }
        }
//        for (int i = 0; i <explodes.size() ; i++) {
//            explodes.get(i).paint(g);
//
//        }
//        y+=10;
    }
    public void save(){
        File file = new File("d:/tankData/my.data");
        ObjectOutputStream outputStream=null;
        try {
             outputStream = new ObjectOutputStream(new FileOutputStream(file));
            outputStream.writeObject(myTank);
            outputStream.writeObject(objs);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(outputStream!=null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    public void load(){
        File file = new File("d:/tankData/my.data");
        ObjectInputStream inputStream=null;
        try {
            inputStream = new ObjectInputStream(new FileInputStream(file));
            myTank= (Tank) inputStream.readObject();
            objs= (List<GameObject>) inputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(inputStream!=null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
