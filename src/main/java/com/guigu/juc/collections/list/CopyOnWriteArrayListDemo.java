package com.guigu.juc.collections.list;

import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;


//正在writer时用----juc包下的java.util.concurrent.CopyOnWriteArrayList;
/*
 * Vetor.add是直接方法上用synchronized，
 * 这会导致，其他线程共同访问时，不能操作它的方法---锁8锁特性
 * 导致不能好的读写分离
 * ，而CopyOnWriteArrayList，的add的内部是用的 ReentrantLock，可以做到读写分离
 *
 * add的底层都是 Arrays.copyof(elements, len+1)
 * CopyOnWriteArrayList采用“写入时复制”策略，对容器的写操作将导致的容器中基本数组的复制，性能开销较大。
 * 所以在有写操作的情况下，CopyOnWriteArrayList性能不佳，而且如果容器容量较大的话容易造成溢出。
 *
 * */
public class CopyOnWriteArrayListDemo {

    public static void main(String[] args) {

        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.size();

        for (int i = 0; i < 300; i++) {
            final String  x=i+"";
            new Thread(() -> {

                list.add(x);
//                list.add(UUID.randomUUID().toString().substring(0, 8));
                //---上面插入，下面马上读取----
                System.out.println(list);
            }, String.valueOf(i))
                    .start();
        }

    }
}
