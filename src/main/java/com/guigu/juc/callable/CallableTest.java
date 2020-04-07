package com.guigu.juc.callable;

import java.util.concurrent.*;

//public class Task implements Callable<Integer>{
//
//    @Override
//    public Integer call() throws Exception {
//        System.out.println("子线程在进行计算");
//        Thread.sleep(3000);
//        int sum = 0;
//        for(int i=0;i<100;i++)
//            sum += i;
//        return sum;
//    }
//
//}

public class CallableTest {
    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.start();

        //创建线程池--ThreadPoolExecutor
        ExecutorService executor = Executors.newFixedThreadPool(3);
//        ExecutorService executor = Executors.newCachedThreadPool();


        //创建Callable对象任务 //        Task task = new Task();
        Callable<Integer> task = ()->{
            System.out.println("子线程在进行计算");
            Thread.sleep(3000);
            int sum = 0;
            for(int i=0;i<10;i++){
                sum += i;
                System.out.println("子线程在--> 计算 中："+i);
            }

            return sum;
        };

        System.out.println("task--:"+executor.getClass().getName());
        //提交任务并获取执行结果
        Future<Integer> result = executor.submit(task);
        //关闭线程池
        executor.shutdown();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        System.out.println("主线程在执行任务");

        try {
            if(result.get()!=null){
                System.out.println("task运行结果"+result.get());
            }else{
                System.out.println("未获取到结果");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("所有任务执行完毕");
    }

}
