package com.example.ProxyServices;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyImplementor {
    public static <T> Object createClass(T instanceClass) {
        InvocationHandler handler = new DemoInvocationHandler<>(instanceClass);

        return Proxy.newProxyInstance(ProxyImplementor.class.getClassLoader(), instanceClass.getClass().getInterfaces(), handler);
    }

    static class DemoInvocationHandler<T> implements InvocationHandler {
        private final T myClass;

        DemoInvocationHandler(T myClass) {
            this.myClass = myClass;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if(myClass.getClass().getMethod(method.getName(), method.getParameterTypes()).isAnnotationPresent(com.example.Annotations.Log.class)) System.out.println("Proxy-method / proxied logged message: " + method);
            return method.invoke(myClass, args);
        }

        @Override
        public String toString() {
            return "DemoInvocationHandler{" +
                    "myClass=" + myClass +
                    '}';
        }
    }
}
