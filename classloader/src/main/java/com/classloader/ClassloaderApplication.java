package com.classloader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClassloaderApplication {

    public static void main(String[] args) throws  Exception{
        //SpringApplication.run(ClassloaderApplication.class, args);
        Class cls = Class.forName("com.jni.JNIhi");
        System.out.println(cls);



    }

}
