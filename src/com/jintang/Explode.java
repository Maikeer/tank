package com.jintang;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Explode {


    private int x,y;
    private int WIGHT=10,HIGHT=10;
    private GameModel tf;

    public static int WIDTH = ResourceMgr.explodes[0].getWidth();

    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();
    private int step = 0;
    public Explode( int x, int y,GameModel tf) {
        this.x = x;
        this.y = y;
        this.tf=tf;
        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }

    public void paint(Graphics g) {
        BufferedImage[] explodes = ResourceMgr.explodes;

        g.drawImage(explodes[step++],x,y,null);

       if(step>=ResourceMgr.explodes.length)tf.explodes.remove(this);
    }

}
