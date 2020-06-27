package com.future;

import java.util.concurrent.CompletableFuture;

public class CmpFutueRun  {

    public void runx(){


        CompletableFuture future = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(5000);
                }catch (InterruptedException ex){

                }
                System.out.println(System.currentTimeMillis() + " this is sleep future");
            }
        });


        CompletableFuture<String> future2 = CompletableFuture.supplyAsync( ()-> {
            try {
                Thread.sleep(2000);
            }catch (InterruptedException ex){

            }
            System.out.println(System.currentTimeMillis() + " this is furtue2");
            return "hello ";
        }).thenApply( ( x ) -> {

            return  x.toUpperCase();
        });

        CompletableFuture futur2rsut = future2.thenCombine(future2,(x, y) -> {

            System.out.println("print futrue2 resut" + x );
            return  x + " r1";
        });


        CompletableFuture<String> future_  = futur2rsut.thenAcceptBothAsync( CompletableFuture.supplyAsync(()->{

            try {
                Thread.sleep(1000);
            }catch (InterruptedException ex){

            }
            return  "future_ ..";
        }),(x, y)->{

            System.out.println("x:" + x + " y:" + y);
        });

        try {
            System.out.println( future_.get());
        }catch ( Exception ex){

        }

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync( () ->{
            try {
                Thread.sleep(1000);
            }catch (InterruptedException ex){

            }
            System.out.println(System.currentTimeMillis() + " this is sleep future");
            return  "mid";
        });



        CompletableFuture completableFuture = future2.thenCombine(CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(3000);
            }catch (Exception ex){

            }
            return " future2 function ";
        }),(x, y) -> {

           return x + y;
        });

        try {
            System.out.println( completableFuture.get());
        }catch ( Exception ex){

        }

    }



}
