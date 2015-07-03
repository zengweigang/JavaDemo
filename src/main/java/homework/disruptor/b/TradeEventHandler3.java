package homework.disruptor.b;

import com.lmax.disruptor.EventHandler;

/**
 * Created by zengweigang on 2015/7/2.
 */
public class TradeEventHandler3 implements EventHandler<TradeEvent>{
    @Override
    public void onEvent(TradeEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.print("录入数据库："+event.toString());
    }
}
