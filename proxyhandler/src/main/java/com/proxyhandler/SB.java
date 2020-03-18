package com.proxyhandler;

public class SB implements ISB {

    public void sayProxy(){
        System.out.println("this is SB proxy");
    }

    public int sayInt(Integer a){
        System.out.println(a);
        return a;
    }
}
