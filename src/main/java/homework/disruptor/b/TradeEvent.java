package homework.disruptor.b;

/**
 * 一个交易事件
 * Created by zengweigang on 2015/7/2.
 */
public class TradeEvent {
    private String uid;//交易ID
    private String from;//
    private String to;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "uid;"+getUid()+" from:"+from+" to:"+to;
    }
}
