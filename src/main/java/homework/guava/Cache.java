package homework.guava;

import com.google.common.cache.*;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by zengweigang on 2015/7/3.
 */
public class Cache {
    @Test
    public void demo() throws ExecutionException, InterruptedException {
        //缓存接口这里是LoadingCache，LoadingCache在缓存项不存在时可以自动加载缓存
        LoadingCache<Integer,Student> studentCache
                //CacheBuilder的构造函数是私有的，只能通过其静态方法newBuilder()来获得CacheBuilder的实例
                = CacheBuilder.newBuilder()
                //设置并发级别为8，并发级别是指可以同时写缓存的线程数
                .concurrencyLevel(8)
                        //设置写缓存后20秒钟过期
                .expireAfterWrite(20, TimeUnit.SECONDS)
                        //设置缓存容器的初始容量为10
                .initialCapacity(10)
                        //设置缓存最大容量为100，超过100之后就会按照LRU最近虽少使用算法来移除缓存项
                .maximumSize(100)
                        //设置要统计缓存的命中率
                .recordStats()
                        //设置缓存的移除通知
                .removalListener(new RemovalListener<Object, Object>() {
                    @Override
                    public void onRemoval(RemovalNotification<Object, Object> notification) {
                        System.out.println(notification.getKey() + " was removed, cause is " + notification.getCause());
                    }
                })
                        //build方法中可以指定CacheLoader，在缓存不存在时通过CacheLoader的实现自动加载缓存
                .build(
                        new CacheLoader<Integer, Student>() {
                            @Override
                            public Student load(Integer key) throws Exception {
                                System.out.println("load student " + key);
                                Student student = new Student();
                                student.setId(key);
                                student.setName("name " + key);
                                return student;
                            }
                        }
                );

        for (int i=0;i<8;i++) {
            //从缓存中得到数据，由于我们没有设置过缓存，所以需要通过CacheLoader加载缓存数据
            Student student = studentCache.get(i);
            System.out.println(student.getName());
            //休眠1秒
            TimeUnit.SECONDS.sleep(1);
        }

        for (int i=0;i<9;i++) {
            //从缓存中得到数据，由于我们没有设置过缓存，所以需要通过CacheLoader加载缓存数据
            Student student = studentCache.get(i);
            System.out.println(student.getName());
            //休眠1秒
            TimeUnit.SECONDS.sleep(1);
        }


        System.out.println("cache stats:");
        //最后打印缓存的命中率等 情况
        System.out.println(studentCache.stats().toString());//CacheStats{hitCount=8, missCount=9, loadSuccessCount=9, loadExceptionCount=0, totalLoadTime=3987668, evictionCount=0}
    }


    public class Student{
        private Integer id;
        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
