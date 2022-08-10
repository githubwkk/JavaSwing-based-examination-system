package c;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.beanutils.ConvertUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//改进的DBUtils：1.所有的数据库操作都使用了连接池技术 2.查询记录返回值中，不再需要强制转换
public class DBUtils {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
    String user = "root";
    String psw = "WKK123456";

    public void close() {
        try {
            if (resultSet != null && !resultSet.isClosed())
                resultSet.close();
            if (statement != null && !statement.isClosed())
                statement.close();
            if (connection != null && !connection.isClosed())
                connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //通过连接池获取connection对象
    public Connection getConnection() {
        Connection connection = null;
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        try {
            cpds.setDriverClass(driver);
            cpds.setJdbcUrl(url);
            cpds.setUser(user);
            cpds.setPassword(psw);
            //最大线程池中连接对象10
            cpds.setMaxPoolSize(10);
            cpds.setMinPoolSize(1);
            connection = cpds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public int executeUpdate(String sql) {
        int result = 0;
        try {
            Class.forName(driver);//加载驱动
            connection = DriverManager.getConnection(url, user, psw);//打开数据库，返回连接数据库对象
            statement = connection.createStatement();//获取执行sql命令的对象
            result = statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }

    public ResultSet executeQueryResultSet(String sql) {
        try {
            Class.forName(driver);//加载驱动
            connection = DriverManager.getConnection(url, user, psw);//打开数据库，返回连接数据库对象
            statement = connection.createStatement();//获取执行sql命令的对象
            resultSet = statement.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    //查找单条记录，封装在对象中
    public <T> T executeQueryBean(String sql, Class<T> clzss) {
        T object = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, psw);
//            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            if (resultSet.next()) {
                object = clzss.newInstance();
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    String methodName = resultSetMetaData.getColumnName(i);
                    Field field = clzss.getDeclaredField(methodName);
                    methodName = "set" + methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
                    Method method = clzss.getMethod(methodName, field.getType());
                    method.invoke(object, ConvertUtils.convert(resultSet.getString(i), field.getType()));//将数据库中的值的类型转换成类方法中的形参类型
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    //查找多条记录，封装在列表中
    public <T> List<T> executeQueryBeans(String sql, Class<T> clzss) {
        List<T> list = new ArrayList<>();
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, psw);
//            connection=getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            while (resultSet.next()) {
                T object = clzss.newInstance();
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    String methodName = resultSetMetaData.getColumnName(i);
                    Field field = clzss.getDeclaredField(methodName);
                    methodName = "set" + methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
                    Method method = clzss.getMethod(methodName, field.getType());
                    method.invoke(object, ConvertUtils.convert(resultSet.getString(i), field.getType()));//将数据库中的值的类型转换成类方法中的形参类型
                }
                list.add(object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (list.size() == 0)
            list = null;
        return list;
    }

}
