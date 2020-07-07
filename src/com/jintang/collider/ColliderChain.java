package com.jintang.collider;

import com.jintang.GameObject;

import java.util.ArrayList;
import java.util.List;

public class ColliderChain implements ICollider {
    private List<ICollider> colls=new ArrayList<>();
    public ColliderChain() {

    }
    public void add(ICollider collider){
        colls.add(collider);
    }
    @Override
    public boolean compartor(GameObject o1, GameObject o2) {
        boolean compartor = false;
        for (int i = 0; i <colls.size() ; i++) {
            compartor= colls.get(i).compartor(o1, o2);
            if(!compartor)break;
        }
        return compartor;
    }
}
