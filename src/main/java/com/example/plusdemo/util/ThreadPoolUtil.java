package com.example.plusdemo.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @Project :
 * @Package Name : com.example.plusdemo.util
 * @Description : 
 * @Author : zhangbin
 * @Create Date : 2019-11-07 14:06
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public class ThreadPoolUtil {


    public static ExecutorService getThreadPool(String name,Integer corePoolSize,Integer maximunPoolSize,Integer queueNum){
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat(name).build();
        return new ThreadPoolExecutor(corePoolSize, maximunPoolSize, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(queueNum), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
    }
}
