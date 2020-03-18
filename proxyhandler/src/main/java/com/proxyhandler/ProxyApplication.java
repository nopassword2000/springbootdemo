package com.proxyhandler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.context.ConfigurableApplicationContext;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URL;

@SpringBootApplication
public class ProxyApplication {

    public static void main(String[] args) throws  Exception, Throwable{
        //ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(ProxyApplication.class, args);



        SB hsb = new SB();
        MethodType methodType = MethodType.methodType(void.class);
        MethodHandle methodHandle = MethodHandles.lookup().findVirtual(SB.class,"sayProxy", methodType);
        methodHandle.invoke(hsb);


        MethodType m1 = MethodType.methodType(int.class, Integer.class);
        MethodHandle h1 = MethodHandles.lookup().findVirtual(SB.class, "sayInt",m1);
        int at = (int)h1.invoke(hsb,15);



        SB hsb2 = new SB();
        Constructor<MethodHandles.Lookup> constructor = MethodHandles.Lookup.class
                .getDeclaredConstructor(Class.class, int.class);
        if (!constructor.isAccessible()) {
            constructor.setAccessible(true);
        }

        final Class<?> declaringClass = SB.class;

        Method method = SB.class.getMethod("sayInt", Integer.class);
        MethodHandles.Lookup  lookup = constructor
                .newInstance(declaringClass,
                        MethodHandles.Lookup.PRIVATE | MethodHandles.Lookup.PROTECTED
                                | MethodHandles.Lookup.PACKAGE | MethodHandles.Lookup.PUBLIC);

        int at1 = (int)lookup.unreflectSpecial(method, declaringClass).bindTo(hsb2).invokeWithArguments(12);
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
