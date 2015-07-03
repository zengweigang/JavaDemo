package homework.disruptor.b;

import com.lmax.disruptor.EventHandler;

/**
 * Created by zengweigang on 2015/7/2.
 */
public class TradeEventHandler4 implements EventHandler<TradeEvent>{
    @Override
    public void onEvent(TradeEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("我负责发布交易情况：" + event.toString()+Thread.currentThread().getName());
    }
}
