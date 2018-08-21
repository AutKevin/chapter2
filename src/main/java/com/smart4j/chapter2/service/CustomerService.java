package com.smart4j.chapter2.service;

import com.smart4j.chapter.util.DBHelper;
import com.smart4j.chapter2.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: chapter2->CustomerService
 * @description: 客户服务层
 * @author: qiuyu
 * @create: 2018-08-13 20:15
 **/
public class CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    /** 
    * @Description: 获取客户列表
    * @Param: [] 
    * @return: java.util.List<com.smart4j.chapter2.model.Customer> 
    * @Author: qiuyu
    * @Date: 2018/8/13 
    */ 
    public List<Customer> getCustomerList(String keyWord){
        Connection conn = null;
        try {
            List<Customer> customerList = new ArrayList<Customer>();
            String sql = "select * from customer";
            conn = DBHelper.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Customer customer = new Customer();
                customer.setId(resultSet.getLong("id"));
                customer.setName(resultSet.getString("name"));
                customer.setContact(resultSet.getString("contact"));
                customer.setTelephone(resultSet.getString("telephone"));
                customer.setEmail(resultSet.getString("email"));
                customer.setRemark(resultSet.getString("remark"));
                customerList.add(customer);
            }
            return customerList;
        } catch (SQLException e) {
            //e.printStackTrace();
            LOGGER.error("execute sql failure",e);
        }finally {
            DBHelper.closeConnection(conn);
        }
        return null;
    }

    /** 
    * @Description: 根据id获取客户
    * @Param: [id] 
    * @return: com.smart4j.chapter2.model.Customer 
    * @Author: qiuyu
    * @Date: 2018/8/13 
    */ 
    public Customer getCustomer(long id){
        //todo
        return null;
    }
    /**
    * @Description:  创建客户
    * @Param: [fieldMap]
    * @return: boolean
    * @Author: qiuyu
    * @Date: 2018/8/13
    */
    public boolean createCustomer(Map<String,Object> fieldMap){
        //todo
        return false;
    }

    /** 
    * @Description: 更新客户
    * @Param: [] 
    * @return: boolean 
    * @Author: qiuyu
    * @Date: 2018/8/13 
    */ 
    public boolean updateCustomer(long id,Map<String,Object> fieldMap){
        //todo
        return false;
    }

    /** 
    * @Description: 删除客户
    * @Param: [] 
    * @return: boolean 
    * @Author: qiuyu
    * @Date: 2018/8/13 
    */ 
    public boolean deleteCustomer(long id){
        //todo
        return false;
    }
    
}
