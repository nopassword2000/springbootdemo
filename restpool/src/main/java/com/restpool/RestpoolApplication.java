package com.restpool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestpoolApplication {

    public static void main(String[] args)  throws  Exception{
       // SpringApplication.run(RestpoolApplication.class, args);

        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(HttpCli.getHttpClient());
        RestTemplate restTemplate = new RestTemplate(httpComponentsClientHttpRequestFactory);

        for (int i=0; i<  100; i++){
            Thread thread = new Thread(){
                @Override
                public void run() {
                    String url = "https://127.0.0.1:8080/user//hello";
                    ResponseEntity<String> responseEntity = restTemplate.getForEntity(url,String.class);
                    if (responseEntity.getStatusCodeValue() == 200 ){

                        System.out.println(Thread.currentThread().getId() + "--------------" + responseEntity.getBody());
                    }
                }
            };

            thread.start();
        }


        Thread.currentThread().join(5000);

    }

}
