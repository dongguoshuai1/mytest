package spring;

import dong.controller.TestController;
import dong.service.IAOPTestService;
import dong.service.impl.AOPTestServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author dong
 * @Title: SpringTest
 * @ProjectName mytest
 * @Description: spring测试
 * @date 2019/5/15下午 2:47
 */
public class SpringTest {

    private ApplicationContext context;

    @Before
    public void befor(){
        context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
    }

    @Test
    public void test(){
        IAOPTestService iaopTestService = context.getBean(IAOPTestService.class);
        iaopTestService.sayHello("1");
        System.out.println(context);
    }
}
