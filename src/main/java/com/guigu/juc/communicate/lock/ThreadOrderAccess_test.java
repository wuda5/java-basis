package com.guigu.juc.communicate.lock;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 多线程之间按顺序调用，每个线程给定一个编号，实现xxx
 *  博客链接： https://editor.csdn.net/md/?articleId=104526031
 * 说，明：拿到一道面试题如下：
 *
 * 构造5个线程，从1-100每个线程依次输出5个数字，
 * 例如线程1输出1-5，线程2输出6-10，...，线程5输出21-25，
 * 然后线程1输出26-30，依次类推到100截止。
 *
 * 自己尝试多次，优化，简单重构了下，`并实现了动态可扩展`，我觉得还不错，分享下。
 *
 * 代码如下（直接拿走，main 方法运行可见结果！）：
 */
class ShareResource_test{

    //CAS原子类测试--操作数
    private  AtomicInteger count = new AtomicInteger(0);

    /** 每个线程给定一个编号--
     * 第一次来默认设置为 1 线程----
     * 线程(1)完了后 通知线程(2),
     * 线程(2)完了后通知线程(3)，
     * 线程(3)完了后通知线程(1) ....4.5....
     * */
    private int currentThreadNumber = 1;//默认第一个线程先能指向
    private int threadNum ;
    private int MAX_VALUE ;

    private Lock lock = new ReentrantLock();/** 锁 ,LOCK.notifyAll()*/

    private Map<Integer,Condition> conditionMap = new HashMap<>();

    public ShareResource_test(int threadNum,int MAX_VALUE) {
        this.threadNum = threadNum;
        this.MAX_VALUE = MAX_VALUE;

        for (int i = 1; i <= threadNum ; i++) {
            conditionMap.put(i,lock.newCondition());
        }
    }


    public AtomicInteger getCount() {
        return count;
    }

    //线程A执行
    public void printx(int x) {

        lock.lock();
        try {
            /**1. 判断,是线程 x 才会不进循环等待走下面 */
            while (currentThreadNumber != x) {
                // 就要停止一直等--
                Condition c = searcherCondiionByThreadNum(x);
                c.await();/** 等待并释放当前xxx*/
            }

            /** 2. 干活--》执行累加数 操作**/
            for (int i = 1; i <= 5; i++) {
                if(count.get()>= MAX_VALUE){
                    break;//或者 contione;  不可用 return;/** 注意：不能直接return,会造成死锁--》因为下面的threadNumber 线程必须切换通知唤醒*/
                }
                count.incrementAndGet();/** 执行加一操作 **/
                System.out.println(Thread.currentThread().getName() + "--currentValue is:  " + count  );
            }


            /** 3. condition.signal(); 通知下一个xx--按指定通知线程--标志位2代表精确唤醒2线程*/
            Map<Integer, Condition> nextSingleThread = judgNextSingalThreadByPrevious(x);
            /**  这一步很重要！！，改变当前xxx **/
            currentThreadNumber = getKeyOrNull(nextSingleThread);
            System.out.println("+++ single other thread do +++++");
            getFirstOrNull(nextSingleThread).signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }



    /**
     * 获取map中第一个key值
     *
     * @param map 数据源
     * @return
     */
    private static  <k ,v> k   getKeyOrNull(Map<k, v> map) {
        k obj = null;
        for (Map.Entry<k, v> entry : map.entrySet()) {
            obj = entry.getKey();
            if (obj != null) {
                break;
            }
        }
        return  obj;
    }


    /**
     * 获取map中第一个数据值
     *
     * @param map 数据源
     * @return
     */
    private static <k ,v> v getFirstOrNull(Map<k, v> map) {
        v obj = null;
        for (Map.Entry<k, v> entry : map.entrySet()) {
            obj = entry.getValue();
            if (obj != null) {
                break;
            }
        }
        return  obj;
    }

    /** 可以考虑个对象封装xxx */
    private Condition searcherCondiionByThreadNum(int x) {
        return conditionMap.get(x);
    }

    /**主要是这个通知下一个条件可以用的线程 逻辑不一样--> 其实应该也可以写为动态的！！-- */
    private Map<Integer,Condition> judgNextSingalThreadByPrevious(int x) {

        HashMap<Integer, Condition> result = new HashMap<>();
        int nextConditionNum;
        if (x < this.threadNum)
        {
            nextConditionNum = x+1; /** 指向下一个线程 */
        }
        else
        {
            nextConditionNum = 1;/**最后一个则是 指向第一个线程 */
        }
        Condition nextCondition = this.conditionMap.get(nextConditionNum);
        result.put(nextConditionNum,nextCondition);

        return result;
    }

}

/**--- main ----
 * @Description: 多线程之间按顺序调用，实现A->B->C
 * x 个线程启动，要求如下：
 * <p>
 * AA打印12345，BB打印678910，CC打印11.12.13.14.15
 * 接着
 * AA打印，BB打印，CC打印
 * ......来x轮 --》直到打印到 100 结束！
 */
public class ThreadOrderAccess_test {

    /**最大累加计数值*/
    public static final int MAX_VALUE = 100;
    /** 任意可变化不同线程数良*/
    private static final int threadNum = 3;

    public static void main(String[] args) {

        ShareResource_test sr = new ShareResource_test(threadNum, MAX_VALUE);

        for (int i = 1; i <= threadNum ; i++) {
            final   int a =i;
            new Thread(() -> {

                while (sr.getCount().get() < MAX_VALUE) {

                    sr.printx(a+0);
                }
            }, i+"").start();

        }

    }
}













