package cn.edu.djtu;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.djtu.util.DBConnection;

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
				
		//�������ݿ��õ��Ķ���
		Connection con = null;         //���Ӷ���
		PreparedStatement pst = null;   //����������
		ResultSet  rs   = null;         //���������
		
		//���ݿ�������
		String sql1 = "SELECT username,userpass FROM login WHERE username=?";       //��ѯ����������
		String sql2 = "insert into login values(?,?)";                              //��Ӳ���
		try(PrintWriter pw = response.getWriter()){
		//�˴��������ݿ��ʵ�ʲ���
			//ʵ����һ��DBConnection����
			DBConnection dbc = new DBConnection();
			
			con = dbc.getConnection();
			//��������������ѯ����
		    pst = con.prepareStatement(sql1);
			pst.setString(1,username );
			rs=pst.executeQuery();
			if(rs.next()) {
				request.setAttribute("error","���û��Ѵ���");
				request.getRequestDispatcher("addLogin.jsp").forward(request, response);
			}else {		
				pst = con.prepareStatement(sql2);
				pst.setString(1, username);
				pst.setString(2, userpass);
				pst.executeUpdate();
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			int i = pst.executeUpdate();
			System.out.println(i);//�ڿ���̨��ӡ��Ӱ�����������
			//���岽���ر�����
			con.close();
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
