package com.jni;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class JNIhi {

    static RestTemplate restTemplate = new RestTemplate();
    public static String hi(String hil){

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://www.baidu.com", String.class);
        System.out.println(responseEntity.getBody());
        return  hil + responseEntity.getBody();
    }

    public String phi(){

        return "hi";
    }

}
