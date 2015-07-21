package homework.dataStructure;

import com.google.common.base.Joiner;
import com.google.common.primitives.Ints;
import org.junit.Test;

import java.util.List;

/**
 * Created by zengweigang on 2015/7/10.
 */
public class SelectionSortTest {
    /**
     * 选择排序
     * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾
     */
    @Test
    public void selecttionSort(){
        List<Integer> data = Ints.asList(12, 7, 8, 6);
        Joiner joiner = Joiner.on(",");
        System.out.println(joiner.join(data));
        for (int i = 0; i < data.size(); i++) {
            int minIndex=i;//无序区的第一位初始化
            int minValue=data.get(i);//无序区的第一位
            for (int j = i+1; j <data.size() ; j++) {//找到最小值
                if(data.get(j)<minValue){
                    minIndex=j;
                    minValue=data.get(j);
                }
            }
            data.set(minIndex,data.get(i));
            data.set(i,minValue);
        }
        System.out.println(joiner.join(data));
    }
}
