package org.springframework.test.service;

/**
 * @author derekyi
 * @date 2020/12/6
 */
public class WorldServiceImpl implements WorldService {

	@Override
	public boolean explode() {
		System.out.println("The Earth is going to explode");
		return true;
	}
}
