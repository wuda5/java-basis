package com.guigu.juc.communicate.lock;

import java.util.concurrent.atomic.AtomicInteger;

public class PrintSequenceThread implements Runnable {

    private static final Object LOCK = new Object();

    /**
     * 当前即将打印的数字
     */
    private static AtomicInteger currentValue =new AtomicInteger(0);;


    /**
     * 当前线程编号，从0开始
     */
    private int threadNo;

    /**
     * 线程数量
     */
    private int threadCount;

    /**
     * 打印的最大数值
     */
    private int max;

    public PrintSequenceThread(int threadNo, int threadCount, int max) {
        this.threadNo = threadNo;
        this.threadCount = threadCount;
        this.max = max;
    }

    @Override
    public void run() {

        /**用while true，是要让每个线程都执行此逻辑中下面的 加 操作  控制在5次！！*/
        while(true) {
            synchronized (LOCK) {

                // 判断是否轮到当前线程执行
                while (currentValue.get() % threadCount != threadNo) {
                    if (currentValue.get() > max) {
                        break;
                    }
                    try {
                        // 如果不是，则当前线程进入wait--wait会释放锁，notify仅仅只是通知，不释放锁)
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 最大值跳出循环
                    if (currentValue.get() > max) {
                        break;
                    }
                System.out.println("thread-" + threadNo + " : " + currentValue);

                currentValue.incrementAndGet();
//                for (int i = 1; i <= 5; i++) {
//                    // 最大值跳出循环
//                    if (currentValue.get() > max) {
//                        break;
//                    }
//                     System.out.println(Thread.currentThread().getName() +"["+threadNo+ "]--currentVule [ " + currentValue  );
//                    currentValue.incrementAndGet();
//                }
                // 唤醒其他wait线程--（notify()或者notifyAll()方法并不是真正释放锁，必须等到synchronized方法或者语法块执行完才真正释放锁）
                LOCK.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        int threadCount = 5;
        int max = 100;

        for(int i=0; i<threadCount;i++) {

            new Thread(new PrintSequenceThread(i,threadCount, max))
                    .start();
        }
    }
}