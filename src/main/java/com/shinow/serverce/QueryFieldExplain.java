package com.shinow.serverce;

import com.shinow.entity.FieldExplainEntity;
import com.shinow.framework.dao.BaseDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;

/**
 * Created by Administrator on 2014-11-10.
 */
@Service
public class QueryFieldExplain {

    @Resource
    private BaseDAO<FieldExplainEntity> fEDao;

    private List<FieldExplainEntity> fElist;

    private FieldExplainEntity f;

    public List<FieldExplainEntity> queryfElist(String tableName){
        f= new FieldExplainEntity();
        f.setTableName(tableName);
        fElist = fEDao.findByExample(FieldExplainEntity.class,f);
        return fElist;
    };

    public List<FieldExplainEntity> getfElist() {
        return fElist;
    }

    public void setfElist(List<FieldExplainEntity> fElist) {
        this.fElist = fElist;
    }


}
