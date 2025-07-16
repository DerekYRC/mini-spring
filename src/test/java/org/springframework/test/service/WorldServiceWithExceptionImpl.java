package org.springframework.test.service;
/**
 * @author NingMao
 * @since 2025-07-16
 */
public class WorldServiceWithExceptionImpl implements WorldService{
    @Override
    public void explode() {
        System.out.println("The Earth is going to explode with an Exception");
        throw new RuntimeException();
    }


}