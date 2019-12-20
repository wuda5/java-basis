package com.guigu.juc.collections.list;

import java.util.ArrayList;
import java.util.UUID;
import java.util.Vector;

/*
*
ArrayList在迭代的时候如果同时对其进行修改就会
抛出java.util.ConcurrentModificationException异常
并发修改异常
* */
public class NotSafeDemo {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        /*
        * Vetor.add是直接方法上用synchronized，
        * 这会导致，其他线程共同访问时，不能操作它的方法---锁8锁特性
        * 导致不能好的读写分离
        * ，而CopyOnWriteArrayList，的add的内部是用的 ReentrantLock，可以做到读写分离
        * */
        new Vector<String>().add("");

        //1.直接vector
//       2. List<String> list2=  Collections.synchronizedList(new ArrayList<>());
        //3.
        for ( int i = 0; i <300 ; i++) {
//            int x=i;
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                //---上面插入，下面马上读取----
                System.out.println( list);
            }, String.valueOf(i))
                    .start();
        }

//        看ArrayList的源码
//        public boolean add(E e) {
//            ensureCapacityInternal(size + 1);  // Increments modCount!!
//            elementData[size++] = e;
//            return true;
//        }
//        没有synchronized线程不安全

    }
}
