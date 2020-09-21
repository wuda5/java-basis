package map;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
//https://blog.csdn.net/weixin_30649641/article/details/95002030?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.channel_param&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.channel_param
public class hashmapDead extends Thread {


    static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(2);
    static AtomicInteger at = new AtomicInteger(0);

    @Override
    public void run() {
        while (at.get() < 100000) {
            map.put(at.get(), at.get());
            at.incrementAndGet();
            System.out.println(at.get());
        }
    }

    public static void main(String[] args) {
        hashmapDead t0 = new hashmapDead();
        hashmapDead t1 = new hashmapDead();
        hashmapDead t2 = new hashmapDead();
        hashmapDead t3 = new hashmapDead();
        hashmapDead t4 = new hashmapDead();
        t0.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }


}