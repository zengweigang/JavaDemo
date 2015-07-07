package homework.guava;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import junit.framework.TestCase;
import org.junit.Test;

import javax.swing.event.ChangeEvent;

/**
 * 一、场景假设

 假设有博客系统中需要实现如下功能：

 系统中用户发布文章，修改文章，删除文章时，需要一些相关的操作需要执行。

 发布文章后，给好友发送邮件通知，给用户加积分，对文章做全文索引。

 修改文章后，给好友发送邮件修改通知，给用户加积分，对文章重新做全文索引。

 删除文章后，给好友发送邮件修改通知，给用户减少积分，对文章重新做全文索引。

 二、相关的概念解析

 分析如上场景，可以采用事件分发和监听机制来实现。

 事件分发和监听有如下几个概念：

 事件源：触发事件的对象 如上场景中我们把实体对象作为事件源，发布的文章就是事件源

 事件： 对事件源的操作产生事件，例如 发表文章 修改文章，删除文章这些操作就会触发相关的 文章被发表事件，文章被删除事件，文章被修改事件

 事件监听器：对事件源各种事件触发执行行为的抽象，包括接口和若干实现类。

 比如： 接口需要定义事件源相关事件触发时需要实现的操作接口。

 事件分发器：事件分发器主要处理事件的分发和事件监听器的管理，注册和删除事件监听器等。
 * Created by zengweigang on 2015/7/7.
 */
public class EventBusTest extends TestCase {


    public  void test(){
       Object obj= new Object() {

           @Subscribe
           public void lister(Integer integer) {
               System.out.printf("%s from int%n", integer);
           }

           @Subscribe
           public void lister(Number integer) {
               System.out.printf("%s from Number%n", integer);
           }

           @Subscribe
           public void lister(Long integer) {
               System.out.printf("%s from long%n", integer);
           }
           @Subscribe
           public void lister(DeadEvent event) {
               System.out.printf("%s=%s from dead events%n", event.getSource().getClass(), event.getEvent());
           }
       };//继承Object
        final EventBus eventBus = new EventBus();
        eventBus.register(obj);
        eventBus.post(2);
        eventBus.post(1L);
        eventBus.post("s");
    }

}
