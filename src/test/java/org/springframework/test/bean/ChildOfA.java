package org.springframework.test.bean;

import org.springframework.beans.factory.annotation.AfterAnnotation;
import org.springframework.beans.factory.annotation.BeforeAnnotation;

/**
 * @Author:sfy
 * @Date: 2024/3/17 - 17:12
 * Description:
 */

public class ChildOfA extends A{
//    @BeforeAnnotation
    public void func() {
        System.out.println("ChildOfA func has done...");
    }
}
