package com.proxyhandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyInvokerHandler implements InvocationHandler {

    Object object;

    public ProxyInvokerHandler(Object object){
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        method.invoke(object,args);
        return null;
    }
}
