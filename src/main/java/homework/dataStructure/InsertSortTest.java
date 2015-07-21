package homework.dataStructure;

import com.google.common.base.Joiner;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 插入排序：直接插入排序、二分法插入排序、希尔排序。
 * 前两个稳定  希尔不稳定   如何判断是否稳定   加入  x和y一样 x排序前在y前面  排序后保持一致  就是稳定
 * Created by Administrator on 2015/7/8.
 */
public class InsertSortTest {
    /**
     * 1.每步将一个待排序的记录，按其顺序码大小插入到前面已经排序的字序列的合适位置（从后向前找到合适位置后），直到全部插入排序完为止
     * 2.假设待排序的记录存放在数组R[1..n]中。初始时，R[1]自成1个有序区，无序区为R[2..n]。从i=2起直至i=n为止，依次将R[i]插入当前的有序区R[1..i-1]中，生成含n个记录的有序区
     * 算法复杂度： O(n^2)
     * 最坏复杂度：n(n-1)/2          等差数列(n-1  +  n-2  + n-3 ....n-n)      = (0+1+2+3+4+5+6+7)
     * 使用场景：不太适合大数据量的排序，
     */
    @Test
    public  void directInsertSort(){
        List<Integer> list = Ints.asList(12,7, 8, 6);
        Joiner joiner = Joiner.on(",");
        System.out.println(joiner.join(list));
        for (int i = 1; i < list.size(); i++) {
            Integer item = list.get(i);//待排序的元素
            int j=i-1;//待比较的有序区元素
            while (j>=0&&item<list.get(j)){//
                    list.set(j + 1, list.get(j));
                    j--;
            }
            list.set(j+1, item);//j 代表是比待排序小的元素   j+1 就是带排序的位置
        }
        System.out.println(joiner.join(list));
    }

    /**
     *
     1、从第一个元素开始，该元素可以认为已经被排序
     2、取出下一个元素，在【已经排序的元素序列中】【二分查找】到第一个比它小的位置
     3、将新元素插入到该位置后
     *二分法插入排序  也就是折半查找有序区 然后插入排序
     */
    @Test
    public void dichotomaInsertSort(){
        List<Integer> data = Ints.asList(12,7, 8, 6);
        Joiner joiner = Joiner.on(",");
        System.out.println(joiner.join(data));
        for (int i = 1; i < data.size(); i++)
        {
            int temp = data.get(i);
            int low = 0;
            int high = i - 1; //0到 i-1 都是已经排序好的  所以用二分法去有序序列找位置
            while (low <= high)
            {
                int mid = (low + high) / 2;
                if (temp < data.get(mid))
                {
                    high = mid - 1;
                }
                else
                {
                    low = mid + 1;
                }
            }
            for (int j = i - 1; j > high; j--)//i所在的位置 就是temp  high以前的都是比temp小的 所以不用管
            {
                data.set(j+1,data.get(j));
            }

            data.set(high+1,temp);//high+1肯定等于low

        }
        System.out.println(joiner.join(data));
    }

    /**
     * 先间隔d的进行对比  然后再间隔d/2进行对比  然后间隔d/4进行对比  直到间隔为1 对比一次后结束
     * 希尔排序【递减增量排序算法】
     */
    @Test
    public  void hillSort(){
        List<Integer> data = Ints.asList(12,7, 8, 6);
        Joiner joiner = Joiner.on(",");
        System.out.println(joiner.join(data));
        int d=data.size();
        while(true){
            d=d/2;
            for(int x=0;x<d;x++){//前半截
                for(int y=x+d;y<data.size();y=y+d){
                    int temp=data.get(y);
                    int j;
                    for(j=y-d;j>=0&&data.get(j)>temp;j=j-d){
                        data.set(j+d,data.get(j));
                    }
                    data.set(j+d,temp);
                }
            }
            if(d==1){
                break;
            }
        }
        System.out.println(joiner.join(data));
    }
}
