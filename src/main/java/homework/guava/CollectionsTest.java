package homework.guava;

import com.google.common.collect.*;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by zengweigang on 2015/7/3.
 */
public class CollectionsTest {
    /**
     *不可变对象有很多优点，包括：
     当对象被不可信的库调用时，不可变形式是安全的；
     不可变对象被多个线程调用时，不存在竞态条件问题
     不可变集合不需要考虑变化，因此可以节省时间和空间。所有不可变的集合都比它们的可变形式有更好的内存利用率（分析和测试细节）；
     不可变对象因为有固定不变，可以作为常量来安全使用。
     ❤❤❤❤防御性编程❤❤❤❤
     */
    @Test
    public void immutable(){
        final List students=ImmutableList.of(1,2,"3");
        students.add("4");//java.lang.UnsupportedOperationException
        ImmutableList list = ImmutableList.copyOf(students);
        list.add("4");//java.lang.UnsupportedOperationException
    }

    /**
     * 集合[set]概念的延伸，它的元素可以重复出现…与集合[set]相同而与元组[tuple]相反的是，Multiset元素的顺序是无关紧要的：Multiset {a, a, b}和{a, b, a}是相等的
     * 这里所说的集合[set]是数学上的概念，Multiset继承自JDK中的Collection接口，而不是Set接口，所以包含重复元素并没有违反原有的接口契约
     */
    @Test
    public void multiSet(){
        Multiset multiset = HashMultiset.create();
        multiset.add("a");
        multiset.add("a");
        multiset.add("b");
        multiset.add("c");
        System.out.println("容量："+multiset.size()+" 非重复数据容量："+multiset.entrySet().size()+" a元素的数量："+multiset.count("a"));
        TreeMultiset<Comparable> treeMultiset = TreeMultiset.create();//需要实现 Comparable
        treeMultiset.add("a");
        treeMultiset.add("a");
        treeMultiset.add("b");
        treeMultiset.add("c");
        System.out.println("a到c 不包含c的数量为："+treeMultiset.subMultiset("a",BoundType.CLOSED,"c",BoundType.OPEN).size());
    }

    /**
     *
     */
    @Test
    public void multiMap(){
        Multimap multiMap=HashMultimap.create();
        multiMap.put("1","a");
        multiMap.put("1","a");
        multiMap.put("1","b");
        System.out.println("multiMap.asMap().size():"+multiMap.asMap().size()+" multiMap.get(\"1\").size():"+multiMap.get("1").size());//multiMap.asMap().size():1 multiMap.get("1").size():2
    }

    /**
     * 实现键值对的双向映射 1对1
     */
    @Test
    public void biMap(){
        HashBiMap<Integer, String> idName = HashBiMap.create();
        idName.put(1,"zhangsan");
        idName.put(2,"lisi");
        idName.put(3,"wangwu");
        //idName.put(4,"wangwu");//java.lang.IllegalArgumentException: value already present: wangwu
        System.out.println(idName.get(1));
        System.out.println(idName.inverse().get("lisi"));
    }
    @Test
    public  void table(){
        HashBasedTable<Integer, Integer, String> table = HashBasedTable.create();
        table.put(0,0,"00");
        table.put(0,1,"01");
        table.put(0,2,"02");
        table.put(0,3,"03");
        table.put(1,0,"10");
        table.put(1,2,"12");
        System.out.println("第一行数量:"+table.row(0).size());//第一行数量:3
    }

}
