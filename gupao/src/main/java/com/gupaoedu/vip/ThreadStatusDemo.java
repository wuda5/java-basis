package com.gupaoedu.vip;

import java.util.concurrent.TimeUnit;

/**
* 终端 jps
 * 拿到 ThreadStatusDemo 的线程id
 * 再 jstack pid
 */
public class ThreadStatusDemo {

    public static void main(String[] args) {
        System.out.println("-------- start ---- test");
        /** 1. 测试 sleep-- Time_Waiting_Thread **/
        new Thread(()->{
            while(true){
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Time_Waiting_Thread【1】").start();

        /** 2. 测试 wait-- Wating_Thread **/
        new Thread(()->{
            while(true){
                synchronized (ThreadStatusDemo.class) {
                    try {
                        ThreadStatusDemo.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"Wating_Thread【2】").start();

    //BLOCKED
        /**Blocke01_Thread" --》java.lang.Thread.State: TIMED_WAITING (sleeping)
         */
        new Thread(new BlockedDemo(),"Blocke01_Thread").start();

        /** "Blocke02_Thread --》java.lang.Thread.State: BLOCKED (on object monitor)
         */
        new Thread(new BlockedDemo(),"Blocke02_Thread").start();
    }
    static class BlockedDemo extends  Thread{

        @Override
        public void run() {
            synchronized (BlockedDemo.class){
                while(true){
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
