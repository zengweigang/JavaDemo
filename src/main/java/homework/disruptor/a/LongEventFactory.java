package homework.disruptor.a;

import com.lmax.disruptor.EventFactory;

/**
 * Created by Administrator on 2015/7/2.
 */
class LongEventFactory implements EventFactory {
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
