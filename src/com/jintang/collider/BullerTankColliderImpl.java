package com.jintang.collider;

import com.jintang.Buller;
import com.jintang.GameObject;
import com.jintang.Tank;

public class BullerTankColliderImpl implements  ICollider {
    @Override
    public boolean compartor(GameObject o1, GameObject o2) {
        if(o1 instanceof Buller && o2 instanceof Tank){
            return ((Buller) o1).collideWith((Tank) o2);
        }else if(o2 instanceof Buller && o1 instanceof Tank){
            return compartor(o2,o1);
        }else {
            return true;
        }
    }
}
