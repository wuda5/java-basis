package com.guigu.jvm.reference.softreference;

import java.lang.ref.SoftReference;
import java.util.ArrayList;

/**
 * @Description : TODO      2.    测试软引用
 * @Author :    yangguang
 * @Date :      2019/11/26
 */
public class SoftReferenceDemo {

    /**
     * -Xms5m -Xmx5m -XX:+PrintGCDetails
     * 故意产生大对象，并配置小内存，制造 oom--》 观察 软引用 的回收情况（不用手动gc）
     * 当内存足够或正常时不会回收软引用
     *
     */
    public static void main(String[] args) {

        softRef_Memorry_Enough();
        softRef_Memorry_Less();

    }

    public static void softRef_Memorry_Less(){
        Object object = new Object();
        SoftReference<Object> softReference = new SoftReference<>(object);
        object = null;
//        System.gc();
        try
        {
            //创建一个接近堆内存大小的byte数组,这里会抛出 Throwable , 捕获,然后再 catch 里打印软引用是否为空
            byte[] bytes = new byte[30 * 1024 * 1024]; // 自动触发垃圾回收
        }
        catch(Throwable throwable)
        {
            throwable.printStackTrace();
            boolean add = new ArrayList<Integer>().add(89);
            System.out.println(add +" add 是否成功");
            System.out.println(" 内存溢出++++ ");
            //不出意外,打印null
            System.out.println(softReference.get());
        }


    }

/**1. 内存足够的时候 （测试时候不改变参数，就系统默认测试）
 *  --> 相当是强应勇；
 * **/
    private static void softRef_Memorry_Enough() {

        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1); // 就看做一个 赋值！！

        /** 1. 打印可以看到 o1 和 softReference.get() 得到对象 指向的是同一个堆对象的内存地址！！ **/
        System.out.println(o1);
        System.out.println(softReference.get());

        o1=null;
        System.gc();

        System.out.println(o1);
        System.out.println(softReference.get()); /** gc后，观察整个软引用是否还有值, 内存足够时候就会还是有纸值的（即不会被回收）！！ **/

        System.out.println("++++ softRef_Memorry_Enough ++++ over +++++++++++++++++++++++++");
    }
}
