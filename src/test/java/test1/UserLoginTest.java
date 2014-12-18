package test1;

import com.shinow.entity.TBaMemberInfoEntity;
import com.shinow.serverce.Md5;
import org.apache.log4j.Logger;
import org.hibernate.*;
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
public class UserLoginTest {
    private Md5 md;
    private TBaMemberInfoEntity menuInfo;

    public TBaMemberInfoEntity getMenuInfo() {
        return menuInfo;
    }
    @Resource
    private SessionFactory sessionFactory;


    public void setMenuInfo(TBaMemberInfoEntity menuInfo) {
        this.menuInfo = menuInfo;
    }

    @Test
    public void test(){
        Logger logger = Logger.getLogger(getClass());
        Session session=null;
        try{
             session=this.sessionFactory.openSession();
            Query q = session.createQuery("from TBaMemberInfoEntity where userName=?");
            q.setParameter(0, "user1");
            menuInfo = (TBaMemberInfoEntity)q.uniqueResult();
        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            session.close();
        }
        md = new Md5();
        logger.debug(menuInfo.getPwd());
        logger.debug(md.switchcode(menuInfo.getPwd()));
        menuInfo.setPwd(md.switchcode(menuInfo.getPwd()));
        try {
            session=this.sessionFactory.openSession();
            Transaction trans=session.beginTransaction();
            session.update(menuInfo);
            trans.commit();
            session.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
