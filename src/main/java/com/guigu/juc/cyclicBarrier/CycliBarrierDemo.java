package com.guigu.juc.cyclicBarrier;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;

/**
 * @Description : TODO          CyclicBarrier是并发编程辅助工具之一
 * @Author :    yangguang
 * @Date :      2019/11/21
 */
@Slf4j
public class CycliBarrierDemo {
    /**
     *
     * 和 CountDownLatch 的机制非常类似,
     * 但 CyucliBarrier 比CountDownLatch 更强大 CycliBarrier 是一个线程做完就等,
     * 一个线程做完就等,相当于加法,直到一定数量的线程完成后才做最终的事
     *
     */
    public static void main(String[] args) {


        //--我要集合7可龙珠！！！！！！
        CyclicBarrier cyclicBarrier = new CyclicBarrier(6,()->{
            System.out.println("已经有6可龙珠都到齐了,加上我自己的龙珠--可以召唤神龙了");
//            log.info("已经有6可龙珠都到齐了,加上我自己的龙珠--可以召唤神龙了");
        });

        for (int i = 1 ; i <= 20; ++i)
        {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + " 到了");
//                log.info(Thread.currentThread().getName() + " 到了");
                try
                {
                    //在线程内部等待
                    cyclicBarrier.await();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            },i+"").start();
        }
    }


}
