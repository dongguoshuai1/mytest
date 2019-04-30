import org.junit.Test;

/**
 * @author dong
 * @Title: MyTest
 * @ProjectName esTest
 * @Description: TODO
 * @date 2019/4/30下午 1:52
 */
public class MyTest {

    @Test
    public void test0(){
        MyLinkedList<String> linkedList = new MyLinkedList<String>();
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        for(String a:linkedList){
            System.out.println(a);
        }
    }
}
