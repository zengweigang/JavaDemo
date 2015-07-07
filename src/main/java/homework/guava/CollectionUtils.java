package homework.guava;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.primitives.Ints;
import org.junit.Test;

/**
 * 好多实用的工具  不写例子了 呵呵哒
 * Created by zengweigang on 2015/7/3.
 */
public class CollectionUtils {
    @Test
    public void factory(){
        Lists.newArrayList();
        Maps.newHashMap();
        Lists.newArrayListWithCapacity(1<<20);
    }
    @Test
    public void iterables(){
        Iterable<Integer> integers = Iterables.concat(Ints.asList(1, 2, 3), Ints.asList(4, 5, 6));
        Integer last = Iterables.getLast(Ints.asList(1, 2));
        System.out.println(last);
    }
}
