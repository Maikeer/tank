package com.jintang;

import com.jintang.flyweight.BullerPoll;

public class QiangFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank tank) {
        int bx = tank.x,by=tank.y;
        bx=tank.getX()+(ResourceMgr.badTankU.getWidth()/2)-ResourceMgr.bulletU.getHeight()/2;
        by=tank.getY()+(ResourceMgr.badTankL.getHeight()/2)-ResourceMgr.bulletU.getHeight()/2;
//       new Buller(Dir.UP,bx,by,Group.GOOD);
//        new Buller(Dir.LEFT,bx,by,Group.GOOD);
//        new Buller(Dir.RIGHT,bx,by,Group.GOOD);
//        new Buller(Dir.DOWN,bx,by,Group.GOOD);
        for (int i = 0; i <4 ; i++) {
            Buller buller = BullerPoll.getInstance().getBuller();
            buller.x=bx;
            buller.y=by;
            buller.setDir(Dir.values()[i]);
            buller.setGroup(Group.GOOD);
            GameModel.getInstance().add(buller);
        }

//        GameModel frame = tank.getFrame();
//        frame.bullets.add(new Buller(Dir.UP,bx,by,Group.GOOD,frame));
//        frame.bullets.add(new Buller(Dir.LEFT,bx,by,Group.GOOD,frame));
//        frame.bullets.add(new Buller(Dir.RIGHT,bx,by,Group.GOOD,frame));
//        frame.bullets.add(new Buller(Dir.DOWN,bx,by,Group.GOOD,frame));
//        Buller buller=new Buller(tank.getDir(),bx,by,frame);
//        if(frame.bullets.size()<tank.getMaxBullers()) frame.bullets.add(buller);
        if(tank.getGroup() == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }
}
