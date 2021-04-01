package com.bestwu.jdbc.util;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <br>
 *
 * @author Best Wu
 * @date 2021/4/1 22:18 <br>
 */
public class ExecutorUtil {

    public static ThreadPoolExecutor newCachedExecutor(int corePoolSize, int maxPoolSize) {
        return new ThreadPoolExecutor(corePoolSize, maxPoolSize, 2, TimeUnit.SECONDS, new SynchronousQueue<>(),
                new ThreadFactory() {
                    private final AtomicInteger poolNumber = new AtomicInteger(1);
                    private final ThreadGroup group = new ThreadGroup("CachedThreadPool");
                    private final AtomicInteger threadNumber = new AtomicInteger(1);
                    private final String namePrefix = "cached-pool-";

                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(group, r,
                                namePrefix + poolNumber.getAndIncrement() + "-" + threadNumber.getAndIncrement(), 0);
                        if (t.isDaemon()) {
                            t.setDaemon(false);
                        }
                        if (t.getPriority() != Thread.NORM_PRIORITY) {
                            t.setPriority(Thread.NORM_PRIORITY);
                        }
                        return t;
                    }
                },
                new RejectedExecutionHandler(){
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        throw new RejectedExecutionException("Too many task");
                    }
                });

    }
}
