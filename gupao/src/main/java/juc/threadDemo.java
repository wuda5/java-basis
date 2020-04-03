package juc;

public class threadDemo extends Thread {

    @Override
    public void run(){
        super.run();
        System.out.println("haha");
    }

    public static void main(String[] args) {
//        new threadDemo().run();
//        new threadDemo().start();

        new Thread(new threadDemo()).start();
    }
}
