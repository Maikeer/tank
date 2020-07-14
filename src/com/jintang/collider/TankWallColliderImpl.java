package com.jintang.collider;

import com.jintang.*;
import com.jintang.observer.TankWallUnintersectObserverImpl;
import com.jintang.observer.UnIntersectEvent;

public class TankWallColliderImpl implements ICollider {
    @Override
    public boolean compartor(GameObject o1, GameObject o2) {
        if(o1 instanceof Tank && o2 instanceof Wall){
            boolean intersects = ((Tank) o1).rect.intersects(((Wall)o2).getRect());
            if(intersects){
                ((Tank) o1).back(true);
                ((Tank) o1).setWall(((Wall)o2));
                GameModel.getInstance().getObservers().add(new TankWallUnintersectObserverImpl());
                return false;
            }else{
                UnIntersectEvent intersectEvent = new UnIntersectEvent(((Tank) o1),((Wall)o2));
                for (int i = 0; i < GameModel.getInstance().getObservers().size(); i++) {
                    GameModel.getInstance().getObservers().get(i).unintersectEvent(intersectEvent);
                }
            }
            return true;
        }else if(o2 instanceof Buller && o1 instanceof Wall){
            return compartor(o2,o1);
        }else {
            return true;
        }
    }
}
