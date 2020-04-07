package com.guigu.jvm.reference.weakhashmap;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * @Description : TODO   测试虚引用 map
 * @Author :    yangguang
 * @Date :      2019/11/26
 */
public class WeakHashMapDemo {

    public static void main(String[] args) {

        ThreadLocal threadLocal = new ThreadLocal();
        Thread a;

        Object object = new Object();
        WeakHashMap<Object, Object> weakHashMap = new WeakHashMap<>();
        weakHashMap.put(object,new Object());

        System.out.println(weakHashMap);

        System.out.println("=======================================");

        object = null;
        System.gc();
        //不出意外,打印null
        System.out.println(weakHashMap);

        testStrongRefrence();
    }


    public static  void Weaktwo(){
//        System.out.println("=======================================");
////        Object object = new Object();
////        WeakHashMap<String, Object> weakHashMap = new WeakHashMap<>();
////
////        new HashMap
////
////        weakHashMap.put(object,new Object());
////
////        System.out.println(map);
////        object = null;
////        System.gc();
////        //不出意外,打印null
////        System.out.println(map);
    }


    public static  void testStrongRefrence(){
        System.out.println("=======================================");
        Object object = new Object();
        HashMap<Object, Object> map = new HashMap<>();
        map.put(object,new Object());

        System.out.println(map);
        object = null;
        System.gc();
        //不出意外,打印null
        System.out.println(map);
    }
}
