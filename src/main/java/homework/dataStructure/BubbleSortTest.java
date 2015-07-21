package homework.dataStructure;

import com.google.common.base.Joiner;
import com.google.common.primitives.Ints;
import org.junit.Test;

import java.util.List;

/**
 * Created by Administrator on 2015/7/13.
 */
public class BubbleSortTest {
    /**
     * 冒泡排序
     * 第一层循环一次  把最大数放到最后面
     */
    @Test
    public  void  bubbleSort(){
        List<Integer> data = Ints.asList(12, 7, 8, 6);
        Joiner joiner = Joiner.on(",");
        System.out.println(joiner.join(data));
        boolean isChange=true;
        while (isChange){
            isChange=false;
            for (int i = 0; i < data.size()-1; i++) {
                Integer temp1=data.get(i);
                Integer temp2=data.get(i+1);
                if (temp1>temp2){
                    data.set(i,temp2);
                    data.set(i+1,temp1);
                    isChange=true;
                }
            }
        }
        System.out.println(joiner.join(data));
    }

    /**
     * 双向冒泡排序/鸡尾酒排序
     */
    @Test
    public  void cocktailSort(){
        List<Integer> data = Ints.asList(12, 7, 8, 6);
        Joiner joiner = Joiner.on(",");
        System.out.println(joiner.join(data));
        int headIndex=0;
        int tailIndex=data.size()-1;
        while (headIndex<tailIndex){
            for (int j=tailIndex;j>headIndex;j--){
                if(data.get(j)<data.get(j-1)){
                    Integer temp=data.get(j);
                    data.set(j,data.get(j-1));
                    data.set(j-1,temp);
                }
            }//最小的已经跑到前面去了
            headIndex++;
            for(int j=headIndex;j<tailIndex;j++){
                if(data.get(j)>data.get(j+1)){
                    Integer temp=data.get(j);
                    data.set(j,data.get(j+1));
                    data.set(j+1,temp);
                }
            }//最大的已经跑到后面去了
            tailIndex--;
        }
        System.out.println(joiner.join(data));
    }
}
