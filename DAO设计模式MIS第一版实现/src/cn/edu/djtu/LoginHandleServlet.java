package cn.edu.djtu;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginHandleServlet
 */
@WebServlet("/LoginHandleServlet")
public class LoginHandleServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置网页响应格式(置顶)
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//接收其他页面传送的数据(根据情况修改)
		String username =request.getParameter("username");
		String userpass =request.getParameter("userpass");
				
		//连接数据库用到的对象
		Connection con = null;          //连接对象
		PreparedStatement pst = null;   //语句对象
		ResultSet  rs   = null;         //结果集对象
		
		//连接数据库用到的参数信息
		String url = "jdbc:mysql://localhost:3306/sample?serverTimezone=UTC";     //连接地址(此处注意数据库名称company该名称需要根据情况进行替换)
		String driver = "com.mysql.jdbc.Driver";                                   //连接驱动名
		String user = "root";                                                      //数据库名
		String password = "Aa1999923";                                             //数据库密码
		
		//数据库操作语句
		String sql = "SELECT username,userpass FROM login WHERE username=?";
		try(PrintWriter pw = response.getWriter()){
		//此处进行数据库的实际操作
			//第一步：装载驱动
			Class.forName(driver);
			
			//第二步：建立连接 
		    con = DriverManager.getConnection(url,user,password);
		    
			//第三步：构建查询对象;
			pst =  con.prepareStatement(sql);
	
			//第四步：将结果集中的数据提取
			pst.setString(1,username);
			rs = pst.executeQuery();
			if(rs.next()&&rs.getString(2).equals(userpass)) {
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}else {
				request.setAttribute("error","您输入的用户名密码错误");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			//第五步：关闭连接		
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
