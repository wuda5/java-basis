package com.guigu.juc.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @Description : TODO          信号量--争夺车位
 * @Author :    yangguang
 * @Date :      2019/11/21
 */
public class SemaphoreDemo {
    /**
     *
     * Semaphore 用于控制多线车公对多资源的控制
     *
     */

    //模拟多个小朋友抢凳子的游戏0
    public static void main(String[] args)
    {
        //一共有3个凳子（3个资源！！）
        Semaphore semaphore = new Semaphore(3);

        //模拟5个小朋友--抢上面的三个资源---
        for (int i = 0 ; i < 6; ++i)
        {
            new Thread(()->{
               try
               {
                   System.out.println("当前线程：【"+ Thread.currentThread().getName());
                   semaphore.acquire();//占用！！
                   System.out.println(Thread.currentThread().getName() + "******** 抢到了凳子");
                   Thread.sleep(2000);
                   System.out.println(Thread.currentThread().getName() + "+++++++离开了凳子,要semaphore.release()才是真的释放哈");
               }
               catch(Exception e)
               {
                   e.printStackTrace();
               } finally {
//                   semaphore.release();
//                   semaphore.release();//要是不释放，其他线程--就会一直卡在
               }
            }).start();
        }
    }
}
