package test1;

import com.shinow.entity.TAuOperInfoEntity;
import com.shinow.serverce.Operlogin;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2014-11-06.
 */
@Service
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:application-context.xml"})
public class serverceTest {

    @Resource
    private Operlogin ol;

    @Resource
    private SessionFactory sessionFactory;

    private TAuOperInfoEntity operInfo;
    @Test
    public void test(){
        Logger logger = Logger.getLogger(getClass());
        Session session=null;
        boolean o = false;
        operInfo = new TAuOperInfoEntity();
        operInfo.setOpername("admin");
        operInfo.setPwd("admin");
        try{
            session=this.sessionFactory.openSession();
           o=ol.login(operInfo);
            logger.debug(1);
        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            session.close();
        }
       logger.debug(o);

    }
}
