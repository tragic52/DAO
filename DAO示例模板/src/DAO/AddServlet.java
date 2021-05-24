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
 * Servlet implementation class AddServlet
 */
@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置网页响应格式(置顶)
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//获取表单提交的信息
		String Name = request.getParameter("name");                //获取的信息需要根据实际情况进行修改
		String Salary = request.getParameter("salary");
		String Age = request.getParameter("age");               
		
		//连接数据库用到的对象
		Connection con = null;         //连接对象
//		Statement  st   = null;         //普通语句对象
		PreparedStatement pst = null;   //条件语句对象
		
		//连接诶数据库用到的参数信息
		String url = "jdbc:mysql://localhost:3306/company?serverTimezone=UTC";     //连接地址(此处注意数据库名称company该名称需要根据情况进行替换)
		String driver = "com.mysql.cj.jdbc.Driver";                                 //连接驱动名
		String user = "root";                                                      //数据库名
		String password = "Aa1999923";                                             //数据库密码
		
		//数据库操作语句
		String sql = "insert into emp(name,salary,age) values(?,?,?)";
		
		try(PrintWriter pw = response.getWriter()){
		//此处进行数据库的实际操作
			//第一步：装载驱动
			Class.forName(driver);
			
			//第二步：建立连接 
			con = DriverManager.getConnection(url,user,password);
		
			//第三步：构建查询对象
		    pst = con.prepareStatement(sql);
			pst.setString(1, Name);
			pst.setString(2, Salary);
			pst.setString(3, Age);
			
			int i = pst.executeUpdate();
			System.out.println(i);//在控制台打印受影响的数据条数
			
			response.sendRedirect("SimpleQueryServlet");     //通过重定向显示所有员工信息
			//第五步：关闭连接
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
