package homework.guava;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import junit.framework.TestCase;
import org.junit.Test;

import javax.swing.event.ChangeEvent;

/**
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
