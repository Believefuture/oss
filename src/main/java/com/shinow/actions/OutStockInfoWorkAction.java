package com.shinow.actions;


import com.shinow.entity.*;
import com.shinow.framework.action.BaseAction;
import com.shinow.framework.dao.BaseDAO;
import com.shinow.serverce.InStockInfoInsertService;
import com.shinow.serverce.OutStockInfoworkService;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2014/11/24.
 */
public class OutStockInfoWorkAction extends BaseAction{

//    @Resource
//    private BaseDAO<TMeMerchandiseInfoEntity> merDao;

    @Resource
    private BaseDAO<TMeOutStockDetailsInfoEntity> outstockdetaDao;

    @Resource
    private BaseDAO<TMeOutStockInfoEntity> outstockDao;

    @Resource
    private OutStockInfoworkService outstockfunction;

//    private List<TMeMerchandiseInfoEntity> merlist;

    private TMeOutStockInfoEntity outstock;

    private List<TMeOutStockInfoEntity> outstocklist;

    private List<TMeOutStockDetailsInfoEntity> outstockdetalist;

    private String success;

    private String message;

    private int limit;

    private int page;

    private int countNumed;

//    private String username;

    public String queryoutstock(){
        countNumed =outstockDao.queryRecordCount("select count(*) from TMeOutStockInfoEntity");
        outstocklist=outstockDao.queryForPage("from TMeOutStockInfoEntity",page,limit);
        success ="true";
        return SUCCESS;
    }

    public String outstockdetavalue(){
        outstockdetalist=outstockdetaDao.queryForPage("from TMeOutStockDetailsInfoEntity where outbillcode.outbillcode=?",0,0,outstock.getOutbillcode());
        countNumed=outstockdetalist.size();
        return SUCCESS;
    }

//    public String inStockDefault(){
//        merlist=merDao.listAll(TMeMerchandiseInfoEntity.class);
//        return SUCCESS;
//    }

//    public String userDefault(){
//        TAuOperInfoEntity dd;
//        dd= (TAuOperInfoEntity)session.getAttribute("loginValid");
//        username=dd.getOpername();
//        return SUCCESS;
//    }



    public String outstockadd(){
        defaultVauel(outstock);
        success="false";
        message="入库失败";
        try {
            boolean result=outstockfunction.instock(outstock,outstockdetalist);
            if(result){
                success="true";
                message="入库成功";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String delect(){
        success="false";
        message="更新失败";
        try {
            boolean result=outstockfunction.detele(outstock);
            if(result){
                success="true";
                message="更新成功";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return SUCCESS;
    }


    public String outstockupdate(){
        success="false";
        message="更新失败";
        TAuOperInfoEntity dd;
        dd= (TAuOperInfoEntity)session.getAttribute("loginValid");
        outstock.setOperid(dd);
        try {
            boolean result=outstockfunction.update(outstock,outstockdetalist);
            if(result){
                success="true";
                message="更新成功";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return SUCCESS;
    }
    public void defaultVauel(TMeOutStockInfoEntity ff){
        TMeOutStockInfoEntity defaultdel;
        TAuOperInfoEntity dd;
        dd= (TAuOperInfoEntity)session.getAttribute("loginValid");
        int defaultId = 0;
        defaultdel = new TMeOutStockInfoEntity();
        defaultId= outstockDao.queryRecordCount("select  MAX(id) from TMeOutStockInfoEntity");
        if(!(defaultId==0)){
            defaultdel.setId(defaultId);
            defaultdel= outstockDao.findByExample(TMeOutStockInfoEntity.class ,defaultdel).get(0);
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

            int id =defaultdel.getId();
            ff.setId(id+1);
            ff.setOutbillcode(defaults);
        }else{
            ff.setId(1);
            ff.setOutbillcode("00001");
        }
        TAuOperInfoEntity oo=new TAuOperInfoEntity();
        oo.setOperid(dd.getOperid());
        ff.setOperid(oo);
    }

//    public List<TMeMerchandiseInfoEntity> getMerlist() {
//        return merlist;
//    }

//    public void setMerlist(List<TMeMerchandiseInfoEntity> merlist) {
//        this.merlist = merlist;
//    }

//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }

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

    public int getCountNumed() {
        return countNumed;
    }

    public void setCountNumed(int countNumed) {
        this.countNumed = countNumed;
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

    public TMeOutStockInfoEntity getOutstock() {
        return outstock;
    }

    public void setOutstock(TMeOutStockInfoEntity outstock) {
        this.outstock = outstock;
    }

    public List<TMeOutStockInfoEntity> getOutstocklist() {
        return outstocklist;
    }

    public void setOutstocklist(List<TMeOutStockInfoEntity> outstocklist) {
        this.outstocklist = outstocklist;
    }

    public List<TMeOutStockDetailsInfoEntity> getOutstockdetalist() {
        return outstockdetalist;
    }

    public void setOutstockdetalist(List<TMeOutStockDetailsInfoEntity> outstockdetalist) {
        this.outstockdetalist = outstockdetalist;
    }
}

