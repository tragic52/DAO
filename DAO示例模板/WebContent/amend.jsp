<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改信息</title>
</head>
<body>
       <%String id=request.getParameter("ID"); %>
       <form action="Update" method="post">
        <fieldset>
           <legend>修改员工信息</legend>
           姓名：<input type="text" name="name"><br>
           薪水：<input type="text" name="salary"><br>
           年龄：<input type="text" name="age"><br>
           <input type="hidden" name="ID" value="<%=id%>">   <!-- 隐藏域传递变量参数，使用 -->
           <input type="submit" value="修改">
           <input type="reset" value="重置"> 
        </fieldset>
     </form>    
</body>
</html>