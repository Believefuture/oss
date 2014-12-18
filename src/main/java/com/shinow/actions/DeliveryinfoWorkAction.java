package com.shinow.actions;

import com.shinow.entity.TBaDeliveryInfoEntity;
import com.shinow.entity.TBaSupplierInfoEntity;
import com.shinow.framework.action.BaseAction;
import com.shinow.framework.dao.BaseDAO;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2014-11-12.
 */
public class DeliveryinfoWorkAction extends BaseAction {

    @Resource
    private BaseDAO<TBaDeliveryInfoEntity> delDao;

    private List<TBaDeliveryInfoEntity> delList;

    private TBaDeliveryInfoEntity del;

    private String success="false";

    private String message;

    private int limit;

    private int page;

    private int countNumed;

    public String qureydel(){
        countNumed=delDao.queryRecordCount("select count(*) from TBaDeliveryInfoEntity");
        delList = delDao.queryForPage("from TBaDeliveryInfoEntity",page,limit);
        success ="true";
        return SUCCESS;
    }
    public void defaultVauel(TBaDeliveryInfoEntity ff){
        TBaDeliveryInfoEntity defaultdel;
        int defaultId = 0;
        defaultdel = new TBaDeliveryInfoEntity();
        defaultId= delDao.queryRecordCount("select  MAX(id) from TBaDeliveryInfoEntity");
        defaultdel.setId(defaultId);
        defaultdel= delDao.findByExample(TBaDeliveryInfoEntity.class ,defaultdel).get(0);
        defaultdel.setId(defaultId+2);
        String defaults =defaultdel.getDeliveryid();
        int i = Integer.parseInt(defaults);
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

        int id =defaultdel.getId();
        ff.setId(id);
        ff.setDeliveryid(defaults);
    }
    public String Adddel(){
            defaultVauel(del);
            try {
                Serializable o= delDao.insert(del);
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

    public String updatadel(){
        boolean coad = delDao.update(del);
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
        boolean coad = delDao.delete(del);
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

    public List<TBaDeliveryInfoEntity> getDelList() {
        return delList;
    }

    public void setDelList(List<TBaDeliveryInfoEntity> delList) {
        this.delList = delList;
    }

    public TBaDeliveryInfoEntity getDel() {
        return del;
    }

    public void setDel(TBaDeliveryInfoEntity del) {
        this.del = del;
    }



}
