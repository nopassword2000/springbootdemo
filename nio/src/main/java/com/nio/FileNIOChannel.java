package com.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.Selector;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileNIOChannel {


    public static  void runCh() {

        try (
        FileChannel in = FileChannel.open(Paths.get("F:\\wmContos\\contos_1\\contos_1-s002.vmdk"), StandardOpenOption.READ);
        FileChannel out = FileChannel.open(Paths.get("F:\\wmContos\\contos_1\\cno\\a.vi"), StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
        ){
            out.force(false);
            out.transferFrom(in, 0, in.size());
        }catch (Exception ex){
            System.out.println("error");
        }

    }


    public static void runByteBuffer(){
        try (
                FileChannel in = FileChannel.open(Paths.get("F:\\wmContos\\contos_1\\contos_1-s002.vmdk"), StandardOpenOption.READ);
                FileChannel out = FileChannel.open(Paths.get("F:\\wmContos\\contos_1\\cno2\\a.vi"), StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.READ);
        ) {
            MappedByteBuffer inByteBuffer = in.map(FileChannel.MapMode.READ_ONLY, 0, in.size());
            MappedByteBuffer outByteBuffer = out.map(FileChannel.MapMode.READ_WRITE, 0, in.size());

            byte[] bytes = new byte[inByteBuffer.limit()];
            inByteBuffer.get(bytes);
            outByteBuffer.put(bytes);



        }catch (Exception ex){

            System.out.println("ex:" + ex.getMessage());
        }
    }


}
