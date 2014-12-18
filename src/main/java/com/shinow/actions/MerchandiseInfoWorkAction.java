package com.shinow.actions;

import com.shinow.entity.TBaDeliveryInfoEntity;
import com.shinow.entity.TMeMerchandiseInfoEntity;
import com.shinow.framework.action.BaseAction;
import com.shinow.framework.dao.BaseDAO;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2014-11-12.
 */
public class MerchandiseInfoWorkAction extends BaseAction {

    @Resource
    private BaseDAO<TMeMerchandiseInfoEntity> merDao;

    private List<TMeMerchandiseInfoEntity> merList;

    private List<TMeMerchandiseInfoEntity> stest;

    private TMeMerchandiseInfoEntity mer;

    private String success = "false";

    private String message;

    private int limit;

    private int page;

    private int countNumed;

    public String qureymer() {
        countNumed = merDao.queryRecordCount("select count(*) from TMeMerchandiseInfoEntity");
        merList = merDao.queryForPage("from TMeMerchandiseInfoEntity", page, limit);
        success = "true";
        return SUCCESS;
    }

    public void defaultVauel(TMeMerchandiseInfoEntity ff) {
        TMeMerchandiseInfoEntity defaultdel;
        int defaultId = 0;
        defaultdel = new TMeMerchandiseInfoEntity();
        defaultId = merDao.queryRecordCount("select  MAX(id) from TMeMerchandiseInfoEntity");
//        defaultdel.setId(defaultId);
        defaultdel =  merDao.queryOne("from TMeMerchandiseInfoEntity where id=?",defaultId);
//        defaultdel = merDao.findByExample(TMeMerchandiseInfoEntity.class, defaultdel).get(0);
        defaultdel.setId(defaultId + 1);
        String defaults = defaultdel.getMerchandiseid();
        int i = Integer.parseInt(defaults);
        i = i + 1;
        defaults = String.valueOf(i);
        int t = defaults.length();
        switch (t) {
            case 1: {
                defaults = "0000" + i;
                break;

            }
            case 2: {
                defaults = "000" + i;
                break;

            }
            case 3: {
                defaults = "00" + i;
                break;

            }
            case 4: {
                defaults = "0" + i;
                break;

            }
        }

        int id = defaultdel.getId();
        ff.setId(id);
        ff.setMerchandiseid(defaults);

    }

    public String Addmer() {
        defaultVauel(mer);
        try {
            Serializable o = merDao.insert(mer);
            if (null == o) {
                success = "false";
                message = "插入失败请重试！";
            } else {
                success = "true";
                message = "成功";
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            success = "false";
            message = "请查看是否有重复值";
        }
        return SUCCESS;
    }

    public String updatamer() {
        boolean coad = merDao.update(mer);
        if (coad) {
            success = "true";
            message = "成功";
        } else {
            success = "false";
            message = "更新失败请重试！";
        }
        return SUCCESS;
    }

    public String delect() {
        boolean coad = merDao.delete(mer);
        if (coad) {
            success = "true";
            message = "成功";
        } else {
            success = "false";
            message = "删除失败请重试！";
        }
        return SUCCESS;
    }

    public String test(){
        //       request.getParameter("stest");
        for(TMeMerchandiseInfoEntity it:stest){
            System.out.print(it);
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

    public List<TMeMerchandiseInfoEntity> getMerList() {
        return merList;
    }

    public void setMerList(List<TMeMerchandiseInfoEntity> merList) {
        this.merList = merList;
    }

    public TMeMerchandiseInfoEntity getMer() {
        return mer;
    }

    public void setMer(TMeMerchandiseInfoEntity mer) {
        this.mer = mer;
    }

    public List<TMeMerchandiseInfoEntity> getStest() {
        return stest;
    }

    public void setStest(List<TMeMerchandiseInfoEntity> stest) {
        this.stest = stest;
    }
}