package com.itheima;

import com.itheima.bean.factoryTest;
import com.itheima.domain.People;
import com.itheima.utils.SpringUtil;
import com.itheima.utils.base2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import spring.base.base;

@RunWith(SpringRunner.class)
@SpringBootTest
//@SpringBootTest(classes = SpringbootMybatisApplication.class)
public class IOCTest {

//    private ApplicationContext ioc =new ClassPathXmlApplicationContext("");

    @Test
    public void testbase(){
        System.out.println("users");

        Object user1 = SpringUtil.getBean(base2.class);
        Object user2 = SpringUtil.getBean(base2.class);

        System.out.println("打印：user1: "+user1);
        System.out.println("打印：user2: "+user2);
        System.out.println("user是否等user2: ["+user1==user2);
    }

    @Test
    public void test(){
        System.out.println("users");

        Object test = SpringUtil.getBean(factoryTest.class);
        Object test2 = SpringUtil.getBean(factoryTest.class);//有问题！！！！！！！！！！！
//        Object test = SpringUtil.getBean("g");
//        Object test2 = SpringUtil.getBean("g");

        System.out.println("people1:"+test);
        System.out.println("people2:"+test2);
        System.out.println("people is equla? ["+test==test2);


        System.out.println("-----------------fen ge xian ---------");
        System.out.println("peopel 中 new count is [ "+People.count);
//        boolean a = new People()==new People();
//        System.out.println("see  peep de address :"+ new People()+"---],[ "+ new People());
//        System.out.println("-- people is equla? ["+test==test2);
    }












    @Test
    public void test3() throws Exception {
        System.out.println("users");

        factoryTest factory = SpringUtil.getBean(factoryTest.class);
        People p1 = factory.getObject();
        People p2 = factory.getObject();

        System.out.println("people1:"+factory.getObject());
        System.out.println("people2:"+factory.getObject());
        boolean a =false;
        if (p1 ==p2)
        {
            a=true;
        }
        System.out.println("people is equla? ["+a );


        System.out.println("-----------------fen ge xian ---------");
        System.out.println("people count is [ "+People.count );
    }

}
