package com.jintang;

import java.io.Serializable;

public interface FireStrategy extends Serializable {
    void  fire(Tank tank);
}
