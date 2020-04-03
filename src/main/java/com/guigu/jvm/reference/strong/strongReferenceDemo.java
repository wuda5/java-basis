package com.guigu.jvm.reference.strong;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class strongReferenceDemo {

    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 =o1;

        o1 =null;
//        System.gc();
        System.out.println(o1);
        System.out.println(o2);

        System.out.println("==================");

        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(8);
        List list2 = list1; // list2还是指向对象的地址的，即list2 还再引用着 堆里的对象
        list1 = null;
        System.gc();

        System.out.println(list1);
        System.out.println(list2);
    }
}
