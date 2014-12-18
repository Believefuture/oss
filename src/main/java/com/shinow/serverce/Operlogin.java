package com.shinow.serverce;

import com.shinow.entity.TAuOperInfoEntity;
import com.shinow.framework.dao.BaseDAO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * Created by Administrator on 2014-11-06.
 */
@Service
public class Operlogin {
    @Resource
    private BaseDAO<TAuOperInfoEntity> menuInfoDao;
    private TAuOperInfoEntity operInfo;
    private Md5 md;
    public boolean login(TAuOperInfoEntity oper){
        boolean cade = false;
        md = new Md5();
        if((null!=oper.getOpername())&&(null!=oper.getPwd())){
            operInfo = menuInfoDao.queryOne("from TAuOperInfoEntity where operName=?", oper.getOpername());
            if (null != operInfo) {
                String passWord = md.switchcode(oper.getPwd());
                System.out.print(passWord);
                if (passWord.equals(operInfo.getPwd())) {
                    cade = true;
                    return cade;
                }
            }
        }
        return cade;
    }

    public TAuOperInfoEntity queryUser(TAuOperInfoEntity oper){
        operInfo = menuInfoDao.queryOne("from TAuOperInfoEntity where operName=?", oper.getOpername());
        return operInfo;
    }
}
