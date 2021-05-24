<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加用户</title>
</head>
<body> 
     ${error}
     <form action="AddLoginServlet" method="post">
        <fieldset>
           <legend>添加员工</legend>
           用户名：<input type="text" name="username"><br>
           密码 ：<input type="password" name="userpass"><br>
           <input type="submit" value="添加用户">
        </fieldset>
     </form>
</body>
</html>