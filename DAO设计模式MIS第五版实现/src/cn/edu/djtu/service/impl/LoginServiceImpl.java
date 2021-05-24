package cn.edu.djtu.service.impl;


import java.sql.Connection;

import cn.edu.djtu.dao.LoginDAO;
import cn.edu.djtu.factory.DAOFactory;
import cn.edu.djtu.service.LoginService;
import cn.edu.djtu.util.DBConnection;
import cn.edu.djtu.vo.Login;

public class LoginServiceImpl implements LoginService {

	private final DBConnection dbc = new DBConnection();
	public String logon(Login login)  {
		// TODO Auto-generated method stub
		String string="error";
		Connection conn = dbc.getConnection();
		LoginDAO loginDAO = DAOFactory.getLoginDAOImpl(conn);
		try {
			Login login1 = loginDAO.findByName(login.getUsername());
			if(login1!=null&&login1.getUserpass().equals(login.getUserpass())) {
				string = "success";
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				dbc.close();
			} catch (Exception e) {
			    e.printStackTrace();
			}
		}
		return string;
	}

	@Override
	public String addLogin(Login login) {
		String string ="error";
		Connection conn = dbc.getConnection();
		LoginDAO loginDAO = DAOFactory.getLoginDAOImpl(conn);
		try {
			if(loginDAO.findByName(login.getUsername())==null&&loginDAO.save(login)) {
				string = "success";
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				dbc.close();
			} catch (Exception e) {
			    e.printStackTrace();
			}
		}
		return string;
	}

}
