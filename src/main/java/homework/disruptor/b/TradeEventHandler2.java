package homework.disruptor.b;

import com.lmax.disruptor.EventHandler;

import java.util.UUID;

/**
 * Created by zengweigang on 2015/7/2.
 */
public class TradeEventHandler2 implements EventHandler<TradeEvent>{
    @Override
    public void onEvent(TradeEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.print("记录日志："+event.toString());
    }
}
