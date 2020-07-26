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

       // AysnFile aysnFile = new AysnFile();
       // aysnFile.asyRead();

        long a = System.currentTimeMillis();
        FileNIOChannel.runCh();
        long b = System.currentTimeMillis();

        FileNIOChannel.runByteBuffer();
        long c = System.currentTimeMillis();

        System.out.println("runch:" + String.valueOf(b-a));
        System.out.println("runByteBuffer:" + String.valueOf(c-b));
       // Thread.currentThread().join();
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