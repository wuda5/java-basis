package com.gupaoedu.vip.requestProcessor;

/**
 * Hello world!
 *
 * 有用到生产则消费则模式
 * 1.生产 --》往队列加request
 * 2.消费--》从队列中取 request
 *
 * ? 怎样去改造自己的程序实现异步处理，--》异步消息队列去解决l ?
 *
 */
public class App{

    static IRequestProcessor requestProcessor;

    /**
     *    构建--异步责任链 requestProcessor
     *    pre处理--> 保存 saveprocessor-->打印printProcessor --
     * **/
    public void setUp(){
        PrintProcessor printProcessor=new PrintProcessor();//他后面没有责任链了，所以不需要参数
        printProcessor.start();
        SaveProcessor saveProcessor=new SaveProcessor(printProcessor);
        saveProcessor.start();

        requestProcessor=new PrevProcessor(saveProcessor);
        ((PrevProcessor) requestProcessor).start();
    }



    public static void main(String[] args) {
        App app=new App();
        app.setUp();

        Request request=new Request();
        request.setName("Mic");

        requestProcessor.process(request);
    }

}
