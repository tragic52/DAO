<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--导入所需要的包--%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>简单查询</title>
</head>
<body>
     <%
//==================================进行数据库的逻辑操作（准备工作）=============================================
        //设置网页响应格式(置顶)
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//接收其他页面传送的数据(根据情况修改)
		String ID =request.getParameter("ID");
		
		//连接数据库用到的对象
		Connection con = null;          //连接对象
		Statement  st   = null;         //语句对象(依条件选用)
		PreparedStatement pst = null;
		ResultSet  rs   = null;         //结果集对象
		
		//连接数据库用到的参数信息
		String url = "jdbc:mysql://localhost:3306/company?serverTimezone=UTC";     //连接地址(此处注意数据库名称company该名称需要根据情况进行替换)
		String driver = "com.mysql.cj.jdbc.Driver";                                   //连接驱动名
		String user = "root";                                                      //数据库名
		String password = "Aa1999923";                                             //数据库密码
		
		//数据库操作语句
		String sql = "select * from emp";
//==================================进行数据库的逻辑操作（实际工作）=============================================
            //第一步：装载驱动
			Class.forName(driver);
			
			//第二步：建立连接 
		    con = DriverManager.getConnection(url,user,password);
		
			//第三步：构建查询对象;
			st = con.createStatement();
		    rs = st.executeQuery(sql);
     %>
         <table width="130" height="35" align="center" border="1">
     <%
        while(rs.next()){
     %>  
             <tr>
                <td><%=rs.getString(1) %></td>
                <td><%=rs.getString(2) %></td>
                <td><%=rs.getString(3) %></td>
                <td><%=rs.getString(4) %></td>
             </tr>
     <%
        }
        if(rs!=null){
        	rs.close();
        }
        if(st!=null){
        	st.close();
        }
        if(con!=null){
        	con.close();
        }
     %>
         </table>
</body>
</html>