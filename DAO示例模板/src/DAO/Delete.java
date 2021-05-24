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
		//设置网页响应格式(置顶)
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//接收其他页面传送的数据(根据情况修改)
		String ID =request.getParameter("ID");
		//连接数据库用到的对象
		Connection con = null;         //连接对象
		
		//连接数据库用到的参数信息
		String url = "jdbc:mysql://localhost:3306/company?serverTimezone=UTC";     //连接地址(此处注意数据库名称company该名称需要根据情况进行替换)
		String driver = "com.mysql.cj.jdbc.Driver";                                  //连接驱动名
		String user = "root";                                                      //数据库名
		String password = "Aa1999923";                                             //数据库密码
		
		//数据库操作语句
		String sql = "delete from emp where ID= ?";
		
		try(PrintWriter pw = response.getWriter()){
		//此处进行数据库的实际操作
			//第一步：装载驱动
			Class.forName(driver);
			
			//第二步：建立连接 
		    con = DriverManager.getConnection(url,user,password);
		
			//第三步：构建查询对象
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, ID);
			int i = pst.executeUpdate();
			System.out.println(i);
			
			pw.write("<h1>"+ID+"</h1>");
			pw.write("信息删除成功，请点击链接查看<a href='SimpleQueryServlet'>查看所有员工信息</a>");
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
