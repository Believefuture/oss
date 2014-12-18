package com.shinow.actions;

import com.shinow.entity.*;
import com.shinow.framework.action.BaseAction;
import com.shinow.framework.dao.BaseDAO;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2014-11-12.
 */
public class MerchandiseCInfoWorkAction extends BaseAction {

    @Resource
    private BaseDAO<TMeMerchandiseCInfoEntity> mccIDao;

    private List<TMeMerchandiseCInfoEntity> mccIList;

    private TMeMerchandiseCInfoEntity mccI;

    private String success="false";

    private String message;

    private int limit;

    private int page;

    private int countNumed;

    public String qureymccI(){
        countNumed=mccIDao.queryRecordCount("select count(*) from TMeMerchandiseCInfoEntity");
        mccIList = mccIDao.queryForPage("from TMeMerchandiseCInfoEntity",page,limit);
        success ="true";
        return SUCCESS;
    }
    public void defaultVauel(TMeMerchandiseCInfoEntity ff){
        TMeMerchandiseCInfoEntity defaultdel;
        int defaultId = 0;
        defaultdel = new TMeMerchandiseCInfoEntity();
        defaultId= mccIDao.queryRecordCount("select  MAX(id) from TMeMerchandiseCInfoEntity");
        defaultdel.setId(defaultId);
        defaultdel= mccIDao.findByExample(TMeMerchandiseCInfoEntity.class ,defaultdel).get(0);
        defaultdel.setId(defaultId+2);
        String defaults =defaultdel.getMerchandisecid();
        int i = Integer.parseInt(defaults);
        i=i+1;
        defaults=String.valueOf(i);
        int t=defaults.length();
        switch (t){
            case 1:{
                defaults="0"+i;
                break;

            }
         }

        int id =defaultdel.getId();
        ff.setId(id);
        ff.setMerchandisecid(defaults);
    }
    public String AddmccI(){
            defaultVauel(mccI);
            try {
                Serializable o= mccIDao.insert(mccI);
                if(null==o){
                    success="false";
                    message="插入失败请重试！";
                }else {
                    success="true";
                    message="成功";
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                success="false";
                message="请查看是否有重复值";
            }
        return SUCCESS;
    }

    public String updatamccI(){
        boolean coad = mccIDao.update(mccI);
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
        boolean coad = mccIDao.delete(mccI);
        if(coad){
            success="true";
            message="成功";
        }else{
            success="false";
            message="删除失败请重试！";
        }
        return SUCCESS;
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

    public List<TMeMerchandiseCInfoEntity> getMccIList() {
        return mccIList;
    }

    public void setMccIList(List<TMeMerchandiseCInfoEntity> mccIList) {
        this.mccIList = mccIList;
    }

    public TMeMerchandiseCInfoEntity getMccI() {
        return mccI;
    }

    public void setMccI(TMeMerchandiseCInfoEntity mccI) {
        this.mccI = mccI;
    }
}
