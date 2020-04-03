package com.guigu.juc.threadpool;

import java.util.concurrent.*;


public class ThreadPoolDemoWu {

    /**
     * 线程池的知识已在 README 详细讲解了
     *
     * 这里作为测试,自定义线程池
     */

    public static void main(String[] args)
    {
        //获取本机cpu核心数(本机为8)
//        int coreSize = Runtime.getRuntime().availableProcessors();


        //创建自定义线程池
        ExecutorService threadPool =
                new ThreadPoolExecutor(5,
                        8,
                        3L, TimeUnit.SECONDS,
                        new LinkedBlockingQueue<>(30),
                        Executors.defaultThreadFactory(),
                        new   ThreadPoolExecutor.AbortPolicy());//默认的CallerRunsPolicy策略
//                        new ThreadPoolExecutor.CallerRunsPolicy());//默认的CallerRunsPolicy策略

        try
        {
            //这里任务就超出了线程池的最大限制,可以调整 拒绝策略,看看程序有啥反应
            //超出的线程有main线程执行
            for (int i = 0 ; i < 20  ;++i)
            {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "任务执行");
                    try {
                        Thread.sleep(3000);
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                });
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally {
            threadPool.shutdown();
        }
    }
}
