<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户登录</title>
</head>
<body>
     <h1>你正在进行管理员登录</h1>
     ${error}
     <form action="LoginHandleServlet" method="post">
        <fieldset>
        <legend>登录</legend>
        用户名：<input type="text" name="username"><br>
        密码 ：<input type="password" name="userpass"><br>
        <input type="submit" name="登录"><br>
        </fieldset>
     </form>
</body>
</html>