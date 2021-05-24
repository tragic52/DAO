package DAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		//������ҳ��Ӧ��ʽ(�ö�)
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//��������ҳ�洫�͵�����(��������޸�)
		String ID =request.getParameter("ID");
		//�������ݿ��õ��Ķ���
		Connection con = null;         //���Ӷ���
		
		//�������ݿ��õ��Ĳ�����Ϣ
		String url = "jdbc:mysql://localhost:3306/company?serverTimezone=UTC";     //���ӵ�ַ(�˴�ע�����ݿ�����company��������Ҫ������������滻)
		String driver = "com.mysql.cj.jdbc.Driver";                                  //����������
		String user = "root";                                                      //���ݿ���
		String password = "Aa1999923";                                             //���ݿ�����
		
		//���ݿ�������
		String sql = "delete from emp where ID= ?";
		
		try(PrintWriter pw = response.getWriter()){
		//�˴��������ݿ��ʵ�ʲ���
			//��һ����װ������
			Class.forName(driver);
			
			//�ڶ������������� 
		    con = DriverManager.getConnection(url,user,password);
		
			//��������������ѯ����
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, ID);
			int i = pst.executeUpdate();
			System.out.println(i);
			
			pw.write("<h1>"+ID+"</h1>");
			pw.write("��Ϣɾ���ɹ����������Ӳ鿴<a href='SimpleQueryServlet'>�鿴����Ա����Ϣ</a>");
			//���岽���ر�����
			con.close();
		
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
