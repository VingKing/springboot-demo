package com.example.plusdemo.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author VingKing
 */
public class ThreadPoolUtil {


    public static ExecutorService getThreadPool(String name,Integer corePoolSize,Integer maximunPoolSize,Integer queueNum){
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat(name).build();
        return new ThreadPoolExecutor(corePoolSize, maximunPoolSize, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(queueNum), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
    }
}
