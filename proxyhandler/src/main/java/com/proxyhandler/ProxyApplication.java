package com.proxyhandler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.context.ConfigurableApplicationContext;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;
import java.net.URL;

@SpringBootApplication
public class ProxyApplication {

    public static void main(String[] args) {
        //ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(ProxyApplication.class, args);

        /**
         * jdk
         */



        Object object = Proxy.newProxyInstance(SB.class.getClassLoader(),SB.class.getInterfaces(),new ProxyInvokerHandler(new SB()));
        ISB sb = (ISB)object;
        sb.sayProxy();
        saveClassFile(object.getClass(), object.getClass().getName());

        /**
         * cglib
         */



        String path = ProxyApplication.class.getResource("/").getPath();
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, path);
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SB.class);
        enhancer.setCallback(new CglibProxyIntercptor());
        SB sbD = (SB) enhancer.create();
        sbD.sayProxy();


    }

    public static void saveClassFile(Class clazz,String proxyName) {
        //生成class的字节数组，此处生成的class与proxy.newProxyInstance中生成的class除了代理类的名字不同，其它内容完全一致
        byte[] classFile = ProxyGenerator.generateProxyClass(proxyName, clazz.getInterfaces());
        //URL url = ProxyApplication.class.getResource(".");
        String path = ProxyApplication.class.getResource(".").getPath();

        try( FileOutputStream fos = new FileOutputStream(path + proxyName + ".class")) {
            fos.write(classFile);
            fos.flush();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

}
