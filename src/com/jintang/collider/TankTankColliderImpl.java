package com.jintang.collider;

import com.jintang.Buller;
import com.jintang.GameObject;
import com.jintang.Tank;

public class TankTankColliderImpl implements  ICollider {
    @Override
    public boolean compartor(GameObject o1, GameObject o2) {
        if(o1 instanceof Tank && o2 instanceof Tank){
            boolean intersects = ((Tank) o1).rect.intersects(((Tank)o2).rect);
            if(intersects){
                ((Tank) o1).back(false);
                ((Tank) o2).back(false);
                return false;
            }

            return true;
        }else {
            return true;
        }
    }
}
