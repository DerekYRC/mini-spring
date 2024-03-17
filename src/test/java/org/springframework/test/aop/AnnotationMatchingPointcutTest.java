package org.springframework.test.aop;

import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.aop.support.AnnotationMatchingPointcut;
import org.springframework.beans.factory.annotation.AfterAnnotation;
import org.springframework.beans.factory.annotation.BeforeAnnotation;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.bean.A;
import org.springframework.test.bean.B;
import org.springframework.test.bean.ChildOfA;
import org.springframework.test.bean.ChildOfB;

/**
 * @Author:sfy
 * @Date: 2024/3/17 - 17:05
 * Description: Pointcut支持Annotation的相关测试
 */
public class AnnotationMatchingPointcutTest extends TestCase {

    /**
     * Description: 测试注解写在类上的匹配效果
     * 暂时设计下，类继承。
     * 因为B类上有@AfterAnnotation注解，故此只有b.func()，b.func2()，以及子类childOfB.func()方法都获得增强
     * date: 2024/3/18
     * @author: sfy
     */
    @Test
    public void testInheritInClass() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:annotation-scan.xml");
        B b = (B) applicationContext.getBean("b");
        ChildOfB child = (ChildOfB) applicationContext.getBean("childOfB");
        assertNotSame("org.springframework.test.bean.B", b.getClass().getName());
        assertNotSame("org.springframework.test.bean.ChildOfB", child.getClass().getName());

        b.func();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>");
        child.func();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>");
        b.func2();
    }

    /**
     * Description: 测试注解写在方法上的匹配效果
     * 暂时设计下，方法不继承。
     * 因为A只有func()上有@BeforeAnnotation注解，故此只有a.func()方法获得增强，子类和其他方法都无法获得增强
     * date: 2024/3/18
     * @author: sfy
     */
    @Test
    public void testInheritInMethod() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:annotation-scan.xml");
        A a = (A) applicationContext.getBean("a");
        ChildOfA child = (ChildOfA) applicationContext.getBean("childOfA");
        assertNotSame("org.springframework.test.bean.A", a.getClass().getName());
        assertEquals("org.springframework.test.bean.ChildOfA", child.getClass().getName());

        a.func();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>");
        child.func();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>");
        a.func2();
    }
}