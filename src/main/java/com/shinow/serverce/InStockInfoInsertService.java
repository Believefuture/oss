package com.shinow.serverce;

import com.shinow.entity.TMeInStockDetailsInfoEntity;
import com.shinow.entity.TMeInStockInfoEntity;
import com.shinow.framework.dao.BaseDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by Administrator on 2014/11/24.
 */
@Service
public class InStockInfoInsertService {

    @Resource
    private BaseDAO<TMeInStockInfoEntity> merDao;
    @Resource
    private BaseDAO<TMeInStockDetailsInfoEntity> instoDao;

    @Transactional
    public boolean instock(TMeInStockInfoEntity instockinfo,List<TMeInStockDetailsInfoEntity> instockdeta_list){
        boolean result=false;
        if(instockinfo!=null&instockdeta_list!=null) {
            String uuid = (String) merDao.insert(instockinfo);
            for (TMeInStockDetailsInfoEntity in : instockdeta_list) {
                in.setBillcode(instockinfo);
                instoDao.insert(in);
            }
            result = true;
        }
        return result;
    }
    @Transactional
    public boolean update ( TMeInStockInfoEntity instockinfo,List<TMeInStockDetailsInfoEntity> instockdeta_list) {
        boolean result = false;
        if(instockinfo!=null&instockdeta_list!=null) {
            for (TMeInStockDetailsInfoEntity in : instockdeta_list) {
                if(in.getId()==null){
                    in.setBillcode(instockinfo);
                    instoDao.insert(in);
                }
                in.setBillcode(instockinfo);
                instoDao.update(in);
            }
            merDao.update(instockinfo);
            result = true;
        }
        return result;

    }
    @Transactional
    public boolean detele (TMeInStockInfoEntity instockinfo) {
        boolean result = false;

            int count=instoDao.extcuteHQL("DELETE FROM TMeInStockDetailsInfoEntity WHERE billcode.billcode=?",instockinfo.getBillcode());
            if(count>0){
             int t=   merDao.extcuteHQL("DELETE FROM TMeInStockInfoEntity WHERE billcode=?",instockinfo.getBillcode());
                if(t>0){
                result = true;
                }
            }
        return result;

    }
}
