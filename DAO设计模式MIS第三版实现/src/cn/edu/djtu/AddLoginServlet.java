package cn.edu.djtu;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.djtu.dao.LoginDAO;
import cn.edu.djtu.impl.LoginDAOImpl;
import cn.edu.djtu.util.DBConnection;
import cn.edu.djtu.vo.Emp;

/**
 * Servlet implementation class AddLoginServlet
 */
@WebServlet("/AddLoginServlet")
public class AddLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置网页响应格式(置顶)
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//获取表单提交的信息
		String username =request.getParameter("username");
		String userpass =request.getParameter("userpass");
				
		try(PrintWriter pw = response.getWriter()){
		//此处进行数据库的实际操作
			//实例化一个DBConnection对象
			DBConnection dbc = new DBConnection();
			
			//实例化一个LoginDAOImpl对象
			LoginDAO loginDAO = new LoginDAOImpl(dbc.getConnection());
			
			if(loginDAO.findByName(username)!=null) {
				request.setAttribute("error","该用户已存在");
				request.getRequestDispatcher("addLogin.jsp").forward(request, response);
			}else {		
				Emp login = new Emp();
				login.setUsername(username);
				login.setUserpass(userpass);
				loginDAO.save(login);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			
			//第五步：关闭连接
			dbc.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
		 
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);	
	}
    
}
