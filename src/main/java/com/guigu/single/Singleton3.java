package com.guigu.single;

import java.io.IOException;
import java.util.Properties;
/**
 * 饿汉,灵活读取外部配置,初始数据,
 * **/
public class Singleton3 {
	public static final Singleton3 INSTANCE;
	private String info;
	
	static{
		try {
			Properties pro = new Properties();
			
//			pro.load(Singleton3.class.getClassLoader().getResourceAsStream("single.properties"));
			
			INSTANCE = new Singleton3(pro.getProperty("info"));
//			INSTANCE = new Singleton3("jj");// 编译都不过?
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private Singleton3(String info){
		this.info = info;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "Singleton3 [info=" + info + "]";
	}
	
}
