package org.smart4j.chapter2.controller;/**
 * Created by Administrator on 2018/8/12.
 */

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: chapter2->CustomerCreateServlet
 * @description: 新增类
 * @author: qiuyu
 * @create: 2018-08-12 23:17
 **/
@WebServlet("/customer_create")
public class CustomerCreateServlet extends HttpServlet{
    /** 
    * @Description:  进入到新增界面
    * @Param: [req, resp] 
    * @return: void 
    * @Author: qiuyu
    * @Date: 2018/8/12 
    */ 
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
    /** 
    * @Description: 新增客户
    * @Param: [req, resp] 
    * @return: void 
    * @Author: qiuyu
    * @Date: 2018/8/12 
    */ 
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
