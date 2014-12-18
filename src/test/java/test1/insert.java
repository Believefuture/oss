package test1;

import com.shinow.entity.TMeInStockInfoEntity;

import com.shinow.entity.TMeOutStockInfoEntity;
import com.shinow.framework.dao.TestBaseDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by Administrator on 2014-11-06.
 */
@Service
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:application-context.xml"})
public class insert {
    @Resource
    private JdbcTemplate jt;
    @Resource
    private TestBaseDAO<TMeOutStockInfoEntity> instockdao;

    public String defaultVauel(){
        TMeOutStockInfoEntity defaultdel;
        int defaultId = 0;
        defaultdel = new TMeOutStockInfoEntity();
        defaultId= instockdao.queryRecordCount("select  MAX(id) from TMeOutStockInfoEntity");
        defaultdel.setId(defaultId);
        defaultdel= instockdao.findByExample(TMeOutStockInfoEntity.class ,defaultdel).get(0);
        defaultdel.setId(defaultId+2);
        String defaults =defaultdel.getOutbillcode();
        int i = Integer.parseInt(defaults);
        i=i+1;
        defaults=String.valueOf(i);
        int t=defaults.length();
        switch (t){
            case 1:{
                defaults="0000"+i;
                break;

            }
            case 2:{
                defaults="000"+i;
                break;

            } case 3:{
                defaults="00"+i;
                break;

            }case 4:{
                defaults="0"+i;
                break;

            }
        }
        return defaults;
    }
    @Test
    public void test(){
        String t ="";
        for(int i=1;i<100001 ;i=i+1){
            t = defaultVauel();
            jt.update("insert into tme_outstockinfo values(?,?,?,?,?,?,?)",  new Object[] {t,"001001",new Date(),"test",1,200,"test"});
            jt.update("insert into tme_outstockdetailsinfo values (?,?,?,?,?)",new Object[]{"00002",t,4,50,20});
        }


    }
}
