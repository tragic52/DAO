package cn.edu.djtu;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.djtu.dao.LoginDAO;
import cn.edu.djtu.impl.LoginDAOImpl;
import cn.edu.djtu.util.DBConnection;
import cn.edu.djtu.vo.Emp;

/**
 * Servlet implementation class LoginHandleServlet
 */
@WebServlet("/LoginHandleServlet")
public class LoginHandleServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置网页响应格式(置顶)
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//接收其他页面传送的数据(根据情况修改)
		String username =request.getParameter("username");
		String userpass =request.getParameter("userpass");
			
		try(PrintWriter pw = response.getWriter()){
		
			//实例化一个DBConnection对象
			DBConnection dbc = new DBConnection();
			
			//实例化一个LoginDAOImpl对象
			LoginDAO loginDAO = new LoginDAOImpl(dbc.getConnection());
		    
			Emp login = loginDAO.findByName(username);
			
			if(login!=null&&login.getUserpass().equals(userpass)) {
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}else {
				request.setAttribute("error","您输入的用户名密码错误");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			//第五步：关闭连接		
			dbc.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
		 
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);	
	}
    
}
