package dong.proxy;


import java.lang.reflect.Proxy;

/**
 * @author dong
 * @Title: ProxyTest
 * @ProjectName mytest
 * @Description: TODO
 * @date 2019/6/28下午 3:22
 */
public class ProxyTest {

    public static void main(String[] args){
        ProxyTestInnerInferface proxyObj = (ProxyTestInnerInferface)Proxy.newProxyInstance(ProxyTest.class.getClassLoader(),
                ProxyTestInner.class.getInterfaces(),
                new MyInvocationHandler(new ProxyTestInner()));
        proxyObj.invoke1();
    }

    private interface ProxyTestInnerInferface{
        String invoke1();
    }

    private static class ProxyTestInner implements ProxyTestInnerInferface{
        public String invoke1(){
            return "1";
        }
    }
}
