package test1;

import com.shinow.entity.TBaSupplierInfoEntity;
import com.shinow.framework.dao.BaseDAO;
import org.apache.log4j.Logger;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import javax.annotation.Resource;

/**
 * Created by Administrator on 2014/11/13.
 */
@Service
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:application-context.xml"})
public class Test {

    @Resource
    private BaseDAO<TBaSupplierInfoEntity> autDao;
    @Resource
    private SessionFactory sessionFactory;

    private TBaSupplierInfoEntity aut;

    @org.junit.Test
    public void test(){
        Logger logger = Logger.getLogger(getClass());
        aut = new TBaSupplierInfoEntity();
        int defaultId = 0;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Query q = session.createQuery("select  MAX(id) from TBaSupplierInfoEntity");
            defaultId = Integer.valueOf(q.uniqueResult().toString());
            aut.setId(defaultId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }finally {
            session.close();
        }
        try {
            session = sessionFactory.openSession();
            Example example = Example.create(aut);
            Criteria criteria = session.createCriteria(TBaSupplierInfoEntity.class);
            criteria.add(example);
            aut= (TBaSupplierInfoEntity)criteria.list().get(0);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }finally {
            session.close();
        }
        logger.debug(aut.getSupplierid());
        logger.debug(Integer.valueOf(aut.getSupplierid()).intValue());
        logger.debug(aut.getId());
    }

    public TBaSupplierInfoEntity getAut() {
        return aut;
    }

    public void setAut(TBaSupplierInfoEntity aut) {
        this.aut = aut;
    }
}
