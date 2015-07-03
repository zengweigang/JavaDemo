package homework.disruptor.b;

import com.lmax.disruptor.EventHandler;

import java.util.UUID;

/**
 * Created by zengweigang on 2015/7/2.
 */
public class TradeEventHandler1 implements EventHandler<TradeEvent>{
    @Override
    public void onEvent(TradeEvent event, long sequence, boolean endOfBatch) throws Exception {
        event.setUid(UUID.randomUUID().toString());
        System.out.println("编号："+event.toString()+Thread.currentThread().getName());
    }
}
