package com.shinow.serverce;

import com.shinow.entity.TAuMenuInfoEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2014/12/9.
 */
@Repository
public class MenuDao {
    @Resource
    private SessionFactory sessionFactory;
    private void querySubModule(TreeNode parentNode){
        Session session=sessionFactory.openSession();
        String hql="from TAuMenuInfoEntity s where s.sortId=?";
        Query query=session.createQuery(hql);
        query.setParameter(0, parentNode.getMenuinfoentity().getMenuid());
        List<TAuMenuInfoEntity> moduleList=query.list();
        session.close();
        for (TAuMenuInfoEntity module:moduleList){
            TreeNode node=new TreeNode();
            node.setMenuinfoentity(module);
            parentNode.addChild(node);
            querySubModule(node);
        }
    }

    @Transactional
    public TreeNode queryModule(String raleId){
        TreeNode result = new TreeNode();
        Session session  = sessionFactory.openSession();
        String sql = "SELECT c.* from TAu_RoleInfo a INNER JOIN TAu_Authorization b on a.RoleID = b.RoleID INNER JOIN TAu_MenuInfo c ON c.MenuID = b.MenuID where  a.RoleID = ? and c.sortid is null ";
        try{
            Query query = session.createSQLQuery(sql).addEntity(TAuMenuInfoEntity.class);
            query.setParameter(0,raleId);
            List<TAuMenuInfoEntity> menuinfoEntities = query.list();
            session.close();
            for(TAuMenuInfoEntity menuinfoEntity:menuinfoEntities){
                TreeNode node = new TreeNode();
                node.setMenuinfoentity(menuinfoEntity);
                result.addChild(node);
                querySubModule(node);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    @Transactional
    public TreeNode queryModule(){
        TreeNode result = new TreeNode();
        Session session  = sessionFactory.openSession();
        String sql = "SELECT c.* from TAu_MenuInfo c where c.sortid is null";
        try{
            Query query = session.createSQLQuery(sql).addEntity(TAuMenuInfoEntity.class);
            List<TAuMenuInfoEntity> menuinfoEntities = query.list();
            session.close();
            for(TAuMenuInfoEntity menuinfoEntity:menuinfoEntities){
                TreeNode node = new TreeNode();
                node.setMenuinfoentity(menuinfoEntity);
                result.addChild(node);
                querySubModule(node);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
//    private void queryroleSubModule(TreeNode parentNode){
//        Session session=sessionFactory.openSession();
//        String sql="select s.* from tau_menuinfo as s INNER JOIN tau_authorization b on s.menuid=b.menuid inner join tau_roleinfo c on c.roleid=b.roleid where s.sortid=? ";
//        Query query=session.createSQLQuery(sql).addEntity(TAuMenuInfoEntity.class);
//        query.setParameter(0, parentNode.getMenuinfoentity().getMenuid());
//        List<TAuMenuInfoEntity> moduleList=query.list();
//        session.close();
//        for (TAuMenuInfoEntity module:moduleList){
//            TreeNode node=new TreeNode();
//            node.setMenuinfoentity(module);
//            parentNode.addChild(node);
//            querySubModule(node);
//        }
//    }
    private void queryroleSubModule(TreeNode parentNode,String raleId){
        Session session=sessionFactory.openSession();
        String sql="select s.* from tau_menuinfo as s INNER JOIN tau_authorization b on s.menuid=b.menuid inner join tau_roleinfo c on c.roleid=b.roleid where s.sortid=? and c.roleid=?";
        Query query=session.createSQLQuery(sql).addEntity(TAuMenuInfoEntity.class);
        query.setParameter(0, parentNode.getMenuinfoentity().getMenuid());
        query.setParameter(1,raleId);
        List<TAuMenuInfoEntity> moduleList=query.list();
        session.close();
        for (TAuMenuInfoEntity module:moduleList){
            TreeNode node=new TreeNode();
            node.setMenuinfoentity(module);
            parentNode.addChild(node);
            querySubModule(node);
        }
    }

    @Transactional
    public TreeNode queryroleModule(String raleId){
        TreeNode result = new TreeNode();
        Session session  = sessionFactory.openSession();
        String sql = "select a.* from tau_menuinfo as a INNER JOIN tau_authorization b on a.menuid=b.menuid where b.roleid=? and a.sortid is null ";
        try{
            Query query = session.createSQLQuery(sql).addEntity(TAuMenuInfoEntity.class);
            query.setParameter(0,raleId);
            List<TAuMenuInfoEntity> menuinfoEntities = query.list();
            session.close();
            for(TAuMenuInfoEntity menuinfoEntity:menuinfoEntities){
                TreeNode node = new TreeNode();
                node.setMenuinfoentity(menuinfoEntity);
                result.addChild(node);
                queryroleSubModule(node,raleId);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
