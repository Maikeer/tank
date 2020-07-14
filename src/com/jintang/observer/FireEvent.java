package com.jintang.observer;

import com.jintang.Tank;

public class FireEvent implements IEvent<Tank>{
    private Tank tank;


    @Override
    public Tank getResoure() {
        return tank;
    }

    public FireEvent(Tank tank) {
        this.tank = tank;
    }
}
