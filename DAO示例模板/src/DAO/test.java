package DAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class test
 */
@WebServlet("/test")
public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		//������ҳ��Ӧ��ʽ(�ö�)
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//��������ҳ�洫�͵�����(��������޸�)
		String ID =request.getParameter("ID");
		
		//�������ݿ��õ��Ķ���
		Connection con = null;          //���Ӷ���
		Statement  st   = null;         //������(������ѡ��)
		PreparedStatement pst = null;
		ResultSet  rs   = null;         //���������
		
		//�������ݿ��õ��Ĳ�����Ϣ
		String url = "jdbc:mysql://localhost:3306/company?serverTimezone=UTC";     //���ӵ�ַ(�˴�ע�����ݿ�����company��������Ҫ������������滻)
		String driver = "com.mysql.cj.jdbc.Driver";                                   //����������
		String user = "root";                                                      //���ݿ���
		String password = "Aa1999923";                                             //���ݿ�����
		
		//���ݿ�������
		String sql = "select * from emp";
		
		try(PrintWriter pw = response.getWriter()){
		//�˴��������ݿ��ʵ�ʲ���
			
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
