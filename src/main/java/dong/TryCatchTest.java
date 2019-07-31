package dong;

/**
 * @author dong
 * @Title: TryCatchTest
 * @ProjectName mytest
 * @Description: TODO
 * @date 2019/7/30下午 2:49
 */
public class TryCatchTest {

    public static void main(String[] args){
        System.out.println(new TryCatchTest().anInt());
    }

    public int anInt(){
        int a = 1;
        try{
            a = 2;
            return a;
        }catch (Exception e){
            a = 3;
            return a;
        }finally {
            a = 4;
        }
    }
}
