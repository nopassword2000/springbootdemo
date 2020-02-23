package com.nio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

@SpringBootApplication
public class NioApplication {

    public static void main(String[] args) throws IOException {
        //SpringApplication.run(NioApplication.class, args);


/*        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        byte [] bytes=  "hello nio".getBytes();
        ByteBuffer byteBuffer1 = byteBuffer.put(bytes);
        byte [] readByte = new byte[100];
        byteBuffer.flip();

        byteBuffer.position();
        ByteBuffer byteBuffer2 = byteBuffer.get(readByte);


        byte [] bytes2=  "hel".getBytes();
        byteBuffer.put(bytes2);
       // byteBuffer.flip();

        byteBuffer.flip();
        byte [] readByte3 = new byte[8];
        byteBuffer.get(readByte3);*/

      // File file = new File("F:\\wmContos\\contos_1\\contos_1-s001.vmdk");
       //FileOutputStream fileOutputStream = new FileOutputStream(file); //C:\Users\Administrator\Desktopspring类继承.doc
        RandomAccessFile randomAccessFile = new RandomAccessFile("F:\\wmContos\\contos_1\\contos_1-s002.vmdk","rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0,fileChannel.size());
        while (mappedByteBuffer.hasRemaining()){
            System.out.println(mappedByteBuffer.getLong());
        }

    }

}
