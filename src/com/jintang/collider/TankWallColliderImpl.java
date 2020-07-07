package com.jintang.collider;

import com.jintang.Buller;
import com.jintang.GameObject;
import com.jintang.Tank;
import com.jintang.Wall;

public class TankWallColliderImpl implements ICollider {
    @Override
    public boolean compartor(GameObject o1, GameObject o2) {
        if(o1 instanceof Tank && o2 instanceof Wall){
            boolean intersects = ((Tank) o1).rect.intersects(((Wall)o2).getRect());
            if(intersects){
                ((Tank) o1).back(true);
                return false;
            }
            return true;
        }else if(o2 instanceof Buller && o1 instanceof Wall){
            return compartor(o2,o1);
        }else {
            return true;
        }
    }
}
