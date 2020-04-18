package com.itheima;

import com.lou.simhasher.SimHasher;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class SpringbootMybatisApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(SpringbootMybatisApplication.class, args);
		run.getBean("");

		System.out.println(9999);
		System.out.println(9999);
		System.out.println(9999);
		SimHasher hash1 = new SimHasher("haha");//自己打包jar原来是这么回事

		test();

	}

	private static void test() {

//		//XmlBeanFactory是典型的BeanFactory。
//		BeanFactory factory = new XmlBeanFactory("XXX.xml");
//		//获取一个叫做mdzz的bean。在这个时候进行实例化。
//		factory.getBean("mdzz");

		//当我们实例化XXX.xml的时候，该文件中配置的bean都会被实例化。（该bean scope是singleton）
		 ApplicationContext appContext = new ClassPathXmlApplicationContext("XXX.xml");
	}

}
