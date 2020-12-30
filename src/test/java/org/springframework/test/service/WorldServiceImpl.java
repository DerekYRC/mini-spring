package org.springframework.test.service;

/**
 * @author derekyi
 * @date 2020/12/6
 */
public class WorldServiceImpl implements WorldService {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void explode() {
        System.out.println("The Earth is going to explode");
        System.out.println("name: " + getName());
    }
}
