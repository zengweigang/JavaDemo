package homework.disruptor.b;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.EventTranslatorTwoArg;
import com.lmax.disruptor.RingBuffer;

/**
 * Created by Administrator on 2015/7/3.
 */
public class TradeEventProducer {
    public static final EventTranslatorTwoArg<TradeEvent,String,String> translator=new EventTranslatorTwoArg<TradeEvent, String, String>() {
        @Override
        public void translateTo(TradeEvent event, long sequence, String from, String to) {
            event.setFrom(from);
            event.setTo(to);
        }
    };
    private final RingBuffer<TradeEvent> ringBuffer;

    public TradeEventProducer(RingBuffer<TradeEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }
    public void publishTrade(String from,String to){
        ringBuffer.publishEvent(translator,from,to);
    }
}
