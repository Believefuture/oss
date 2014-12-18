package com.shinow.actions;

import com.shinow.entity.FieldExplainEntity;
import com.shinow.framework.action.BaseAction;
import com.shinow.serverce.QueryFieldExplain;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2014-11-10.
 */
public class FieldJsonAction extends BaseAction {

    private List<FieldExplainEntity> fElist;

    @Resource
    private QueryFieldExplain qfe;

    private String tableName;

    public String queryField(){
        fElist=qfe.queryfElist(tableName);
        return SUCCESS;
    }

    public List<FieldExplainEntity> getfElist() {
        return fElist;
    }

    public void setfElist(List<FieldExplainEntity> fElist) {
        this.fElist = fElist;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
