package com.guigu.single.test;

import com.guigu.single.Singleton1;

public class TestSingleton1 {

	public static void main(String[] args) {
		int x = Singleton1.x;
		Singleton1 s = Singleton1.INSTANCE;
		System.out.println(s);
	}

}
