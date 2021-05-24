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
		//设置网页响应格式(置顶)
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//连接数据库用到的对象
		Connection con = null;         //连接对象
		Statement  st   = null;         //语句对象
		ResultSet  rs   = null;         //结果集对象
		
		//连接数据库用到的参数信息
		String url = "jdbc:mysql://localhost:3306/company?serverTimezone=UTC";     //连接地址(此处注意数据库名称company该名称需要根据情况进行替换)
		String driver = "com.mysql.cj.jdbc.Driver";                                   //连接驱动名
		String user = "root";                                                      //数据库名
		String password = "Aa1999923";                                             //数据库密码
		
		//数据库操作语句
		String sql = "select * from emp";
		
		try(PrintWriter pw = response.getWriter()){
		//此处进行数据库的实际操作
			//第一步：装载驱动
			Class.forName(driver);
			
			//第二步：建立连接 
		    con = DriverManager.getConnection(url,user,password);
		
			//第三步：构建查询对象;
			st = con.createStatement();
		    rs = st.executeQuery(sql);
		
			//第四步：将结果集中的数据提取
			pw.write("<table align='center' border='1'>");
			pw.write("<caption>员工信息</caption>");
			pw.write("<tr>");
			pw.write("<th style=\"font-size:150%\"> 编号 </th>");
			pw.write("<th style=\"font-size:150%\"> 姓名 </th>");
			pw.write("<th style=\"font-size:150%\"> 薪水 </th>");
			pw.write("<th style=\"font-size:150%\"> 年龄 </th>");
			pw.write("<th style=\"font-size:150%\"> 操作 </th>");
			pw.write("</tr>");
			while(rs.next()) {
				pw.write("<tr>");
				pw.write("<th style=\"font-size:150%\">" +rs.getString("ID")+ "</th>");
				pw.write("<th style=\"font-size:150%\">" +rs.getString("name")+ "</th>");
				pw.write("<th style=\"font-size:150%\">" +rs.getString("salary")+ "</th>");
				pw.write("<th style=\"font-size:150%\">" +rs.getString("age")+ "</th>");
                String id = rs.getString("ID");
				pw.write("<th style=\"font-size:150%\">"+" <a href='Delete?ID="+id+"'>删除</a>"+ " <a href='amend.jsp?ID="+id+"'>修改</a>"+"</th>");
				pw.write("");                                                      //使用超链接传递变脸(动态)
				pw.write("</tr>");
			}
			pw.write("</table>");
			
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
