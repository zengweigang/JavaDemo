package homework.disruptor.b;

import com.lmax.disruptor.EventFactory;

/**
 * Created by Administrator on 2015/7/2.
 */
public class TradeFactory implements EventFactory {
    @Override
    public Object newInstance() {
        return new TradeEvent();
    }
}
