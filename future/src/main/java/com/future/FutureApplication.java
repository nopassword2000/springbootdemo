package com.future;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@SpringBootApplication
public class FutureApplication {

    public static void main(String[] args) throws Exception {
        //SpringApplication.run(FutureApplication.class, args);

        Callable callable = new Callable();
        Future<Integer> future = callable.runx();
        System.out.println(future.get());

        CmpFutrue cmpFutrue = new CmpFutrue();
        cmpFutrue.run2();


    }

}
