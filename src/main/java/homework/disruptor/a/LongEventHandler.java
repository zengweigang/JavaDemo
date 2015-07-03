package homework.disruptor.a;

import com.lmax.disruptor.EventHandler;

/**
 * Created by Administrator on 2015/7/2.
 */
public class LongEventHandler<LongEvent> implements EventHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("Event:"+event);
    }
}
