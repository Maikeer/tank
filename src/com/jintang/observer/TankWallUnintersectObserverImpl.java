package com.jintang.observer;

import com.jintang.Tank;
import com.jintang.Wall;

public class TankWallUnintersectObserverImpl implements IObserver<Tank> {
    @Override
    public void fireActionEvent(IEvent<Tank> event) {
//        event.getResoure().procUnintersect(((UnIntersectEvent)event).getWall());
    }
    @Override
    public void unintersectEvent(IEvent<Tank> event) {
        event.getResoure().procUnintersect(((UnIntersectEvent)event).getWall());
    }
}
