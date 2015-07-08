package homework.guava;

import com.google.common.collect.Range;
import com.google.common.primitives.Ints;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by Administrator on 2015/7/8.
 */
public class RangeTest{
    @Test
    public void test(){
        Range<Integer> range = Range.open(1, 18);//(1,18)
        System.out.println(range.contains(1)+" "+range.contains(18));
        range = Range.closed(1, 18);//[1,18]
        System.out.println(range.contains(1)+" "+range.contains(18));
        range=Range.closedOpen(1,18);
        System.out.println(range.contains(1)+" "+range.contains(18));
        //关系运算
        Range.closedOpen(4, 4).isEmpty(); // returns true
        Range.openClosed(4, 4).isEmpty(); // returns true
        Range.closed(4, 4).isEmpty(); // returns false
        Range.open(4, 4).isEmpty(); // Range.open throws IllegalArgumentException
        Range.closed(3, 10).lowerEndpoint(); // returns 3
        Range.open(3, 10).lowerEndpoint(); // returns 3
        Range.closed(3, 10).lowerBoundType(); // returns CLOSED
        Range.open(3, 10).upperBoundType(); // returns OPEN
        //相连[isConnected]
        Range.closed(3, 5).isConnected(Range.open(5, 10)); // returns true
        Range.closed(0, 9).isConnected(Range.closed(3, 4)); // returns true
        Range.closed(0, 5).isConnected(Range.closed(3, 9)); // returns true
        Range.open(3, 5).isConnected(Range.open(5, 10)); // returns false
        Range.closed(1, 5).isConnected(Range.closed(6, 10)); // returns false Range.isConnected(Range)判断区间是否是相连的。
        //交集[intersection]
        Range.closed(3, 5).intersection(Range.open(5, 10)); // returns (5, 5]
        Range.closed(0, 9).intersection(Range.closed(3, 4)); // returns [3, 4]
        Range.closed(0, 5).intersection(Range.closed(3, 9)); // returns [3, 5]
        Range.open(3, 5).intersection(Range.open(5, 10)); // throws IllegalArgumentException
        Range.closed(1, 5).intersection(Range.closed(6, 10)); // throws IllegalArgumentException

        //区间运算
        Range.closed(1, 3).contains(2);//return true
        Range.closed(1, 3).contains(4);//return false
        Range.lessThan(5).contains(5); //return false
        Range.closed(1, 4).containsAll(Ints.asList(1, 2, 3)); //return true

        //查询运算
        Range.closedOpen(4, 4).isEmpty(); // returns true
        Range.openClosed(4, 4).isEmpty(); // returns true
        Range.closed(4, 4).isEmpty(); // returns false
        Range.open(4, 4).isEmpty(); // Range.open throws IllegalArgumentException
        Range.closed(3, 10).lowerEndpoint(); // returns 3
        Range.open(3, 10).lowerEndpoint(); // returns 3
        Range.closed(3, 10).lowerBoundType(); // returns CLOSED
        Range.open(3, 10).upperBoundType(); // returns OPEN


    }
    @Test
    public void demo(){
        Range.open(3, 5).intersection(Range.open(5, 10)); // throws IllegalArgumentException
    }
}
