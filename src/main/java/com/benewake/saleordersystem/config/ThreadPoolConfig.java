package com.benewake.saleordersystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

/**
 * @author Lcs
 */
@Configuration
@EnableAsync
public class ThreadPoolConfig {

    // 核心线程数
    private static final int CORE_POLL_SIZE = 5;
    // 线程池维护的最大线程数 只有当队列满时才会申请超过核心线程数的线程
    private static final int MAXI_NUM_POLL_SIZE = 10;
    // 允许的空闲时间
    private static final long KEEPALIVE_TIME = 3;
    // 队列大小
    private static final int QUEUE_SIZE = 10;

    public static final String ASYN_EXECUTOR_NAME = "asynExecutor";
    @Bean(ASYN_EXECUTOR_NAME)
    public ThreadPoolTaskExecutor myThreadPoolTask(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(CORE_POLL_SIZE);
        taskExecutor.setMaxPoolSize(MAXI_NUM_POLL_SIZE);
        taskExecutor.setQueueCapacity(QUEUE_SIZE);
        taskExecutor.setKeepAliveSeconds((int) KEEPALIVE_TIME);
        taskExecutor.setThreadNamePrefix("AsyncExecutor-");
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.setAwaitTerminationSeconds(60);
        taskExecutor.initialize();
        return taskExecutor;
//        return new ThreadPoolExecutor(
//                CORE_POLL_SIZE,
//                MAXI_NUM_POLL_SIZE,
//                KEEPALIVE_TIME,
//                TimeUnit.SECONDS,
//                new LinkedBlockingQueue<>(QUEUE_SIZE),
//                Executors.defaultThreadFactory(),
//                /**
//                 * 当线程池的任务缓存队列已满并且线程池中的线程数目达到maximumPoolSize，如果还有任务到来就会采取任务拒绝策略
//                 * 通常有以下四种策略：
//                 * ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
//                 * ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
//                 * ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
//                 * ThreadPoolExecutor.CallerRunsPolicy：重试添加当前的任务，自动重复调用 execute() 方法，直到成功
//                 */
//                new ThreadPoolExecutor.AbortPolicy()
//        );
    }
}
