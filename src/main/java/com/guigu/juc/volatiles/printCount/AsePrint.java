package com.guigu.juc.volatiles.printCount;

import java.util.ArrayList;
import java.util.List;

public class AsePrint {
    protected static Integer value = 0;

    volatile public static boolean next = false;

    static Object lockObject = new Object();

    static boolean flag = true;

    private List<PrintThread> createThreadObject(int num) {
        List<PrintThread> list = new ArrayList<PrintThread>();
        PrintThread printThread = null;
        for (int i = 0; i < num; i++) {
            printThread = new PrintThread();
            list.add(printThread);
        }
        return list;
    }

    public static void main(String[] args) throws InterruptedException {
        // PrintThread printThread = new PrintThread();
        // printThread.start();
        AsePrint asePrint = new AsePrint();
        int threadNum = 3;
        List<PrintThread> list = asePrint.createThreadObject(threadNum);
        System.out.println(list);

        boolean first = true;
        for (int i = 0; value < 100; i++) {
            if(first) {
                list.get(i).start();
            }
            else {
                synchronized (list.get(i).lock) {
                    list.get(i).lock.notify();
                }
            }
            while(!next) {
                Thread.sleep(1);
            }
            next = false;

            if (i == threadNum - 1) {
                i = -1;
                first = false;
            }
        }

    }
}

/**
 * 线程--
 * **/
class PrintThread extends Thread {
    public Object lock = new Object();

    public void run() {
        while(true) {
            for (int i = 1; i <= 5; i++) {
                synchronized (AsePrint.value) {
                    ++AsePrint.value;
                    System.out.println(Thread.currentThread().getName() + " " + AsePrint.value);
                }
            }
            AsePrint.next = true;
            System.out.println(AsePrint.next);
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }
}