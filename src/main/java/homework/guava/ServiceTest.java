package homework.guava;

import com.google.common.util.concurrent.AbstractIdleService;
import com.google.common.util.concurrent.AbstractScheduledService;
import javafx.concurrent.Service;
import javafx.concurrent.Worker;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by zengweigang on 2015/7/7.
 */
public class ServiceTest {
    @Test
    public void idleService(){
        AbstractIdleService service=new AbstractIdleService() {
            @Override
            protected void startUp() throws Exception {
                System.out.println("启动了");
            }

            @Override
            protected void shutDown() throws Exception {
                System.out.println("关闭了");
            }
        };
        service.startAsync().awaitRunning();
        System.out.println("执行中");
        service.stopAsync().awaitTerminated();
    }
    @Test
    public void scheduledService(){
        AbstractScheduledService service=new AbstractScheduledService() {
            @Override
            protected void runOneIteration() throws Exception {
                System.out.println("执行");
            }

            @Override
            protected Scheduler scheduler() {//调度策略
                return Scheduler.newFixedDelaySchedule(0,10, TimeUnit.MILLISECONDS);
            }

            @Override
            protected ScheduledExecutorService executor() {//线程池

                return new ScheduledThreadPoolExecutor(10);
            }
        };
        service.startAsync().awaitRunning();
        service.stopAsync().awaitTerminated();
    }
}
