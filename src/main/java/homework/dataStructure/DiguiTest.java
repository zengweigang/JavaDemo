package homework.dataStructure;

/**
 * Created by Administrator on 2015/7/14.
 */
public class DiguiTest {
    public void digui(int i){
        i++;
        if(i<=2){

            System.out.println("前"+i);
            digui(i);//将这段代码以后的逻辑 push到栈里面  等当前这个递归return (出口)以后 再从栈顶 pop出来
            System.out.println("中"+i);
//            digui(i);
//            System.out.println("后"+i);
        }else return;
    }

    public static void main(String[] args) {
        new DiguiTest().digui(0);
    }
}
