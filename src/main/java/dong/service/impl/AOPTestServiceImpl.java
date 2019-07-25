package dong.service.impl;

import dong.service.IAOPTestService;
import org.springframework.stereotype.Service;

/**
 * @author dong
 * @Title: AOPTestServiceImpl
 * @ProjectName mytest
 * @Description: TODO
 * @date 2019/5/15下午 2:52
 */
@Service
public class AOPTestServiceImpl implements IAOPTestService {
    @Override
    public void sayHello(String str) {
        System.out.println(str);
    }
}
