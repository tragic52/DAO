package cn.edu.djtu.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.edu.djtu.dao.LoginDAO;
import cn.edu.djtu.vo.Login;

/**
 * @author 冯朝
 * 创建时间：2021年4月16日 下午8:06:40 
 * @version 
 */
public class LoginDAOImpl implements LoginDAO {
	
	private Connection conn = null;
	private PreparedStatement prst = null  ;

	public LoginDAOImpl(Connection conn) {
		this.conn=conn;
	}

	public Login findByName(String username) throws Exception {
		Login login = null;
		ResultSet rs;
		String sql = "SELECT username,userpass FROM login WHERE username = ?";
		prst = conn.prepareStatement(sql);
		prst.setString(1, username);
		rs=prst.executeQuery();
		if(rs.next()) {
			login = new Login();
			login.setUsername(rs.getString(1));
			login.setUserpass(rs.getString(2));
		}
		rs.close();
		prst.close();
		return login;
	}

	
	public boolean save(Login login) throws Exception {
		boolean b = false;
		PreparedStatement prst = null  ;
        String sql = "insert into login values(?,?)";
        prst= conn.prepareStatement(sql);
        prst.setString(1,login.getUsername());
        prst.setString(2,login.getUserpass());
        if(prst.executeUpdate()>0) {
        	b=true;
        }
        prst.close();
		return b;
	}
}
