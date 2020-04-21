package com.nio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

@SpringBootApplication
public class NioApplication {

    public static void main(String[] args) throws Exception {
        //SpringApplication.run(NioApplication.class, args);
       // mianex();
        //NioByteBuffer nioByteBuffer = new NioByteBuffer();
        //nioByteBuffer.reandFile();

        AysnFile aysnFile = new AysnFile();
        aysnFile.asyRead();
        Thread.currentThread().join();
    }





    public static  void mianex(){
        F f = new S();
        f.eat();

        NioApplication nioApplication = new NioApplication();
        nioApplication.eat(f);
    }


    public void eat(S s){
        System.out.println("s");
    }

    public void eat(F f){
        System.out.println("f");
    }

}


class F
{

    public void eat(){
        System.out.println("f");
    }
}


class  S  extends  F{
    public void eat(){
        System.out.println("S");
    }
}