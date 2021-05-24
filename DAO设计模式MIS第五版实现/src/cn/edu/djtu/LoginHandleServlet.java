package cn.edu.djtu;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.djtu.factory.ServiceFactory;
import cn.edu.djtu.service.impl.LoginServiceImpl;
import cn.edu.djtu.vo.Login;

/**
 * Servlet implementation class LoginHandleServlet
 */
@WebServlet("/LoginHandleServlet")
public class LoginHandleServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//������ҳ��Ӧ��ʽ(�ö�)
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//��������ҳ�洫�͵�����(��������޸�)
		String username =request.getParameter("username");
		String userpass =request.getParameter("userpass");
		
		Login login = new Login();
		login.setUsername(username);
		login.setUserpass(userpass);
		
		//ͨ������������ȡһ��LoginServiceImplʵ��
		LoginServiceImpl loginService = ServiceFactory.getLoginServiceImpl();
		
		if(loginService.logon(login).equals("success")) {
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else {
			request.setAttribute("error","��������û����������");
			request.getRequestDispatcher("login.jsp").forward(request, response);
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
