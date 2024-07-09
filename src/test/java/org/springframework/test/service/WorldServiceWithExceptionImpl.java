package org.springframework.test.service;

/**
 * @Date 2024/6/1 17:08
 * @Created by weixiao
 */
public class WorldServiceWithExceptionImpl implements WorldService {
    @Override
    public void explode() {
        System.out.println("The Earth is going to explode with an Exception");
        throw new RuntimeException();
    }
}
