package DAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SimpleQueryServlet
 */
@WebServlet("/SimpleQueryServlet")
public class SimpleQueryServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//������ҳ��Ӧ��ʽ(�ö�)
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//�������ݿ��õ��Ķ���
		Connection con = null;         //���Ӷ���
		Statement  st   = null;         //������
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
			//��һ����װ������
			Class.forName(driver);
			
			//�ڶ������������� 
		    con = DriverManager.getConnection(url,user,password);
		
			//��������������ѯ����;
			st = con.createStatement();
		    rs = st.executeQuery(sql);
		
			//���Ĳ�����������е�������ȡ
			pw.write("<table align='center' border='1'>");
			pw.write("<caption>Ա����Ϣ</caption>");
			pw.write("<tr>");
			pw.write("<th style=\"font-size:150%\"> ��� </th>");
			pw.write("<th style=\"font-size:150%\"> ���� </th>");
			pw.write("<th style=\"font-size:150%\"> нˮ </th>");
			pw.write("<th style=\"font-size:150%\"> ���� </th>");
			pw.write("<th style=\"font-size:150%\"> ���� </th>");
			pw.write("</tr>");
			while(rs.next()) {
				pw.write("<tr>");
				pw.write("<th style=\"font-size:150%\">" +rs.getString("ID")+ "</th>");
				pw.write("<th style=\"font-size:150%\">" +rs.getString("name")+ "</th>");
				pw.write("<th style=\"font-size:150%\">" +rs.getString("salary")+ "</th>");
				pw.write("<th style=\"font-size:150%\">" +rs.getString("age")+ "</th>");
                String id = rs.getString("ID");
				pw.write("<th style=\"font-size:150%\">"+" <a href='Delete?ID="+id+"'>ɾ��</a>"+ " <a href='amend.jsp?ID="+id+"'>�޸�</a>"+"</th>");
				pw.write("");                                                      //ʹ�ó����Ӵ��ݱ���(��̬)
				pw.write("</tr>");
			}
			pw.write("</table>");
			
			//���岽���ر�����		
			con.close();
			
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
