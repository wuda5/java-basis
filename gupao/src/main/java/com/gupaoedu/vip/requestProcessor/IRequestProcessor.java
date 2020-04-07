package com.gupaoedu.vip.requestProcessor;

/**
* 责任链 异步调用--
 */
public interface IRequestProcessor {


    void process(Request request);

}
