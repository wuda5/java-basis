package com.guigu.juc.synchronize.lock_56;


import java.util.concurrent.TimeUnit;
/*
* static 锁56--锁的不是对象，锁定的是 。class类模板！！！！！！！！
* */

class Phone {

    //发短信--延迟了4s
    public static synchronized void sendEmail () {
        try {
            TimeUnit.SECONDS.sleep(4);//
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "---发邮件-【有设置延迟的】--sendEmail");
    }

    //发邮件
    public  static synchronized void sendSMS() {
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

 * 5 两个静态同步方法，1部手机，先打印短信还是邮件--邮件
 * 6 两个静态同步方法，2部手机，先打印短信还是邮件--邮件
 *
 * 7 。1个静态同步方法,1个普通同步方法，1部手机，先打印短信还是邮件
 * 8 。1个静态同步方法,1个普通同步方法，2部手机，先打印短信还是邮件
 * ---------------------------------

 */
public class Lock_56 {
    public static void main(String[] args) throws Exception {

        Phone phone = new Phone();
        Phone phone2 = new Phone();

        //---邮件
        new Thread(() -> phone.sendEmail(), "AA1").start();

        Thread.sleep(100);
        //---发短信
        new Thread(() -> phone2.sendSMS(), "BB2").start();// 6锁
//        new Thread(() -> phone.sendSMS(), "bb2").start();//5锁
    }
}


