package com.guigu.juc.synchronize;


import java.util.concurrent.TimeUnit;


class Phone {

    //发短信--延迟了4s
    public synchronized void sendEmail () {
        try {
            TimeUnit.SECONDS.sleep(4);//
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "---发邮件-【有设置延迟的】--sendEmail");
    }

    //发邮件
    public synchronized void sendSMS() {
        System.out.println(Thread.currentThread().getName() + "---短信【no延迟】--sendSMS-");
    }

    //--当成手机壳
    public void getHello() {
        System.out.println("---3---getHello");
    }

}

/**
 * @Description: 8锁
 * <p>
 * 1 标准访问，先打印短信还是邮件
 * 2 停4秒在短信方法内，先打印短信还是邮件
 * 3 新增普通的hello方法，是先打短信还是hello
 *
 * 4 现在有两部手机，先打印短信还是邮件---             短信--因为：相当手机对象有两个，可以有两把锁！
 * 5 两个静态同步方法，1部手机，先打印短信还是邮件
 * 6 两个静态同步方法，2部手机，先打印短信还是邮件
 * 7 1个静态同步方法,1个普通同步方法，1部手机，先打印短信还是邮件
 * 8 1个静态同步方法,1个普通同步方法，2部手机，先打印短信还是邮件
 * ---------------------------------
 * 运行答案：
 * 1、短信
 * 2、短信
 * 3、Hello
 * 4、邮件----
 * 5、短信
 * 6、短信
 * 7、邮件
 * 8、邮件
 */
public class Lock_14 {
    public static void main(String[] args) throws Exception {

        Phone phone = new Phone();
        Phone phone2 = new Phone();

        //---邮件
        new Thread(() -> phone.sendEmail(), "AA1").start();

        Thread.sleep(100);
        //---发短信
        new Thread(() -> phone2.sendSMS(), "BB2").start();// 4.
//        new Thread(() -> phone.sendSMS(), "AA").start();
    }
}


