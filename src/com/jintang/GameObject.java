package com.jintang;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class GameObject {
    public int x,y;
    public abstract void paint(Graphics g)throws IOException;

    public abstract int getWIDTH();
    public abstract int getHEIGHT();
}
