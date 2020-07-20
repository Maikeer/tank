package com.jintang;

import com.jintang.web.Client;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();
        new Thread(()->{
            Client.INSTANCE.connect();
        }).start();
        while (true){
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}
