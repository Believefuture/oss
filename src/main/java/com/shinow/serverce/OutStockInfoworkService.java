package com.shinow.serverce;

import com.shinow.entity.TMeInStockDetailsInfoEntity;
import com.shinow.entity.TMeInStockInfoEntity;
import com.shinow.entity.TMeOutStockDetailsInfoEntity;
import com.shinow.entity.TMeOutStockInfoEntity;
import com.shinow.framework.dao.BaseDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by Administrator on 2014/11/24.
 */
@Service
public class OutStockInfoworkService {

    @Resource
    private BaseDAO<TMeOutStockInfoEntity> merDao;
    @Resource
    private BaseDAO<TMeOutStockDetailsInfoEntity> instoDao;

    @Transactional
    public boolean instock(TMeOutStockInfoEntity instockinfo,List<TMeOutStockDetailsInfoEntity> instockdeta_list){
        boolean result=false;
        if(instockinfo!=null&instockdeta_list!=null) {
            String uuid = (String) merDao.insert(instockinfo);
            for (TMeOutStockDetailsInfoEntity in : instockdeta_list) {
                in.setOutbillcode(instockinfo);
                instoDao.insert(in);
            }
            result = true;
        }
        return result;
    }
    @Transactional
    public boolean update ( TMeOutStockInfoEntity instockinfo,List<TMeOutStockDetailsInfoEntity> instockdeta_list) {
        boolean result = false;
        if(instockinfo!=null&instockdeta_list!=null) {
            for (TMeOutStockDetailsInfoEntity in : instockdeta_list) {
                if(in.getId()==null){
                    in.setOutbillcode(instockinfo);
                    instoDao.insert(in);
                }
                in.setOutbillcode(instockinfo);
                instoDao.update(in);
            }
            merDao.update(instockinfo);
            result = true;
        }
        return result;

    }
    @Transactional
    public boolean detele (TMeOutStockInfoEntity instockinfo) {
        boolean result = false;

            int count=instoDao.extcuteHQL("DELETE FROM TMeOutStockDetailsInfoEntity WHERE outbillcode.outbillcode=?",instockinfo.getOutbillcode());
            if(count>0){
             int t=   merDao.extcuteHQL("DELETE FROM TMeOutStockInfoEntity WHERE outbillcode=?",instockinfo.getOutbillcode());
                if(t>0){
                result = true;
                }
            }
        return result;

    }
}
