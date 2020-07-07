package com.jintang.collider;

import com.jintang.Buller;
import com.jintang.GameObject;
import com.jintang.Tank;
import com.jintang.Wall;

public class BullerWallColliderImpl implements ICollider {
    @Override
    public boolean compartor(GameObject o1, GameObject o2) {
        if(o1 instanceof Buller && o2 instanceof Wall){
            boolean intersects = ((Buller) o1).getRect().intersects(((Wall)o2).getRect());
            if(intersects){
                ((Buller) o1).die();
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
