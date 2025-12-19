package org.springframework.test.ioc;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.bean.A;
import org.springframework.test.bean.B;
import org.springframework.test.bean.C;
import org.springframework.test.service.HelloService;

public class CircularReferenceWIthProxyBeanTest2 {

    /**
     * 测试1：无循环依赖的普通对象 (spring.xml配置文件中的helloService对象)
     *
     * BeanPostprocessor后置处理器返回原始bean，收尾阶段，二级缓存中无值，说明无循环依赖，将初始化的bean添加到一级缓存
     */
    @Test
    public void test1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        HelloService helloService = context.getBean("helloService", HelloService.class);
        helloService.sayHello();
    }


    /**
     * 测试2: 无循环依赖的代理对象 (circular-reference-with-proxy-bean-2.xml中的c对象)
     *
     * BeanPostprocessor后置处理器返回代理bean，收尾阶段，二级缓存中无值，说明无循环依赖，将代理bean添加到一级缓存
     */
    @Test
    public void test2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:circular-reference-with-proxy-bean-2.xml");
        C c = (C) context.getBean("c");
        c.sayHello();
    }


    /**
     * 测试3：存在循环依赖的普通对象 (circular-reference-without-proxy-bean.xml中的a,b对象)
     *
     * BeanPostprocessor后置处理器返回普通bean,收尾阶段，二级缓存中有值，说明有循环依赖，为了确保a.getB() == b ，将二级缓存中bean添加到一级缓存
     *
     */
    @Test
    public void test3() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:circular-reference-without-proxy-bean.xml");
        //A为普通对象
        A a = (A) context.getBean("a");
        //B为普通对象
        B b = (B) context.getBean("b");

        Assert.assertTrue(a.getB() == b);
        Assert.assertTrue(b.getA() == a);
    }


    /**
     * 测试4：存在循环依赖的代理对象(circular-reference-with-proxy-bean.xml 中的a对象)
     *
     * BeanPostprocessor后置处理器跳过执行，收尾阶段，二级缓存中有代理对象，将二级缓存添加到一级缓存中
     */
    @Test
    public void test4() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:circular-reference-with-proxy-bean.xml");
        //A为代理对象
        A a = (A) context.getBean("a");
        //B为普通对象
        B b = (B) context.getBean("b");

        a.func();

        //对象a的二级缓存和一级缓存内容一致，因此b.getA() == a 为 true
        Assert.assertTrue(b.getA() == a);
    }

}
