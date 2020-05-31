package com.restpool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class RestpoolApplication {

    public static void main(String[] args)  throws  Exception{
       // SpringApplication.run(RestpoolApplication.class, args);

        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(HttpCli.getHttpClient());
        RestTemplate restTemplate = new RestTemplate(httpComponentsClientHttpRequestFactory);
        AtomicInteger atomicI = new AtomicInteger(0);
        String url = "https://127.0.0.1:8080/user//hello";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url,String.class);
        System.out.println("this is count ==================== :" + atomicI.incrementAndGet());
        if (responseEntity.getStatusCodeValue() == 200 ){

            System.out.println(Thread.currentThread().getId() + "--------------" + responseEntity.getBody());
        }


        for (int i=0; i<  100; i++){
            Thread thread = new Thread(){
                @Override
                public void run() {
                    for (int i = 0; i< 10000; i++){


                    String url = "https://127.0.0.1:8080/user//hello";
                    ResponseEntity<String> responseEntity = restTemplate.getForEntity(url,String.class);
                    System.out.println("this is count ==================== :" + atomicI.incrementAndGet());
                    if (responseEntity.getStatusCodeValue() == 200 ){

                        System.out.println(Thread.currentThread().getId() + "--------------" + responseEntity.getBody());
                    }else{
                        System.out.println("is ierr");
                    }
                    }
                }
            };

            thread.start();
        }


        Thread.currentThread().join();

    }

}
