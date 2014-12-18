package com.shinow.serverce;

import com.shinow.entity.*;

import com.shinow.framework.dao.BaseDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2014/12/14.
 */
@Service
public class authorizationworkService {
    @Resource
    private BaseDAO<TAuRoleInfoEntity> roleDao;
    @Resource
    private BaseDAO<TAuAuthorizationEntity> authDao;
    @Transactional
    public boolean inauth(TAuRoleInfoEntity instockinfo,List<TAuMenuInfoEntity> instockdeta_list){
        TAuAuthorizationEntity auth;
        boolean result=false;
        if(instockinfo!=null&instockdeta_list!=null) {
            String uuid = (String) roleDao.insert(instockinfo);
            for (TAuMenuInfoEntity in : instockdeta_list) {
                auth = new TAuAuthorizationEntity();
                auth.setRoleid(instockinfo);
                auth.setMenuid(in);
                authDao.insert(auth);
            }
            result = true;
        }
        return result;
    }

}
