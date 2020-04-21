package com.nio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

public class AysnFile {


    public void readFile() throws  Exception{

        Path path = Paths.get("F:\\wmContos\\contos_1\\contos_1-s002.vmdk");
        AsynchronousFileChannel asynchronousFileChannel =  AsynchronousFileChannel.open(path, StandardOpenOption.READ);
        long  filesize = asynchronousFileChannel.size();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        long position = 0;
        Future<Integer> operation = asynchronousFileChannel.read(buffer, position);
        while(!operation.isDone());
        buffer.flip();
        byte[] data = new byte[buffer.limit()];
        buffer.get(data);
        System.out.println(new String(data));
        buffer.clear();

    }

    public void asyRead() throws Exception{
        Path path = Paths.get("G:\\cloud.zip");
        Path pathw = Paths.get("G:\\cloud2.zip");
        if(!Files.exists(pathw)){
            Files.createFile(pathw);
        }
        AsynchronousFileChannel asynchronousFileChannel =  AsynchronousFileChannel.open(path, StandardOpenOption.READ);
        AsynchronousFileChannel asynchronousFileChannelw =  AsynchronousFileChannel.open(pathw, StandardOpenOption.WRITE);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //buffer.put("this is ok ok-----------------------------".getBytes());
        long position = 0;
        buffer.flip();
        asynchronousFileChannel.read(buffer, position, buffer, new CompletionHandlerImpl(asynchronousFileChannel, asynchronousFileChannelw, position));
        //asynchronousFileChannelw.write(buffer, position, buffer, new CompletionHandlerImplw(asynchronousFileChannelw));
    }
}


class  CompletionHandlerImpl implements CompletionHandler<Integer, ByteBuffer>{

    AsynchronousFileChannel asynchronousFileChannel;
    AsynchronousFileChannel asynchronousFileChannelw;
    long position;
    public CompletionHandlerImpl(AsynchronousFileChannel asynchronousFileChannel, AsynchronousFileChannel asynchronousFileChannelw,long position){
        this.asynchronousFileChannel = asynchronousFileChannel;
        this.asynchronousFileChannelw = asynchronousFileChannelw;
        this.position = position;
    }
    @Override
    public void completed(Integer result, ByteBuffer attachment) {


        attachment.flip();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(attachment);
        if (result > 0){

            asynchronousFileChannelw.write(byteBuffer, position, byteBuffer, new CompletionHandlerImplw(asynchronousFileChannelw));
        }
        System.out.println(new String(attachment.array()));
        position += attachment.limit();

        attachment.clear();

        try {
            if (position != asynchronousFileChannel.size()){
                asynchronousFileChannel.read(attachment, position, attachment, new CompletionHandlerImpl(asynchronousFileChannel, asynchronousFileChannelw, position));

            }
        }catch (Exception ex){

        }


    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {

        System.out.println("file error");
    }


}

class  CompletionHandlerImplw implements CompletionHandler<Integer, ByteBuffer>{

    AsynchronousFileChannel asynchronousFileChannel;

    public CompletionHandlerImplw(AsynchronousFileChannel asynchronousFileChannel){

        this.asynchronousFileChannel = asynchronousFileChannel;
    }
    @Override
    public void completed(Integer result, ByteBuffer attachment) {


        System.out.println("result :" + result);
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {

    }
}