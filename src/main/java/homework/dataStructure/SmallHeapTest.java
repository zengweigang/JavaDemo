package homework.dataStructure;

import com.google.common.base.Joiner;
import com.google.common.primitives.Ints;

import java.util.List;

/**
 * 最大堆的测试
 * Created by Administrator on 2015/7/13.
 */
public class SmallHeapTest {
    public int parent(int i){
        return (i-1)/2;
    }
    public int left(int i){
        return (2*i+1);
    }
    public int right(int i ){
        return (2*i+2);
    }

    /**
     * 堆调整
     * 对以i为根节点的子树进行堆调整(保持最小堆的特性)
     */
    public void adjustHeap(List<Integer> data,int father,int heap_size){
        int l=left(father);
        int r=right(father);
        int smallest;
        int temp;
        if(l<=heap_size-1&&data.get(l)<data.get(father)){
            smallest=l;
        }else{
            smallest=father;
        }
        if(r<=heap_size-1&&data.get(r)<data.get(smallest)){
            smallest=r;
        }
        if(smallest!=father){//无最小堆的特性 需要调整      |一直递归到不做调整了为止
            temp=data.get(father);
            data.set(father,data.get(smallest));
            data.set(smallest,temp);
            adjustHeap(data, smallest, heap_size);
        }
    }

    /**
     * 建堆是一个通过不断的堆调整，使得整个二叉树中的数满足堆性质的操作
     *总结：就是去检查所有的父节点是否符合堆的特性 不符合就要去调整（和自己的子节点进行交换）
     *
     * 构造堆
     * @param data
     */
    public void buildHeap(List<Integer> data,int heap_size){
        int finalRootIndex=heap_size-1;//最后一个子节点的下标
        int finalFatherIndex=parent(finalRootIndex);//最后一个子节点父节点下标
        for(int i=finalFatherIndex;i>=0;i--){//从最后一个父节点开始调整堆 接下来就是倒数第二个 第三个父节点进行堆调整
            adjustHeap(data, i, heap_size);
        }
    }

    /**
     * 堆排序
     * 就是不断的从堆顶里面拿出东西
     * 数组储存成堆的形式之后，
     * 第一次将A[0]与A[n - 1]交换，再对A[0…n-2]重新恢复堆。
     * 第二次将A[0]与A[n-2]交换，再对A[0…n-3]重新恢复堆，重复这样的操作直到A[0]与A[1]交换。由于每次都是将最小的数据并入到后面的有序区间
     * @param data
     */
    public  void sortHeap(List<Integer> data){
        Joiner join=Joiner.on(",");
        buildHeap(data,data.size());
        System.out.println("建堆后："+join.join(data));
        int temp;
        for (int i =data.size()-1; i >=0; i--) {
            temp=data.get(0);
            data.set(0,data.get(i));
            data.set(i,temp);
            adjustHeap(data, 0, i + 1 - 1);//【对无序堆进行堆调整】 i+1 等于当前的size  -1代表让data[i] 不参与了   其实就是让最小的不参与 沉淀到数组最后
        }

        System.out.println("最小堆 倒序  排序后："+join.join(data));

    }
    public  void topK(int k,List<Integer> data){
        Joiner join=Joiner.on(",");
        if(k-1<=data.size()){
            List<Integer> kData=data.subList(0,k);
            buildHeap(kData,k);
            for(int i=k;i<data.size();i++){
                Integer root=kData.get(0);
                Integer temp=data.get(i);
                if(temp>root){
                    data.set(0,temp);
                    adjustHeap(kData,0,kData.size());
                }
            }
            System.out.println("TOP "+k+":"+join.join(kData)+" topk是无序的 如果要排序 还得再堆排序一次 所以这个topk 指的是 取前k个元素  但是取出来是无序的");
        }

    }
    public static void main(String[] args) {
        List<Integer> data = Ints.asList(12, 7, 8, 6,20,4);
        Joiner joiner = Joiner.on(",");
        System.out.println("源数据："+joiner.join(data));
        SmallHeapTest smallHeapTest = new SmallHeapTest();
        smallHeapTest.sortHeap(data);

        data = Ints.asList(12, 7, 8, 6,20,4);
        smallHeapTest.topK(3,data);
    }
}
