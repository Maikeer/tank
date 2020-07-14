package com.jintang.observer;

import com.jintang.Tank;

public interface IObserver<T> {
    void fireActionEvent(IEvent<T> event);
    void unintersectEvent(IEvent<Tank> event);
}
