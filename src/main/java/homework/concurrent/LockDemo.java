package homework.concurrent;

import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2015/7/7.
 */
public class LockDemo {
    @Test
    public void lock(){
        Lock lock=new ReentrantLock();
        lock.lock();
        try{
            //TODO
        }finally {
            lock.unlock();
        }
    }
}
