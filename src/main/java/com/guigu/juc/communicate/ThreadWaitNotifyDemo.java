package com.guigu.juc.communicate;

class AirConditioner {
    private int number = 0;

    //加
    public    void increment() throws Exception {
        int x=0;
        //1.判断--是0才会执行干活加数操作--否则不为0--让出cpu等待--
//        if (number >0 )
        while (number !=0 )//除了为0都会执行
//        if (number !=0 )
        {
            //---这个位置会卡克？？？枷锁的这对象，应该其他线程在此对象没自动释放锁--前不会执行啊？？？
//            Thread.sleep(10000);
            System.out.println("--------线程【"+ Thread.currentThread().getName()+"--------+睡觉1s, 此时number is ["+number);
            //--执行这个wait方法肯定是相当于会自动释放锁synchronized，其他线程才能访问对象的其他同步方法！
            //这也是wait 和 sleep的区别
            this.wait();
//             1.假如此时 number = 1,线程a进入此方法，wait后释放锁后，
//             2.此时万一还是被加操作的线程C 得到锁的也进行的曾操作，
//             3.那么那边 当C 也进入此法内后也wait释放锁后，所有线程竞争，
//             4.万一恰好线程A又拿到cpu资源话（被唤醒）
//             5.那么，如果此方法如果是前面是 if 判断的话，那么就是直接 的 线程A和C会 走到下面+1 操作，就可能多加数字了
//             6.如果是 while，那么就算 4. 中线程A又是拿到资源被唤醒，但他会继续执行while的判断数字number是否是已经被加过了，
//               如果还没被加过（即此时线程 C 也还没有执行到 +1 的操作，准确说 C 也还在 while 循环判断条件 内）--》此时就会继续执行wait()等待下一次被唤醒
//               即不会出现多+数字的情况了。

            x++;
        }
        if (x>2)
        {
            System.out.println("*********** 说明有虚假唤醒 *** 唤醒次数【"+x+" 】 ************************ ["+ Thread.currentThread().getName());
        }
        //2.干活
        number++;
        System.out.println("加--线程-"+ Thread.currentThread().getName() +"--number ["+number);

        //3.通知
        this.notifyAll();
    }

// 减法
    public synchronized  void decrement() throws Exception {
        //1.判断
        while (number == 0 )
//        if (number == 0 )
        {
            this.wait();
        }
        //2.干活
        number--;
        System.out.println("减--线程-"+ Thread.currentThread().getName() +"--number ["+number);

        //3.通知
        this.notifyAll();
    }
}
//--资源类

/*
 * 实现两个线程操作一个初始值为0的变量；
 * thread1 对变量 +1，线程2 -1；
 * 实现交替，来10论，
 *
 * 1.高内聚低内聚前提下，线程操作资源类
 * 2. 判断/干活/通知
 * 3. 多线程交换中，要谨记
 *
 * */
public class ThreadWaitNotifyDemo {

    public static void main(String[] args) throws Exception {
        AirConditioner air = new AirConditioner();


//        for (int i = 1; i <100 ; i++) {
//                new Thread(()->{
//                    try {
//                         air.increment();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                },"加线程[+"+i+"]").start();
//
//            new Thread(()->{
//                try {
//                    air.decrement();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            },"+++ 减++线程[-"+i+"]").start();
//        }

        Thread.sleep(1000);

//        for (int i = 1; i <100 ; i++) {
//            new Thread(()->{
//                try {
//                    air.decrement();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            },"+++ 减++线程[-"+i+"]").start();
//        }

//

        new Thread(()->{
            for (int i = 1; i <100 ; i++) {
                try {
                    air.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"+A").start();

        new Thread(()->{
            for (int i = 1; i <100 ; i++) {
                try {
                    air.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"-B").start();
        new Thread(()->{
            for (int i = 1; i <100 ; i++) {
                try {
                    air.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"+C").start();

        new Thread(()->{
            for (int i = 1; i <100 ; i++) {
                try {
                    air.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"-D").start();
    }
}
