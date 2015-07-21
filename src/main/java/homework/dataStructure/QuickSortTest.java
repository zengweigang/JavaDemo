package homework.dataStructure;

import com.google.common.base.Joiner;
import com.google.common.primitives.Ints;
import org.junit.Test;

import java.util.List;

/**
 * Created by Administrator on 2015/7/13.
 */
public class QuickSortTest {
    /**
     * 快速排序（挖坑填数） 左边挖出的东西从右边找个小的替换  然后再从左边找个
     * http://blog.csdn.net/morewindows/article/details/6684558
     */
    @Test
    public void quickSort(){
        List<Integer> data = Ints.asList(12, 7, 8, 6);
        Joiner joiner = Joiner.on(",");
        System.out.println(joiner.join(data));
        int right=data.size()-1;
        int left=0;
        int x=data.get(0);
        while(left<right){//不断的向中间靠拢  知道 不符合循环条件了
            while (left<right){
                if(data.get(right)<x){
                    data.set(left,data.get(right));
                    break;
                }else {
                    right--;//right之后的 都是比基准数要大的
                }
            }
            while (left<right){
                if (data.get(left)>x){
                    data.set(right,data.get(left));//在左边找到一个比基准数要大的 丢到右边的坑里面去
                    break;
                }else {
                    left++;//left之前的都是比基准数要小的
                }
            }
        }
        data.set(left,x);
        System.out.println(joiner.join(data));
    }
}
