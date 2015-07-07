package homework.guava;

import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import org.junit.Test;

import javax.swing.text.html.Option;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Created by Administrator on 2015/7/3.
 */
public class BasicUtilities {
    /**
     * Optional存在的意义在于 null本身语义的模糊性
     * Optional<T>的最常用价值在于，例如，假设一个方法返回某一个数据类型，调用这个方法的代码来根据这个方法的返回值来做下一步的动作，
     * 若该方法可以返回一个null值表示成功，或者表示失败，在这里看来都是意义含糊的，所以使用Optional<T>作为返回值，
     * 则后续代码可以通过isPresent()来判断是否返回了期望的值（原本期望返回null或者返回不为null，其意义不清晰），
     * 并且可以使用get()来获得实际的返回值
     */
    @Test
    public void optional(){
        Optional<Integer> optional=Optional.absent();
        if(optional.isPresent()){
            System.out.println(optional.get());
        }
        optional=Optional.of(998);
        if(optional.isPresent()){
            System.out.println(optional.get());
        }

        optional=Optional.fromNullable(null);
        if(optional.isPresent()){
            System.out.println(optional.get());
        }

        Set<Integer> set = optional.asSet();
        System.out.println(set.size());
        optional=Optional.of(999);
        System.out.println(optional.asSet().size());
    }

    /**
     * 在日常开发中，我们经常会对方法的输入参数做一些数据格式上的验证，以便保证方法能够按照正常流程执行下去
     *
     */
    @Test
    public void preconditions(){
        Optional<Boolean> isSuccess=Optional.fromNullable(true);
        Preconditions.checkArgument(isSuccess.get(),"执行结果必须成功 当前结果是：%s",isSuccess.get());
        Optional<Integer> age=Optional.fromNullable(3);
        Preconditions.checkArgument(age.get()>8,"年龄不合格 当前年龄是：%s ",age.get());
    }
    @Test
    public void objects(){
        System.out.println(Objects.equal(null,null));
        System.out.println(Objects.hashCode(1,2,4,"kk"));
        System.out.println(Objects.toStringHelper(this).add("x",1).add("y",2));

    }
    @Test
    public void sort(){
        System.out.println(ComparisonChain.start().compare(2l,2l).compare(1,1).compare(4f,4f).compare("x","y").result());
        Ordering<String> orderByLength=new Ordering<String>() {
            @Override
            public int compare(String s, String s2) {
                return Ints.compare(s.length(),s2.length());
            }
        }.nullsFirst().compound(new Comparator<String>() {
            @Override
            public int compare(String s, String s2) {
                return ComparisonChain.start().compare(s,s2).result();
            }
        });
        System.out.println(orderByLength.compare("xx","yy"));;
        System.out.println(orderByLength.compare("xx","y"));;

        List<String> list = Lists.newArrayList();
        list.add("kingg");
        list.add("why");
        list.add("bad");
        list.add("good");
        list.add("jhon");
        list.add("kong");

        System.out.println("list:"+ list);

        Ordering<String> naturalOrdering = Ordering.natural();
        Ordering<Object> usingToStringOrdering = Ordering.usingToString();
        Ordering<Object> arbitraryOrdering = Ordering.arbitrary();//随机顺序

        System.out.println("naturalOrdering:"+ naturalOrdering.sortedCopy(list));
        System.out.println("usingToStringOrdering:"+ usingToStringOrdering.sortedCopy(list));
        System.out.println("arbitraryOrdering:"+ arbitraryOrdering.sortedCopy(list));
    }

    /**
     * 有时候，你会想把捕获到的异常再次抛出。
     * 这种情况通常发生在Error或RuntimeException被捕获的时候，
     * 你没想捕获它们，但是声明捕获Throwable和Exception的时候，
     * 也包括了了Error或RuntimeException。Guava提供了若干方法，来判断异常类型并且重新传播异常
     * @throws SQLException
     */
    @Test
    public void throwables() throws SQLException, NoSuchMethodException {
        try {

        }catch (NullPointerException e){
            //处理异常
        }catch (Throwable t){
            Throwables.propagateIfInstanceOf(t, SQLException.class);
            Throwables.propagateIfInstanceOf(t, NoSuchMethodException.class);
        }
    }
}
