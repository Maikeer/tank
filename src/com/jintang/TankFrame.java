package com.jintang;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TankFrame extends Frame {



    private final int MaxBullers=100;
    private final int SPEED=10;

    public static final int GAME_WIDTH=800,GAME_HEIGHT=600;
    public TankFrame(){
        setSize(GAME_WIDTH,GAME_HEIGHT);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);

        GameModel.getInstance().init();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        addKeyListener(new MyKeyListener());
        new Thread(()->new Audio("audio/war1.wav").play()).start();
    }
    class MyKeyListener extends KeyAdapter {

        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    setMainTankDir();
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    setMainTankDir();
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    setMainTankDir();
                    break;
                case KeyEvent.VK_DOWN:

                    bD = true;
                    setMainTankDir();
                    break;

                default:
                    break;
            }

            new Thread(()->new Audio("audio/tank_move.wav").play()).start();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();

            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    setMainTankDir();
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    setMainTankDir();
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    setMainTankDir();
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    setMainTankDir();
                    break;

                case KeyEvent.VK_CONTROL:
                    GameModel.getInstance().getMyTank().fire();
                    break;

                default:
                    break;
            }


        }

        private void setMainTankDir() {
           Tank myTank= GameModel.getInstance().getMyTank();

            if (!bL && !bU && !bR && !bD) {
                myTank.setMoving(false);
            }else{
                myTank.setMoving(true);
                if(bL){
                    myTank.setDir(Dir.LEFT);
                }
                if(bR){
                    myTank.setDir(Dir.RIGHT);
                }
                if(bU){
                    myTank.setDir(Dir.UP);
                }
                if(bD){
                    myTank.setDir(Dir.DOWN);
                }
            }
//            repaint();
//            //save the old dir
//            Dir dir = myTank.getDir();
//
//            if (!bL && !bU && !bR && !bD) {
//                myTank.setMoving(false);
//                Client.INSTANCE.send(new TankStopMsg(getMainTank()));
//            } else {
//
//                if (bL)
//                    myTank.setDir(Dir.LEFT);
//                if (bU)
//                    myTank.setDir(Dir.UP);
//                if (bR)
//                    myTank.setDir(Dir.RIGHT);
//                if (bD)
//                    myTank.setDir(Dir.DOWN);
//                //发出坦克移动的消息
//                if(!myTank.isMoving())
//                    Client.INSTANCE.send(new TankStartMovingMsg(getMainTank()));
//
//                myTank.setMoving(true);
//
//                if(dir != myTank.getDir()) {
//                    Client.INSTANCE.send(new TankDirChangedMsg(myTank));
//                }
//            }


        }
    }
    Image offScreenImage=null;
    @Override
    public void update(Graphics g) {

        if(offScreenImage==null){
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics graphics = offScreenImage.getGraphics();
        Color c = graphics.getColor();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        graphics.setColor(c);
        paint(graphics);
//        super.paint(graphics);
        g.drawImage(offScreenImage,0,0,null);

    }

    @Override
    public void paint(Graphics g) {
            GameModel.getInstance().paint(g);
    }

}
