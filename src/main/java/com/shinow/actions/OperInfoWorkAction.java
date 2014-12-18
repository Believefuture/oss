package com.shinow.actions;

import com.shinow.entity.TAuOperInfoEntity;
import com.shinow.entity.TAuRoleInfoEntity;
import com.shinow.entity.TBaSupplierInfoEntity;
import com.shinow.framework.action.BaseAction;
import com.shinow.framework.dao.BaseDAO;
import com.shinow.serverce.Md5;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2014/12/10.
 */
public class OperInfoWorkAction extends BaseAction{

    private List<TAuOperInfoEntity> operlist;

    private List<TAuRoleInfoEntity> rolelist;

    @Resource
    private BaseDAO<TAuRoleInfoEntity> roleDao;

    private TAuOperInfoEntity oper;

    @Resource
    private BaseDAO<TAuOperInfoEntity> operDao;


    private String success;

    private String message;

    private int limit;

    private int page;

    private int countNumed;

    public String queryoperlist(){
        countNumed =operDao.queryRecordCount("select count(*) from TAuOperInfoEntity");
        operlist=operDao.queryForPage("from TAuOperInfoEntity", page, limit);
        success ="true";
        return SUCCESS;
    }

    public String queryrolederault(){
        rolelist=roleDao.findByHql1("from TAuRoleInfoEntity where state=?",true);
        return SUCCESS;
    }

    public String defaultVauel(){
        Md5 md=new Md5();
        int defaultId = 0;
        TAuOperInfoEntity o = new TAuOperInfoEntity();
        defaultId= operDao.queryRecordCount("select  MAX(id) from TAuOperInfoEntity");
        o.setId(defaultId);
        o= operDao.findByExample(TAuOperInfoEntity.class ,o).get(0);
        oper.setId(defaultId+1);
        String defaults =o.getOperid();
        int i = Integer.parseInt(defaults.substring(3,6));
        i=i+1;
        defaults=String.valueOf(i);
        int t=defaults.length();
        switch (t){
            case 1:{
                defaults="00"+i;
                break;

            }
            case 2:{
                defaults="0"+i;
                break;

            }
        }
        oper.setPwd( md.switchcode("666"));
        String yy =oper.getRoleid().getRoleid();
        oper.setOperid(yy + defaults);
        return SUCCESS;
    }

    public String Addoper(){
        defaultVauel();
        try {
            Serializable o= operDao.insert(oper);
            if(null==o){
                success="false";
                message="插入失败请重试！";
            }else {
                success="true";
                message="成功";
            }
        } catch (Exception e) {
            e.printStackTrace();
            success="false";
            message="请查看是否有重复值";
        }

        return SUCCESS;
    }

    public List<TAuOperInfoEntity> getOperlist() {
        return operlist;
    }

    public void setOperlist(List<TAuOperInfoEntity> operlist) {
        this.operlist = operlist;
    }

    public TAuOperInfoEntity getOper() {
        return oper;
    }

    public void setOper(TAuOperInfoEntity oper) {
        this.oper = oper;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getCountNumed() {
        return countNumed;
    }

    public void setCountNumed(int countNumed) {
        this.countNumed = countNumed;
    }

    public List<TAuRoleInfoEntity> getRolelist() {
        return rolelist;
    }

    public void setRolelist(List<TAuRoleInfoEntity> rolelist) {
        this.rolelist = rolelist;
    }
}
