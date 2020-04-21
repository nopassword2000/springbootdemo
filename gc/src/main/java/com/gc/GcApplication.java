package com.gc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GcApplication {

    public static void main(String[] args) {


        Tx tx = new Tx();
        tx.start();
        SpringApplication.run(GcApplication.class, args);




    }

    static class Tx extends Thread{

        @Override
        public void run() {

            for (int i=0; i < 5000000; i++){
                int memSize =  1024;
                byte[] bytes = new byte[5 * memSize];
                try {
                    sleep(200);
                }catch (Exception ex){

                }

            }

        }
    }

}
