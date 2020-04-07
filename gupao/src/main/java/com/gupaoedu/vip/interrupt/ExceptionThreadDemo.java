package com.gupaoedu.vip.interrupt;

import java.util.concurrent.TimeUnit;

/**
* 标志interrupt 复位方式2--> 异常InterruptedException
 */
public class ExceptionThreadDemo {
    private static int i;

    public static void main(String[] args) throws InterruptedException {
        Thread thread=new Thread(()->{
            while(!Thread.currentThread().isInterrupted()){//默认是false(代表未中断)  _interrupted state?

                try {
                    System.out.println("demo");
                    /***注意：这里让睡了s */
                    TimeUnit.SECONDS.sleep(1); //中断一个处于阻塞状态的线程。join/wait/queue.take..

                } catch (InterruptedException e) {
                    e.printStackTrace();//异常会标致复位--即变为fasle,为了避免再次判断后又会进打印 demo，所以下面马上break中止循环
                    break;
                }
            }
            System.out.println("i:"+i);
        });
        thread.start();

        TimeUnit.SECONDS.sleep(5);
        thread.interrupt(); //把isInterrupted设置成true

        System.out.println("last: "+thread.isInterrupted()); //true
    }
}
