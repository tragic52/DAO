package cn.edu.djtu.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
     private static final String DBDRIVER  = "com.mysql.jdbc.Driver";
     private static final String DBURL = "jdbc:mysql://localhost:3306/sample?serverTimezone=UTC";      //ע�����ݿ���滻
     private static final String DBUSER = "root";
     private static final String DBPASSWORD = "Aa1999923";
     private Connection conn = null;
     
     public DBConnection() throws Exception{                      //��������ʱ���������ݿ⣬�޲ι��캯��
    	 try {
    		 Class.forName(DBDRIVER);
    		 this.conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
    	 }catch(Exception ex){
    		 ex.printStackTrace();
    	 }
     }
     public Connection getConnection() {                               //��ȡ���Ӷ���
    	 return this.conn; 
     }
     public void close() throws Exception{                             //�ر����ݿ�����
    	 if(this.conn!=null){
    		 try {
        		 this.conn.close();
        	 }catch(Exception ex) {
        		 ex.printStackTrace();
        	 }
    	 }
     }
}
