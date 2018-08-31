package org.smart4j.chapter2.controller;/**
 * Created by Administrator on 2018/8/31.
 */

import com.smart4j.chapter2.model.Customer;
import com.smart4j.chapter2.service.CustomerService;

import javax.swing.text.View;
import javax.xml.ws.Action;
import java.util.List;

/**
 * @program: CustomerController
 * @description: 处理客户管理相关请求
 * @author: qiuyu
 * @create: 2018-08-31 22:06
 * 功能开发中
 **/
@Controller
public class CustomerController {

    private CustomerService customerService;

    /**
     * 进入客户端界面
     */
    @Action("get:/customer")
    public View index(){
        List<Customer> customerList = customerService.getCustomerList();
        return new View("customer.jsp").addModel("customerList",customerList);
    }

    /**
     * 显示客户基本信息
     */
    @Action("get:/customer_show")
    public View show(Param param){
        long id = param.getLong(id);
        Customer customer = customerService.getCustomer(id);
        return new View("customer_show.jsp").addModel("customer",customer);
    }

    /**
     * 删除客户信息
     * @param param
     * @return
     */
    @Action("delete:/customer_edit")
    public Data delete(Param param){
        long id = param.getLong(id);
        boolean result = customerService.deleteCustomer(id);
        return new Data(result);
    }
}
