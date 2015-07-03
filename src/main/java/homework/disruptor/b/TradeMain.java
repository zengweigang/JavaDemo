package homework.disruptor.b;

import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by zengweigang on 2015/7/2.
 */
public class TradeMain {
    public static void main(String[] args) {
        Executor executor = Executors.newCachedThreadPool();

        TradeFactory tradeFactory = new TradeFactory();
        TradeEventHandler1 handler1 = new TradeEventHandler1();
        TradeEventHandler2 handler2 = new TradeEventHandler2();
        TradeEventHandler3 handler3 = new TradeEventHandler3();
        TradeEventHandler4 handler4 = new TradeEventHandler4();
        Disruptor<TradeEvent> disruptor = new Disruptor<TradeEvent>(tradeFactory, 1 << 10, executor);
        disruptor.handleEventsWith(handler1).then(handler2,handler3).then(handler4);
        disruptor.start();
        TradeEventProducer tradeEventProducer = new TradeEventProducer(disruptor.getRingBuffer());

        for (long l = 0; l<2; l++) {
            tradeEventProducer.publishTrade("zengweigang" + l, "zhangsan" + l);
        }
        disruptor.shutdown();
    }
}
