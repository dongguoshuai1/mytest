package dong.proxy;

import javax.swing.plaf.multi.MultiInternalFrameUI;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author dong
 * @Title: MyInvocationHandler
 * @ProjectName mytest
 * @Description: TODO
 * @date 2019/6/28下午 3:19
 */
public class MyInvocationHandler implements InvocationHandler {

    private Object obj;

    public MyInvocationHandler(Object obj){
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.print("befor");
        Object res = method.invoke(obj, args);
        System.out.print("after");
        return res;
    }
}
