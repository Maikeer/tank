package com.jintang.flyweight;

import com.jintang.Buller;
import com.jintang.Dir;
import com.jintang.GameModel;
import com.jintang.Group;

import java.util.ArrayList;
import java.util.List;

public class BullerPoll {
    private static BullerPoll instance=new BullerPoll();
    private final int MaxBullers=10;
    private List<Buller> bullerList=new ArrayList<>();
    public static BullerPoll getInstance(){
        return instance;
    }

    private BullerPoll() {
        for (int i = 0; i <MaxBullers ; i++) {
            Buller buller=new Buller(Dir.UP,0,0, Group.BAD);
            bullerList.add(buller);
        }
    }

    public Buller getBuller(){

        for (int i = 0; i <bullerList.size() ; i++) {
            if(!bullerList.get(i).isUsed()){
                System.err.println("buller get from poll..");
                Buller buller = bullerList.get(i);
                buller.setUsed(true);
                return buller ;
            }
        }
        System.err.println("no buller get from poll..");
        return new Buller(Dir.UP,0,0, Group.BAD);
    }
}
