package cn.edu.djtu.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
     private static final String DBDRIVER  = "com.mysql.jdbc.Driver";
     private static final String DBURL = "jdbc:mysql://localhost:3306/sample?serverTimezone=UTC";      //注意数据库的替换
     private static final String DBUSER = "root";
     private static final String DBPASSWORD = "Aa1999923";
     private Connection conn = null;
     
     public DBConnection() throws Exception{                      //创建对象时就连接数据库，无参构造函数
    	 try {
    		 Class.forName(DBDRIVER);
    		 this.conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
    	 }catch(Exception ex){
    		 ex.printStackTrace();
    	 }
     }
     public Connection getConnection() {                               //获取连接对象
    	 return this.conn; 
     }
     public void close() throws Exception{                             //关闭数据库连接
    	 if(this.conn!=null){
    		 try {
        		 this.conn.close();
        	 }catch(Exception ex) {
        		 ex.printStackTrace();
        	 }
    	 }
     }
}
