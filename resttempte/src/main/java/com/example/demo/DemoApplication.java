package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) throws Exception{
        //SpringApplication.run(DemoApplication.class, args);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> responseEntity = restTemplate.exchange("https://www.baidu.com", HttpMethod.GET, null, String.class);
        String body = responseEntity.getBody();
        String bodyx = new String(body.getBytes("ISO-8859-1"),Charset.forName("UTF-8"));
        System.out.println(bodyx);
    }

}
