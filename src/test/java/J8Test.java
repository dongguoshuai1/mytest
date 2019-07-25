import java.util.*;
import java.util.stream.Collectors;

/**
 * @author dong
 * @Title: J8Test
 * @ProjectName mytest
 * @Description: java8
 * @date 2019/5/13下午 5:23
 */
public class J8Test {


    public static void main(String[] args){
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1>o2) return 1;
                if(o1<o2) return -1;
                return 0;
            }
        };
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(1);
        list.add(2);
        list.add(1);
        boolean aa = list.stream().allMatch(integer -> {
            Map m = new HashMap();
            m.put("val", integer);
            if(integer>1){
                return true;
            }
            return false;
        });
        Map<String, List<Integer>> bb = list.stream().collect(Collectors.groupingBy(t -> t.toString()));
        System.out.println(list);
        System.out.println(aa);
    }

}
