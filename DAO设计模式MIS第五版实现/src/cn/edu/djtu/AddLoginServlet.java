package cn.edu.djtu;

import java.io.IOException;
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
				
		Login login = new Login();
		login.setUsername(username);
		login.setUserpass(userpass);
		
		//通过工厂类来获取一个LoginServiceImpl实例
		LoginServiceImpl loginService = ServiceFactory.getLoginServiceImpl();
		
		if(loginService.addLogin(login).equals("error")) {
			HttpSession session = request.getSession();
			session.setAttribute("error", "该用户已存在");
			request.getRequestDispatcher("addLogin.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("index.jsp").forward(request, response);
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
