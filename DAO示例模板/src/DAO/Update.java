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
 * Servlet implementation class Update
 */
@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		//������ҳ��Ӧ��ʽ(�ö�)
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//��������ҳ�洫�͵�����(��������޸�)
		String ID = request.getParameter("ID");
		String newname = request.getParameter("name");
		String newsalary = request.getParameter("salary");
		String newage = request.getParameter("age");

		
		//�������ݿ��õ��Ķ���
		Connection con = null;                  //���Ӷ���
		PreparedStatement pst   = null;         //������
		
		//�������ݿ��õ��Ĳ�����Ϣ
		String url = "jdbc:mysql://localhost:3306/company?serverTimezone=UTC";     //���ӵ�ַ(�˴�ע�����ݿ�����company��������Ҫ������������滻)
		String driver = "com.mysql.cj.jdbc.Driver";                                  //����������
		String user = "root";                                                      //���ݿ���
		String password = "Aa1999923";                                             //���ݿ�����
		
		//���ݿ�������
		String sql =  "update emp set name =?,salary =?,age =?where ID =?";
		
		try(PrintWriter pw = response.getWriter()){
		//�˴��������ݿ��ʵ�ʲ���
			//��һ����װ������
			Class.forName(driver);
			
			//�ڶ������������� 
		   con = DriverManager.getConnection(url,user,password);
		
			//��������������ѯ����
			pst = con.prepareStatement(sql);
			pst.setString(1, newname);
			pst.setString(2, newsalary);
			pst.setString(3, newage);
			pst.setString(4, ID);
			
			int i = pst.executeUpdate();
			System.out.println(i);
			response.sendRedirect("SimpleQueryServlet");     //ͨ���ض�����ʾ����Ա����Ϣ
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
