package test1;


import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.Date;

/**
* Created by Administrator on 2014/11/13.
*/
@Service
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:application-context.xml"})
public class Testshij {

    @org.junit.Test
    public void test(){
    Logger logger = Logger.getLogger(getClass());
    String s=new Date().toString();
    logger.debug(s);
    }
}
