package com.jintang.observer;

import com.jintang.Tank;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

public class FireObserverImpl implements IObserver<Tank> {
    @Override
    public void fireActionEvent(IEvent<Tank> event) {
        event.getResoure().fire();
    }

    @Override
    public void unintersectEvent(IEvent<Tank> event) {

    }


}
