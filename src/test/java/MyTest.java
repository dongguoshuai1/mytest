//import org.joda.time.LocalDateTime;
//import org.junit.Test;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.function.BiFunction;
//import java.util.function.Function;
//import java.util.function.Predicate;
//import java.util.stream.Collectors;

/**
 * @author dong
 * @Title: MyTest
 * @ProjectName esTest
 * @Description: TODO
 * @date 2019/4/30下午 1:52
 */
public class MyTest extends MyTest2{

    int int1 = 128;
    final int int2 = 128;
    final Integer integer1 = 128;
    final Integer integer2 = 128;

    public static void main(String[] args){
        String a1 = "1";
        String a4 = "4";
        String append1 = "1"+a1+"3"+a4;
        String append2 = append1+a4;

        int a = 15 >> 1;
        System.out.print(a);
//        int a = 1;
//        int b = 2;
//        int c = a+b;
//        System.out.print(c);
//        Date date = new Date();
//        new Thread(() -> {
//            System.out.println(1);
//        });
//        List<Integer> list = new ArrayList<Integer>();
//        list.add(1);
//        list.add(2);
//        List<Integer> biglist = list.parallelStream()
//                .filter(i -> {
//                    if(i > 1)
//                        return false;
//                    else
//                        return true;
//                })
//                .collect(Collectors.toList());
//        Predicate<Integer> predicate = i -> {
//                if(i.equals(1)){
//                    return true;
//                }else{
//                    return false;
//                }
//            };
//        System.out.println(biglist);
    }
}
