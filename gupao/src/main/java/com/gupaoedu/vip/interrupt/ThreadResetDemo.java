package com.gupaoedu.vip.interrupt;

import java.util.concurrent.TimeUnit;

/**
 标志interrupt 复位方式【1】--> Thread.interrupted()
 */
public class ThreadResetDemo {

    //1. Thread.interrupted()
    //2. InterruptedException

    public static void main(String[] args) throws InterruptedException {
        Thread thread=new Thread(()->{
            while(true){//默认是false  _interrupted state?
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("before:"+Thread.currentThread().isInterrupted());
                    Thread.interrupted(); //复位- 回到初始状态,如果不加这个，这里就会一直循环打印了
                    System.out.println("after:"+Thread.currentThread().isInterrupted());


                }
            }
        });
        thread.start();

        TimeUnit.SECONDS.sleep(5);
        System.out.println("外部来中断---");
        thread.interrupt(); //把isInterrupted设置成true---中断线程
    }
}
