package com.shinow.actions;


import com.shinow.entity.TAuOperInfoEntity;
import com.shinow.entity.TMeInStockDetailsInfoEntity;
import com.shinow.entity.TMeInStockInfoEntity;
import com.shinow.entity.TMeMerchandiseInfoEntity;
import com.shinow.framework.action.BaseAction;
import com.shinow.framework.dao.BaseDAO;
import com.shinow.serverce.InStockInfoInsertService;
import com.sun.net.httpserver.Authenticator;
import org.omg.PortableInterceptor.SUCCESSFUL;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2014/11/24.
 */
public class InStockInfoWorkAction extends BaseAction{

    @Resource
    private BaseDAO<TMeMerchandiseInfoEntity> merDao;

    @Resource
    private BaseDAO<TMeInStockDetailsInfoEntity> instockdetaDao;

    @Resource
    private BaseDAO<TMeInStockInfoEntity> instockDao;

    @Resource
    private InStockInfoInsertService instockfunction;

    private List<TMeMerchandiseInfoEntity> merlist;

    private TMeInStockInfoEntity instockinfo;

    private List<TMeInStockInfoEntity> instockinfolist;

    private List<TMeInStockDetailsInfoEntity> instockdetalist;

    private String success;

    private String message;

    private int limit;

    private int page;

    private int countNumed;

    private String username;

    public String queryStockInfo(){
        countNumed =instockDao.queryRecordCount("select count(*) from TMeInStockInfoEntity");
        instockinfolist=instockDao.queryForPage("from TMeInStockInfoEntity",page,limit);
        success ="true";
        return SUCCESS;
    }

    public String dataderaultvalue(){
        TMeInStockDetailsInfoEntity tt = new TMeInStockDetailsInfoEntity();
        tt.setBillcode(instockinfo);
        instockdetalist=instockdetaDao.queryForPage("from TMeInStockDetailsInfoEntity where billcode.billcode=?",0,0,instockinfo.getBillcode());
//        instockdetalist=instockdetaDao.findByExample(TMeInStockDetailsInfoEntity.class,tt);
        countNumed=instockdetalist.size();
        return SUCCESS;
    }

    public String inStockDefault(){
        merlist=merDao.listAll(TMeMerchandiseInfoEntity.class);
        return SUCCESS;
    }

    public String userDefault(){
        TAuOperInfoEntity dd;
        dd= (TAuOperInfoEntity)session.getAttribute("loginValid");
        username=dd.getOpername();
        return SUCCESS;
    }



    public String instockinfoadd(){
        defaultVauel(instockinfo);
        success="false";
        message="入库失败";
        try {
            boolean result=instockfunction.instock(instockinfo,instockdetalist);
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
            boolean result=instockfunction.detele(instockinfo);
            if(result){
                success="true";
                message="更新成功";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return SUCCESS;
    }


    public String instockinfoupdate(){
        success="false";
        message="更新失败";
        TAuOperInfoEntity dd;
        dd= (TAuOperInfoEntity)session.getAttribute("loginValid");
        instockinfo.setOperid(dd);
        try {
            boolean result=instockfunction.update(instockinfo,instockdetalist);
            if(result){
                success="true";
                message="更新成功";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return SUCCESS;
    }
    public void defaultVauel(TMeInStockInfoEntity ff){
        TMeInStockInfoEntity defaultdel;
        TAuOperInfoEntity dd;
        dd= (TAuOperInfoEntity)session.getAttribute("loginValid");
        int defaultId = 0;
        defaultdel = new TMeInStockInfoEntity();
        defaultId= instockDao.queryRecordCount("select  MAX(id) from TMeInStockInfoEntity");
        defaultdel.setId(defaultId);
        defaultdel= instockDao.findByExample(TMeInStockInfoEntity.class ,defaultdel).get(0);
        defaultdel.setId(defaultId+2);
        String defaults =defaultdel.getBillcode();
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
        ff.setBillcode(defaults);
        TAuOperInfoEntity oo=new TAuOperInfoEntity();
        oo.setOperid(dd.getOperid());
        ff.setOperid(oo);
    }

    public List<TMeMerchandiseInfoEntity> getMerlist() {
        return merlist;
    }

    public void setMerlist(List<TMeMerchandiseInfoEntity> merlist) {
        this.merlist = merlist;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public TMeInStockInfoEntity getInstockinfo() {
        return instockinfo;
    }

    public void setInstockinfo(TMeInStockInfoEntity instockinfo) {
        this.instockinfo = instockinfo;
    }

    public List<TMeInStockDetailsInfoEntity> getInstockdetalist() {
        return instockdetalist;
    }

    public void setInstockdetalist(List<TMeInStockDetailsInfoEntity> instockdetalist) {
        this.instockdetalist = instockdetalist;
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

    public List<TMeInStockInfoEntity> getInstockinfolist() {
        return instockinfolist;
    }

    public void setInstockinfolist(List<TMeInStockInfoEntity> instockinfolist) {
        this.instockinfolist = instockinfolist;
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
}

