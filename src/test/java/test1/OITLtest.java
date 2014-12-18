package test1;

import com.shinow.entity.TAuOperInfoEntity;
import com.shinow.serverce.Md5;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
public class OITLtest {
    private TAuOperInfoEntity operInfo;
    private Md5 md;

    @Resource
    private SessionFactory sessionFactory;


    @Test
    public void test(){
        Logger logger = Logger.getLogger(getClass());
        Session session=null;
        try{
            session=this.sessionFactory.openSession();
            Query q = session.createQuery("from TAuOperInfoEntity where operid=?");
            q.setParameter(0, "001001");
            operInfo = (TAuOperInfoEntity)q.uniqueResult();
        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            session.close();
        }
        md = new Md5();
        logger.debug(operInfo.getPwd());
        logger.debug(md.switchcode(operInfo.getPwd()));
        operInfo.setPwd(md.switchcode(operInfo.getPwd()));
        try {
            session=this.sessionFactory.openSession();
            Transaction trans=session.beginTransaction();
            session.update(operInfo);
            trans.commit();
            session.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
