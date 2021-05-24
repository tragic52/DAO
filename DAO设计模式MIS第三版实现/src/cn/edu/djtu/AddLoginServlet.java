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
		//������ҳ��Ӧ��ʽ(�ö�)
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//��ȡ���ύ����Ϣ
		String username =request.getParameter("username");
		String userpass =request.getParameter("userpass");
				
		try(PrintWriter pw = response.getWriter()){
		//�˴��������ݿ��ʵ�ʲ���
			//ʵ����һ��DBConnection����
			DBConnection dbc = new DBConnection();
			
			//ʵ����һ��LoginDAOImpl����
			LoginDAO loginDAO = new LoginDAOImpl(dbc.getConnection());
			
			if(loginDAO.findByName(username)!=null) {
				request.setAttribute("error","���û��Ѵ���");
				request.getRequestDispatcher("addLogin.jsp").forward(request, response);
			}else {		
				Emp login = new Emp();
				login.setUsername(username);
				login.setUserpass(userpass);
				loginDAO.save(login);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			
			//���岽���ر�����
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
