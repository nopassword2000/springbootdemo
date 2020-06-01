package com.future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Callable {

    public Future<Integer> runx() throws  Exception{
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Integer> future = executor.submit(new CTask());
        return future;
    }

    static  class CTask implements java.util.concurrent.Callable<Integer>{

        @Override
        public Integer call() throws Exception {
            return 20;
        }
    }
}
