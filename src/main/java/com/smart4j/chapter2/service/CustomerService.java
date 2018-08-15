package com.smart4j.chapter2.service;/**
 * Created by Administrator on 2018/8/13.
 */

import com.smart4j.chapter.util.PropsUtil;
import com.smart4j.chapter2.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @program: chapter2->CustomerService
 * @description: 客户服务层
 * @author: qiuyu
 * @create: 2018-08-13 20:15
 **/
public class CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    /**
     * 静态代码块在类加载时运行
     */
    static{
        Properties conf = PropsUtil.loadProps("config.properties");
        DRIVER = conf.getProperty("jdbc.driver");
        URL = conf.getProperty("jdbc.url");
        USERNAME = conf.getProperty("jdbc.username");
        PASSWORD = conf.getProperty("jdbc.password");

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
            LOGGER.error("can not load jdbc driver",e);
        }
    }

    public static void main(String[] args) {
        new CustomerService().getCustomerList("");
    }

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
            conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
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
            if (conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOGGER.error("close connection failure",e);
                }
            }
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
