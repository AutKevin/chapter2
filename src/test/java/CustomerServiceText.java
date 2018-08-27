/**
 * Created by Administrator on 2018/8/13.
 */

import com.smart4j.chapter.util.DBHelper;
import com.smart4j.chapter2.model.Customer;
import com.smart4j.chapter2.service.CustomerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: chapter2->CustomerServiceText
 * @description: 客户测试类
 * @author: qiuyu
 * @create: 2018-08-13 20:24
 **/
public class CustomerServiceText {
    private final CustomerService customerService;

    public CustomerServiceText() {
        customerService = new CustomerService();
    }

    @Before
   public void init() throws IOException {
        DBHelper.executeSqlFile("sql/customer_init.sql");
   }

   @Test
   public void getCustomerListText(){

       List<Customer> customerList =customerService.getCustomerList("");
       Assert.assertEquals(2,customerList.size());
   }
   @Test
   public void getCustomerTest(){
       long id =5;
       Customer customer = customerService.getCustomer(id);
       Assert.assertNotNull(customer);
   }
   @Test
   public void createCustomerTest(){
       Map<String,Object> fieldMap = new HashMap<String, Object>();
       fieldMap.put("name","小猪佩琪");
       fieldMap.put("contact","John");
       fieldMap.put("telephone","18151449650");
       boolean result = customerService.createCustomer(fieldMap);
       Assert.assertTrue(result);
   }

   @Test
   public void updateCustomerTest(){
       long id =5;
       Map<String,Object> fieldMap = new HashMap<String,Object>();
       fieldMap.put("contact","Aeolian");
       boolean result = customerService.updateCustomer(id,fieldMap);
       Assert.assertTrue(result);
   }

   @Test
   public void deleteCustomer(){
       long id =5;
       boolean result = customerService.deleteCustomer(id);
       Assert.assertTrue(result);
   }
}
