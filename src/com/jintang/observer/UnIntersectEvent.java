package com.jintang.observer;

import com.jintang.Tank;
import com.jintang.Wall;

/**
 * 不再碰撞事件
 */
public class UnIntersectEvent implements IEvent<Tank> {
    private Tank tank;
    private Wall wall;
    @Override
    public Tank getResoure() {
        return tank;
    }

    public Wall getWall() {
        return wall;
    }

    public UnIntersectEvent(Tank tank, Wall wall) {
        this.tank = tank;
        this.wall=wall;
    }
}
