package com.guigu.single;

/*
/*
 * 饿汉式：
 * 	在类初始化时直接创建实例对象，不管你是否需要这个对象都会创建
 *
 * （1）构造器私有化
 * （2）自行创建，并且用静态变量保存
 * （3）向外提供这个实例
 * （4）强调这是一个单例，我们可以用final修改
 */
public class Singleton1 {
	// 定义为了final 常量,编译的时候就初始好了,而非等到类初始化阶段???,如果不用final那么
	public static final Singleton1 INSTANCE = new Singleton1();
	public static final int x = 4;
	private Singleton1(){
		
	}
}
