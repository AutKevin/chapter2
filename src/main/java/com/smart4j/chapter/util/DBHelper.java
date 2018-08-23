package com.smart4j.chapter.util;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @program: DBHelper
 * @description: 数据库操作类
 **/
public class DBHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(DBHelper.class);

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

    /**
     * 获取数据库连接
     * @return
     */
    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();  //在catlina.out中打印
            LOGGER.error("get connection failure",e);
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     * @param conn
     */
    public static void closeConnection(Connection conn){
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                LOGGER.error("close connection failure",e);
            }
        }
    }

    private static final QueryRunner QUERY_RUNNER = new QueryRunner();

    /**
     * 查询实体列表
     * @param entityClass
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public static <T> List<T> queryEntityList(Class<T> entityClass,String sql,Object... params){
        List<T> entityList;
        Connection conn = getConnection();
        try {
            entityList = QUERY_RUNNER.query(conn,sql,new BeanListHandler<T>(entityClass),params);
        } catch (SQLException e) {
            //e.printStackTrace();
            LOGGER.error("query entity list failure",e);
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }
        return entityList;
    }

    /**
     * 查询实体
     * @param entityClass
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public static <T> T queryEntity(Class<T> entityClass,String sql,Object... params){
        T entity;
        Connection conn = getConnection();
        try {
            entity=QUERY_RUNNER.query(conn,sql,new BeanHandler<T>(entityClass),params);
        } catch (SQLException e) {
            //e.printStackTrace();
            LOGGER.error("query entity failure",e);
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }
        return entity;
    }

    /**
     * 多表查询，其中的Map表示列明与列值的映射关系
     * @param sql
     * @param params
     * @return
     */
    public static List<Map<String,Object>> executeQuery(String sql,Object... params){
        List<Map<String,Object>> result;
        Connection conn = getConnection();
        try {
            result = QUERY_RUNNER.query(conn,sql,new MapListHandler(),params);
        } catch (SQLException e) {
            //e.printStackTrace();
            LOGGER.error("execute query failure",e);
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 执行更新语句（包括update，insert，delete）
     * @param sql
     * @param params
     * @return
     */
    public static int executeUpdate(String sql,Object... params){
        int rows =0;
        Connection conn = getConnection();
        try {
            rows = QUERY_RUNNER.update(conn,sql,params);
        } catch (SQLException e) {
            //e.printStackTrace();
            LOGGER.error("execute update failure",e);
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }
        return rows;
    }

    /**
     * 插入实体（根据executeUpdate方法）
     * @param entityClass
     * @param fieldMap
     * @param <T>
     * @return
     */
    public static <T> boolean insertEntity(Class<T> entityClass,Map<String,Object> fieldMap){
        if (CollectionUtil.isEmpty(fieldMap)){
            LOGGER.error("can not insert entity: fieldMap is empty");
            return false;
        }

        String sql = "insert into "+getTableName(entityClass);
        StringBuilder columns = new StringBuilder("(");
        StringBuilder values = new StringBuilder("(");
        for (String fieldName:fieldMap.keySet()){
            columns.append(fieldName).append(", ");
            values.append("?, ");
        }
        columns.replace(columns.lastIndexOf(", "),columns.length(),")");
        values.replace(values.lastIndexOf(", "),values.length(),")");
        sql += columns+" VALUES" +values;
        Object[] params = fieldMap.values().toArray();
        return executeUpdate(sql,params)==1;
    }

    /**
     * 修改
     * @param entityClass
     * @param id
     * @param fieldMap
     * @param <T>
     * @return
     */
    public static <T> boolean updateEntity(Class<T> entityClass,long id,Map<String,Object> fieldMap){
        if (CollectionUtil.isEmpty(fieldMap)){
            LOGGER.error("can not update entity: fieldMap is empty");
            return false;
        }

        String sql = "update "+getTableName(entityClass) + " set ";
        StringBuilder columns = new StringBuilder();
        for (String fieldName:fieldMap.keySet()){
            columns.append(fieldName).append("=?, ");
        }
        sql+= columns.substring(0,columns.lastIndexOf(", "))+"where id=?";

        List<Object> paramList = new ArrayList<Object>();
        paramList.addAll(fieldMap.values());
        paramList.add(id);
        Object[] params = paramList.toArray();
        return executeUpdate(sql,params)==1;
    }

    /**
     * 删除
     * @param entityClass
     * @param id
     * @param <T>
     * @return
     */
    public static <T> boolean deleteEntity(Class<T> entityClass,long id){
        String sql = "delete from "+getTableName(entityClass)+" where id =?";
        return executeUpdate(sql,id)==1;
    }

    /**
     * 获取类名（不包含报名和后缀）
     * @param entityClass
     * @return
     */
    private static String getTableName(Class<?> entityClass){
        return entityClass.getSimpleName();
    }

    public static void main(String[] args) {
        System.out.println(getTableName(CastUtil.class));
    }
}
