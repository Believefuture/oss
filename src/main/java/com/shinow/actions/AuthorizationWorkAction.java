package com.shinow.actions;

import com.shinow.entity.TAuAuthorizationEntity;
import com.shinow.entity.TBaSupplierInfoEntity;
import com.shinow.framework.action.BaseAction;
import com.shinow.framework.dao.BaseDAO;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2014-11-12.
 */
public class AuthorizationWorkAction extends BaseAction {

    @Resource
    private BaseDAO<TBaSupplierInfoEntity> autDao;

    private List<TBaSupplierInfoEntity> autList;

    private TBaSupplierInfoEntity aut;

    private String success="false";

    private String message;

    private int limit;

    private int page;

    private int countNumed;

    public String qureyAut(){
        countNumed=autDao.queryRecordCount("select count(*) from TBaSupplierInfoEntity");
        autList = autDao.queryForPage("from TBaSupplierInfoEntity",page,limit);
        success ="true";
        return SUCCESS;
    }

    public String AddAut(){

            try {
                Serializable o= autDao.insert(aut);
                if(null==o){
                    success="false";
                    message="插入失败请重试！";
                }else {
                    success="true";
                    message="成功";
                }
            } catch (Exception e) {
                success="false";
                message="请查看是否有重复值";
            }

        return SUCCESS;
    }

    public String defaultVauel(){
        int defaultId = 0;
        aut = new TBaSupplierInfoEntity();
        defaultId= autDao.queryRecordCount("select  MAX(id) from TBaSupplierInfoEntity");
        aut.setId(defaultId);
        aut= autDao.findByExample(TBaSupplierInfoEntity.class ,aut).get(0);
        aut.setId(defaultId+1);
        String defaults =aut.getSupplierid();
        int i = Integer.parseInt(defaults);
        i=i+1;
        defaults=String.valueOf(i);
        int t=defaults.length();
        switch (t){
            case 1:{
                defaults="00000"+i;
                break;

            }
            case 2:{
                defaults="0000"+i;
                break;

            }
            case 3:{
                defaults="000"+i;
                break;

            }
            case 4:{
                defaults="00"+i;
                break;

            }
            case 5:{
                defaults="0"+i;
                break;

            }
        }
        aut.setSupplierid(defaults);
        return SUCCESS;
    }

    public String updataAut(){
        boolean coad = autDao.update(aut);
        if(coad){
            success="true";
            message="成功";
        }else{
            success="false";
            message="更新失败请重试！";
        }
        return SUCCESS;
    }

    public String delect(){
        boolean coad = autDao.delete(aut);
        if(coad){
            success="true";
            message="成功";
        }else{
            success="false";
            message="删除失败请重试！";
        }
        return SUCCESS;
    }

    public List<TBaSupplierInfoEntity> getAutList() {
        return autList;
    }

    public void setAutList(List<TBaSupplierInfoEntity> autList) {
        this.autList = autList;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getCountNumed() {
        return countNumed;
    }

    public void setCountNumed(int countNumed) {
        this.countNumed = countNumed;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public TBaSupplierInfoEntity getAut() {
        return aut;
    }

    public void setAut(TBaSupplierInfoEntity aut) {
        this.aut = aut;
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
}
