package org.springframework.test.ioc;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.bean.A;
import org.springframework.test.bean.B;
import org.springframework.test.bean.C;

public class CircularReferenceWIthProxyBeanTest2 {
    /**
     * 无循环依赖的代理对象，二级缓存为空，且allowEarlyReference为false，不查三级缓存，将初始化后的bean添加到一级缓存中！没毛病
     */
    @Test
    public void test1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:circular-reference-with-proxy-bean-2.xml");
        C c = (C) context.getBean("c");
        c.sayHello();
    }

    /**
     * 循环依赖的代理对象，二级缓存不为空，且allowEarlyReference为false，不查三级缓存，将二级缓存中的bean添加到一级缓存，保证其他依赖该类的bean的属性 和 该bean一致！
     */
    @Test
    public void test2(){
        ApplicationContext context =new ClassPathXmlApplicationContext("classpath:circular-reference-with-proxy-bean.xml");
        //A为代理对象
        A a = (A) context.getBean("a");
        //B为普通对象
        B b = (B) context.getBean("b");

        a.func();
    }

}
