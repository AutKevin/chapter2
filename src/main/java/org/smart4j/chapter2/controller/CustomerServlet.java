package org.smart4j.chapter2.controller;/**
 * Created by Administrator on 2018/8/12.
 */

import com.smart4j.chapter2.model.Customer;
import com.smart4j.chapter2.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @description: 进入客户列表界面
 **/
@WebServlet("/customer")
public class CustomerServlet extends HttpServlet{
    private CustomerService service;

    @Override
    public void init() throws ServletException {
        super.init();
        service = new CustomerService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> customerList = service.getCustomerList("");
        req.setAttribute("customerList",customerList);
        req.getRequestDispatcher("/WEB-INF/view/customer.jsp").forward(req,resp);
    }
}
