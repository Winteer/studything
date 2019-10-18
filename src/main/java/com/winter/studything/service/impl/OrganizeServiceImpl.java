package com.winter.studything.service.impl;

import com.winter.studything.Entity.BookInfo;
import com.winter.studything.Entity.DepartInfo;
import com.winter.studything.Entity.PersonInfo;
import com.winter.studything.dao.OrganizeDao;
import com.winter.studything.service.OrganizeService;
import com.winter.studything.utils.SqlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrganizeServiceImpl implements OrganizeService {

    @Autowired
    OrganizeDao BookInfoDao;
    private String BookInfo_TAB = "book_info"; //预定信息表表名

    private String User_TAB = "ach_sys_users"; //用户表
    private String Part_TAB = "ach_sys_deptinfo"; //组织部门表

    private static String photoPath = "http://39.104.75.19";

//    @Override
//    public Map<String,Object> getCount(){
//        Map<String,Object> retMap = new HashMap<>();
//        retMap = BookInfoDao.getCount();
//        return retMap;
//    }


    @Override
    public void deleteByID(String id){
        BookInfoDao.deleteByID(id);
    }


    @Override
    public int insertBookInfo(PersonInfo personInfo){
        int flag = BookInfoDao.insertBookInfo(personInfo);
        return flag;
    }

    @Override
    public Map<String, Object> getInfoByID(PersonInfo personInfo) {
        Map<String, Object> map = new HashMap<>();
        List<Map<String,Object>> deptList = new ArrayList<>();
        deptList = getAllCompany();
        String sql = "select serial_no,user_name,user_photo,user_code,user_cookie,user_mob,company_code,user_roles,user_post,user_flag from "+User_TAB+" where serial_no ='" + personInfo.getSerial_no()+"'";
//        String sql = "select user.serial_no,user.user_name,user.user_photo,user.user_code,user.user_cookie,user.user_mob,user.company_code,dept.dept_name as company_name,user.user_roles,user.user_post,user.user_flag from ach_sys_users user LEFT JOIN ach_sys_deptinfo dept ON user.company_code = dept.serial_no where user.serial_no ='" + personInfo.getSerial_no()+"'";
        map = BookInfoDao.getInfoByID(sql);
        for(Map<String,Object> deptMap : deptList){
            if(map.get("company_code").equals(deptMap.get("serial_no"))){
                map.put("company_name",deptMap.get("dept_name"));
            }
            if(map.get("user_roles").equals(deptMap.get("serial_no"))){
                map.put("user_dept",deptMap.get("dept_name"));
            }
        }
        return map;
    }

    @Override
    public Map<String, Object> getDepartInfoByID(DepartInfo departInfo) {
        Map<String, Object> map = new HashMap<>();
        String sql = "select serial_no,parent_id,dept_name,dept_type  from "+Part_TAB+" where serial_no ='" + departInfo.getSerial_no()+"'";
        map = BookInfoDao.getInfoByID(sql);
        return map;
    }

    @Override
    public List<Map<String, Object>> getAllCompany() {
        List<Map<String, Object>> retList = new ArrayList<>();
        String sql = "select serial_no,dept_name,dept_type  from "+Part_TAB;
        retList = BookInfoDao.queryForMapList(sql);
        return retList;
    }

    @Override
    public List<Map<String, Object>> getDpartByComp(String companyCode) {
        List<Map<String, Object>> retList = new ArrayList<>();
        String sql = "select serial_no,dept_name,dept_type  from "+Part_TAB +" where parent_id = '"+companyCode +"' and dept_type = '2'";
        retList = BookInfoDao.queryForMapList(sql);
        return retList;
    }

    @Override
    public int updateForm(PersonInfo personInfo){
        int flag = -1;
        String sql = "";
        Map<String,Object> beanMap = new HashMap<>();
        beanMap = SqlUtils.BeanToMap(personInfo);
        if(!beanMap.isEmpty()){
            sql = SqlUtils.makeUpdateSql(User_TAB,beanMap,"serial_no");
            flag = BookInfoDao.updateForm(sql);
        }
        return flag;
    }

    @Override
    public int updateDepart(DepartInfo departInfo){
        int flag = -1;
        String sql = "";
        Map<String,Object> beanMap = new HashMap<>();
        beanMap = SqlUtils.BeanToMap(departInfo);
        beanMap.remove("parent_id");
        if(!beanMap.isEmpty()){
            sql = SqlUtils.makeUpdateSql(Part_TAB,beanMap,"serial_no");
            flag = BookInfoDao.updateForm(sql);
        }
        return flag;
    }

    @Override
    public int addDepart(DepartInfo departInfo){
        int flag = -1;
        String sql = "";
        Map<String,Object> beanMap = new HashMap<>();
        beanMap = SqlUtils.BeanToMap(departInfo);
        if(!beanMap.isEmpty()){
//            sql = SqlUtils.makeUpdateSql(Part_TAB,beanMap,"serial_no");
            sql = SqlUtils.makeInsertSql(Part_TAB,beanMap,"");
            flag = BookInfoDao.updateForm(sql);
        }
        return flag;
    }

    @Override
    public int getCount(String companyCode){
        Map<String,Object> idMap = new HashMap<>();
        List<Map<String, Object>>idList = new ArrayList<>();
        String idStr = "";
        idMap.put("id",companyCode);
        idList = getAllSonDeptId(idMap);
        for(Map<String,Object> tmpMap : idList){
            idStr = idStr + "'" +tmpMap.get("id")+"',";
        }
        idStr = idStr.substring(0,idStr.length()-1);
        String sql = "";
        sql = "select count(*) as count from "+ User_TAB+" where (company_code in(" + idStr +") or user_roles in(" + idStr +")) and user_flag = 0";
//        sql = SqlUtils.makeCountSql(User_TAB,searchWord,date,"phone","room","pay_mode");
        return BookInfoDao.getCount(sql);
    }

    @Override
    public List<Map<String, Object>> getInfoByPage(String companyCode,String sortColumn,String sortMethod,int pageNum,int pageSize){
        Map<String,Object> idMap = new HashMap<>();
        List<Map<String, Object>>tmpList = new ArrayList<>();
        List<Map<String, Object>>retList = new ArrayList<>();
        List<Map<String, Object>>idList = new ArrayList<>();
        String idStr = "";
        idMap.put("id",companyCode);
        idList = getAllSonDeptId(idMap);
        for(Map<String,Object> tmpMap : idList){
            idStr = idStr + "'" +tmpMap.get("id")+"',";
        }
        idStr = idStr.substring(0,idStr.length()-1);
        String sql = "select serial_no,user_name,user_photo,user_code,user_cookie,user_mob,company_code,user_roles,user_post,user_flag,sort from "+User_TAB+" where (company_code in(" + idStr +") or user_roles in(" + idStr +")) and user_flag = 0";
        if("".equals(sortMethod) || "undefined".equals(sortMethod)){ //无排序时，默认按照创建时间逆序排列
            sortMethod = "desc";
            sortColumn = "user_post";
        }else if("descending".equals(sortMethod)){
            sortMethod = "desc";
        }else if("ascending".equals(sortMethod)){
            sortMethod = "asc";
        }
//        sql = sql + SqlUtils.makeSelectWhereSql(searchWord,current_date,"phone","room","pay_mode") + " order by "+sortColumn+" "+sortMethod+" limit "+(pageNum-1)*pageSize +","+pageSize;
        sql = sql + " order by sort asc limit "+(pageNum-1)*pageSize +","+pageSize;
        System.out.println(sql);

        sql = "select com.serial_no,com.user_name,com.user_photo,com.user_code,com.user_cookie,com.user_mob,com.company_code,dept1.dept_name as user_roles,com.user_post,com.user_flag from("
                +"select user.serial_no,user.user_name,user.user_photo,user.user_code,user.user_cookie,user.user_mob,dept.dept_name as company_code,user.user_roles,user.user_post,user.user_flag,user.sort from ("
                +sql
                +") user LEFT JOIN ach_sys_deptinfo dept ON user.company_code = dept.serial_no ) com LEFT JOIN ach_sys_deptinfo dept1 ON com.user_roles = dept1.serial_no order by com.sort asc";
        tmpList = BookInfoDao.getInfoByPage(sql);
        //修改照片路径
        for(Map<String,Object> tmpMap : tmpList){
            if(tmpMap.get("user_photo")!=null && !"".equals(tmpMap.get("user_photo").toString())){
               tmpMap.put("user_photo",photoPath+tmpMap.get("user_photo"));
            }
            retList.add(tmpMap);
        }
        return retList;
    }

    /**
     * 获取组织层级机构信息
     * @return
     */
    @Override
    public Map<String, Object> getDepartInfo(){
        Map<String, Object> retMap = new HashMap<>();
        List<Map<String, Object>> retList = new ArrayList<>();
        Map<String, Object> nodeMap = BookInfoDao.getHeadDepart();
        nodeMap.put("icon","el-icon-star-on");
        retMap = getDepartInfoUtils(nodeMap);
        return retMap;
    }


    /**
     * 获取该id下所有的子部门id集合
     * @param idMap
     * @return
     */
    public List<Map<String, Object>> getAllSonDeptId(Map<String,Object> idMap){
        List<Map<String, Object>>tmpList = new ArrayList<>();
        List<Map<String, Object>>retList = new ArrayList<>();
        String sql = "select serial_no as id  from ach_sys_deptinfo where parent_id ='"+idMap.get("id")+"'";
        tmpList = BookInfoDao.queryForMapList(sql);
        if(tmpList.size() > 0){
            for(Map<String,Object> map : tmpList){
                retList.addAll(getAllSonDeptId(map));
            }
        }
        retList.add(idMap);
        return retList;
    }

    /**
     * 根据传入的节点递归按照规则查询所有子节点信息
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> getDepartInfoUtils(Map<String,Object> map){
        Map<String, Object> retMap = new HashMap<>();
        List<Map<String, Object>> tmpList = new ArrayList<>();
        tmpList = BookInfoDao.getNextDepartInfo(map);
        if(tmpList.size() > 0){ //判断是否有子节点 ，有子节点则继续递归
            List<Map<String, Object>> childList = new ArrayList<>();
            for(Map<String, Object> tmpMap : tmpList){
                if("2".equals(tmpMap.get("dept_type"))){
                    tmpMap.put("icon","el-icon-s-flag");
                }else{
                    tmpMap.put("icon","el-icon-s-home");
                }
                tmpMap.remove("dept_type");
                childList.add(getDepartInfoUtils(tmpMap));
            }
            map.put("children",childList);
        }else{  //无子节点则返回自身
            retMap = map;
        }
        return map;
    }

}
