package cn.edu.djtu;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.djtu.util.DBConnection;

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
				
		//�������ݿ��õ��Ķ���
		Connection con = null;          //���Ӷ���
		PreparedStatement pst = null;   //������
		ResultSet  rs   = null;         //���������
		
			
		
		
		//���ݿ�������
		String sql = "SELECT username,userpass FROM login WHERE username=?";
		try(PrintWriter pw = response.getWriter()){
		//�˴��������ݿ��ʵ�ʲ���
			//ʵ����һ��DBConnection����
			DBConnection dbc = new DBConnection();
			
		    con = dbc.getConnection();
		    
			//��������������ѯ����;
			pst =  con.prepareStatement(sql);
	
			//���Ĳ�����������е�������ȡ
			pst.setString(1,username);
			rs = pst.executeQuery();
			if(rs.next()&&rs.getString(2).equals(userpass)) {
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}else {
				request.setAttribute("error","��������û����������");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			//���岽���ر�����		
			con.close();
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
