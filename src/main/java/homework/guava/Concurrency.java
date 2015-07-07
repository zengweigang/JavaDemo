package homework.guava;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2015/7/7.
 */
public class Concurrency {
    @Test
    public  void future(){
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        ListenableFuture<Long> future = executorService.submit(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return System.currentTimeMillis();
            }
        });
        Futures.addCallback(future,new FutureCallback<Object>() {
            @Override
            public void onSuccess(Object result) {
                System.out.println("success"+result);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("failure"+t.getMessage());
            }
        });
    }
    @Test
    public void rateLimiter(){
        RateLimiter limiter=RateLimiter.create(30);
        limiter.acquire();//每一秒最多 调用30次 acquire
    }
    @Test
    public void monitor() throws InterruptedException {
        final ArrayList<String> list= Lists.newArrayList();
        Monitor monitor = new Monitor();
        Monitor.Guard condition=new Monitor.Guard(monitor) {
            @Override
            public boolean isSatisfied() {
                System.out.println("size:"+list.size());
                return list.size()<20;
            }
        };
        try{
            for (int i = 0; i <30 ; i++) {
                System.out.println("data:"+i);
                if(monitor.enterIf(condition)){
                    list.add(i+"");
                }else {
                    list.remove(0);
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            monitor.leave();
        }
    }
}
