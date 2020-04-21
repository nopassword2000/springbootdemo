package com.nio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class NioByteBuffer {

    void readBuffer(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
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
        byteBuffer.get(readByte3);
    }


    void reandFile() throws Exception{
        //File file = new File("F:\\wmContos\\contos_1\\contos_1-s001.vmdk");
        //FileOutputStream fileOutputStream = new FileOutputStream(file); //C:\Users\Administrator\Desktopspring类继承.doc
        RandomAccessFile randomAccessFile = new RandomAccessFile("F:\\wmContos\\contos_1\\contos_1-s002.vmdk","rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0,fileChannel.size());
        while (mappedByteBuffer.hasRemaining()){


            System.out.println(mappedByteBuffer.array());
        }
    }
}
