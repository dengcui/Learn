package com.learn.proxy;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibTest {

    public static void main(String[] args){
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "E:\\Hsassetmanage30");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HelloService.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("before method run...");
                Object result = proxy.invokeSuper(obj, args);
                System.out.println("after method run...");
                return result;
            }
        });
        HelloService helloService = (HelloService) enhancer.create();
        helloService.hello();
    }
}
