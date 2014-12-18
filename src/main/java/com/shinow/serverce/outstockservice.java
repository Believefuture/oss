//package com.shinow.serverce;
//
//import com.shinowit.dao.BaseDAO;
//
//import com.shinowit.entity.TMeOutStockDetailsInfoEntity;
//import com.shinowit.entity.TMeOutStockInfoEntity;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.annotation.Resource;
//import java.util.List;
//
///**
// * Created by Administrator on 2014-11-19.
// */
//@Repository
//public class outstockservice {
//    @Resource
//    private BaseDAO<TMeOutStockDetailsInfoEntity> dao1;
//    @Resource
//    private BaseDAO<TMeOutStockInfoEntity> dao2;
//    @Transactional
//    public boolean insert ( TMeOutStockInfoEntity a,List<TMeOutStockDetailsInfoEntity> b) {
//        boolean result = false;
//        try{
//            dao2.insert(a);
//            for(TMeOutStockDetailsInfoEntity c:b){
//                c.setMeOutStockInfoByOutBillCode(a);
//                dao1.insert(c);
//            }
//            result=true;
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        return result;
//
//    }
//    @Transactional
//    public boolean detele (TMeOutStockInfoEntity a) {
//        List<TMeOutStockDetailsInfoEntity> details;
//        boolean result = false;
//        try{
//            details=dao1.findByHql1("from TMeOutStockDetailsInfoEntity  where meOutStockInfoByOutBillCode.outBillCode=? " ,a.getOutBillCode());
//            for(TMeOutStockDetailsInfoEntity aa:details){
//                dao1.delete(aa);
//            }
//
//            dao2.delete(dao2.findById(TMeOutStockInfoEntity.class,a.getOutBillCode())) ;
//            result=true;
//
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        return result;
//
//    }
//    @Transactional
//    public boolean update ( TMeOutStockInfoEntity a,TMeOutStockDetailsInfoEntity b) {
//        boolean result = false;
//        try{
//            dao1.update(b);
//            dao2.update(a);
//            result=true;
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        return result;
//
//    }
//
//}
//
