package homework.concurrent;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 Executor 接口定义了最基本的 execute 方法，用于接收用户提交任务。 ExecutorService 定义了线程池终止和创建及提交 futureTask 任务支持的方法。

 AbstractExecutorService 是抽象类，主要实现了 ExecutorService 和 futureTask 相关的一些任务创建和提交的方法。

 ThreadPoolExecutor 是最核心的一个类，是线程池的内部实现。线程池的功能都在这里实现了，平时用的最多的基本就是这个了。其源码很精练，远没当时想象的多。

 ScheduledThreadPoolExecutor 在 ThreadPoolExecutor 的基础上提供了支持定时调度的功能。线程任务可以在一定延时时间后才被触发执行。
 */
public class ExecutorDemo {
    @Test
    public void executor(){
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();//支持keepalivetime时间是60秒（线程空闲存活时间），且corePoolSize为0，maximumPoolSize无穷大的线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);//不支持keepalivetime，且corePoolSize和maximumPoolSize相等的线程池 固定大小了 fixed
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);//实现定时调度的线程池
    }
}
