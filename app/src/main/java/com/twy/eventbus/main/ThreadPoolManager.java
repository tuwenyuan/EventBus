package com.twy.eventbus.main;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolManager {

    private static  ThreadPoolManager instance=new ThreadPoolManager();


    public ThreadPoolExecutor threadPoolExecutor;
    public static ThreadPoolManager getInstance() {
        return instance;
    }
    private ThreadPoolManager()
    {
        threadPoolExecutor=new ThreadPoolExecutor(4, 10, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(4));
    }
}
