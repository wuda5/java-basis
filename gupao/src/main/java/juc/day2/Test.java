package juc.day2;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */
public class Test implements Runnable{

    private static volatile boolean isRunning=false; //中断标识

    @Override
    public void run() {
        while(!isRunning){ //!true
            //TODO
            System.out.println("hah");
        }

    }

    public static void main(String[] args) {
        System.out.println("test");

        Thread thread=new Thread();
//        Test test = new Test();



        thread.interrupt();//isRunning=true;
    }

}
