package com.shinow.actions;

import com.shinow.entity.TAuOperInfoEntity;
import com.shinow.framework.action.BaseAction;
import com.shinow.serverce.MenuDao;
import com.shinow.serverce.Operlogin;
import com.shinow.serverce.TreeNode;
import com.sun.net.httpserver.Authenticator;

import javax.annotation.Resource;
import java.awt.*;
import java.util.List;

/**
 * Created by Administrator on 2014-11-07.
 */
public class OpLoginAction extends BaseAction {

    private TAuOperInfoEntity user;
    @Resource
    private Operlogin oplogin;

    private String validCode;

    private String success;

    private TreeNode tree;

    @Resource
    private MenuDao treefunctio;

    private boolean valid;

    private String message;

    private String jsonUrl;

    public String login(){
        success="false";
        valid = false;
        message ="请输入验证码";
        if(null !=validCode&&(validCode.trim().length()>1)){
           String i = (String)session.getAttribute("rand");
            if(validCode.equals(i)){
                valid=oplogin.login(user);
                if(valid){
                    success="true";
                    message="成功";
                    user=oplogin.queryUser(user);
                    session.setAttribute("loginValid",user);
                    return SUCCESS;
                }
                message="请输入正确的用户名或密码";
                return SUCCESS;
            }
            message ="验证码不正确请重新输入";

        }
        return SUCCESS;
    }

    public String loginSucceed(){
        user = (TAuOperInfoEntity)session.getAttribute("loginValid");
        if(user==null){
            return INPUT;
        }
        return SUCCESS;
    }

    public String querymenu(){
        user = (TAuOperInfoEntity)session.getAttribute("loginValid");
        tree =treefunctio.queryModule(user.getRoleid().getRoleid());
        success="true";
        return SUCCESS;
    }
    public TAuOperInfoEntity getUser() {
        return user;
    }

    public void setUser(TAuOperInfoEntity user) {
        this.user = user;
    }

    public String getValidCode() {
        return validCode;
    }

    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
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

    public String getJsonUrl() {
        return jsonUrl;
    }

    public void setJsonUrl(String jsonUrl) {
        this.jsonUrl = jsonUrl;
    }

    public TreeNode getTree() {
        return tree;
    }

    public void setTree(TreeNode tree) {
        this.tree = tree;
    }
}
