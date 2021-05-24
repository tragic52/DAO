package cn.edu.djtu.factory;

import java.sql.Connection;

import cn.edu.djtu.impl.LoginDAOImpl;

public class DAOFactory {
    public static LoginDAOImpl getLoginDAOImpl(Connection conn) {
    	return new LoginDAOImpl(conn);
    }
}
