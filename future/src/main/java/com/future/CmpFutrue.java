package com.future;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class CmpFutrue {

    public void run1(){

        CompletableFuture completableFuture = CompletableFuture.runAsync(() -> {
                System.out.println("1111111111111");
        });

        completableFuture.whenComplete(new BiConsumer<Object,Throwable>() {
            @Override
            public void accept(Object o, Throwable throwable) {
                System.out.println("11111");
            }

            @Override
            public BiConsumer<Object, Throwable> andThen(BiConsumer<? super Object, ? super Throwable> after) {
                System.out.println("2222");
                return null;
            }
        });
    }


    public void run2() throws  Exception{
        CompletableFuture completableFuture = CompletableFuture.supplyAsync(() -> {
                return "123456";
        }).whenComplete(new BiConsumer<String, Throwable>() {
            @Override
            public void accept(String s, Throwable throwable) {
                System.out.println("s========" +s);
            }
        }).whenCompleteAsync(new BiConsumer<String, Throwable>() {
            @Override
            public void accept(String s, Throwable throwable) {
                System.out.println("y========" + s);
            }
        }).exceptionally(new Function<Throwable, String>() {
            @Override
            public String apply(Throwable throwable) {
                return "66666";
            }
        });
        Object x = completableFuture.get();
        System.out.println("z============" + x);
        completableFuture.handle(new BiFunction<String, Throwable, Object>(){
            @Override
            public Object apply(String s, Throwable throwable) {
                System.out.println("a============" + s);
                return null;
            }

            @Override
            public <V> BiFunction<String, Throwable, V> andThen(Function<? super Object, ? extends V> after) {

                return null;
            }
        });
    }

    public void run3() throws Exception{


        CompletableFuture completableFuture = new CompletableFuture();

        completableFuture.thenApply( (fn) ->{

            return null;
        });
    }
}
