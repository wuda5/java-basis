package com.guigu.juc.lock.readWriteLock;

import java.util.HashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**读写锁代码验证----> 对应视频-->xxxxxx
 * @Description : TODO      读写锁
 * @Author :    yangguang
 * @Date :      2019/11/21
 */
public class ReadWriteLockDemo
{
    /**
     *      * 读-读 : 无锁(能共存)
     *      * 读-写　:　锁（不能共存）
     *      * 写－写　:　锁（不能共存）
     *
     *      写操作： 原子+ 独占 (注意观察测试结果是否满足！有否被打断)，--》即看 开始写入 和  写入完成 是否连续，中间是否有其他操作！
     *      整个过程必须是一个完整 的统一体，中间不许被分割，被打断
     */
    //模拟缓存被读和被写 -- 缓存资源类

    static class Cache
    {
        private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        private volatile HashMap<String, Object> cache = new HashMap<>();
//        private HashMap<String, Object> cache = new HashMap<>();

        public void put(String key , Object val)
        {
            readWriteLock.writeLock().lock(); /** 1. 加写锁 */
            try
            {
                System.out.println(Thread.currentThread().getName()+" 开始写入："+ val);
                cache.put(key,val);
                System.out.println(Thread.currentThread().getName()+" 写入完成");
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                readWriteLock.writeLock().unlock();
            }
        }

        /** 2. 读共享 -- 》 加 读锁 */
        public void get(String key)
        {
            readWriteLock.readLock().lock();
            try
            {
                System.out.println(Thread.currentThread().getName() + " 开始读取");
                Object obj = cache.get(key);
                System.out.println(Thread.currentThread().getName() + " 读取完成 : " + obj);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
               readWriteLock.readLock().unlock();
            }
        }
    }



    public static void main(String[] args)
    {
        Cache cache = new Cache();
        /**1. 模拟 5 个线程写操作 **/
        for (int i = 0 ; i < 5; ++i)
        {
            final int tempI = i;
            new Thread(()->{
               cache.put(String.valueOf(tempI),tempI);
            }).start();
        }
        /**1. 模拟 5 个线程 读 操作 **/
        for (int i = 0 ; i < 5; ++i)
        {
            final int tempI = i;
            new Thread(()->{
                cache.get(String.valueOf(tempI));
            }).start();
        }
    }
}
