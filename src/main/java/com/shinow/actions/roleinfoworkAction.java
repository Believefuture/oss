package com.shinow.actions;


import com.shinow.entity.TAuMenuInfoEntity;
import com.shinow.entity.TAuRoleInfoEntity;
import com.shinow.framework.action.BaseAction;
import com.shinow.framework.dao.BaseDAO;
import com.shinow.serverce.MenuDao;
import com.shinow.serverce.TreeNode;
import com.shinow.serverce.authorizationworkService;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2014/12/10.
 */
public class roleinfoworkAction extends BaseAction {


    private List<TAuRoleInfoEntity> rolelist;

    private TAuRoleInfoEntity role;
    private List<TAuMenuInfoEntity> arry;

    @Resource
    private BaseDAO<TAuRoleInfoEntity> roleDao;
    @Resource
    private authorizationworkService authservice;
    private String success;

    private String message;

    private int limit;

    private int page;

    private int countNumed;

    private TreeNode roletree;

    @Resource
    private MenuDao treefunctio;

    public String queryrolelist(){
        countNumed =roleDao.queryRecordCount("select count(*) from TAuRoleInfoEntity");
        rolelist=roleDao.queryForPage("from TAuRoleInfoEntity", page, limit);
        success ="true";
        return SUCCESS;
    }


    public String queryroletree(){
        roletree=treefunctio.queryroleModule(role.getRoleid());
        return SUCCESS;
    }

    public String treedefault(){
        roletree=treefunctio.queryModule();
        return SUCCESS;
    }

    public String defaultVauel(){
        int defaultId = 0;
        TAuRoleInfoEntity o = new TAuRoleInfoEntity();
        defaultId= roleDao.queryRecordCount("select  MAX(id) from TAuRoleInfoEntity");
        o.setId(defaultId);
        o= roleDao.findByExample(TAuRoleInfoEntity.class ,o).get(0);
        role.setId(defaultId+1);
        String defaults =o.getRoleid();
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
        role.setRoleid(defaults);
        return SUCCESS;
    }

    public String Addrole(){
        defaultVauel();
        try {
            Serializable o= authservice.inauth(role,arry);
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

    public List<TAuRoleInfoEntity> getRolelist() {
        return rolelist;
    }

    public void setRolelist(List<TAuRoleInfoEntity> rolelist) {
        this.rolelist = rolelist;
    }

    public TAuRoleInfoEntity getRole() {
        return role;
    }

    public void setRole(TAuRoleInfoEntity role) {
        this.role = role;
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

    public TreeNode getRoletree() {
        return roletree;
    }

    public void setRoletree(TreeNode roletree) {
        this.roletree = roletree;
    }

    public List<TAuMenuInfoEntity> getArry() {
        return arry;
    }

    public void setArry(List<TAuMenuInfoEntity> arry) {
        this.arry = arry;
    }
}
