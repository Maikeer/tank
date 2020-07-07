package com.jintang;

import com.jintang.decorator.CricleBullerDecorator;
import com.jintang.decorator.LineBullerDecorator;

public class DefaultStrategy implements FireStrategy {
    @Override
    public void fire(Tank tank) {
        int bx = tank.x,by=tank.y;
        bx=tank.getX()+(ResourceMgr.badTankU.getWidth()/2)-ResourceMgr.bulletU.getHeight()/2;
        by=tank.getY()+(ResourceMgr.badTankL.getHeight()/2)-ResourceMgr.bulletU.getHeight()/2;
        GameModel frame = GameModel.getInstance();
        new LineBullerDecorator(new CricleBullerDecorator(new Buller(tank.getDir(),bx,by,Group.BAD)));
//        Buller buller=new Buller(tank.getDir(),bx,by,Group.BAD);
//        if(frame.bullets.size()<tank.getMaxBullers()) frame.bullets.add(buller);
        if(tank.getGroup() == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }
}
