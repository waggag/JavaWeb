<%@ page import="java.util.ArrayList" %>
<%@ page import="cn.waggag.domain.User" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>循环显示表格内容</title>
  </head>
  <body>
  <%
    ArrayList<User> users = new ArrayList<>();
    users.add(new User("waggag","225514",new Date()));
    users.add(new User("ms","sss",new Date()));
    request.setAttribute("users",users);
  %>

  <table border="1px" width="500" align="center">
    <tr>
      <th>循环次数</th>
      <th>username</th>
      <th>password</th>
      <th>date</th>
    </tr>
  <c:forEach items="${users}" var="user" varStatus="s">
    <c:if test="${s.count%2 == 0}">
      <tr bgcolor="red">
        <th>${s.count}</th>
        <th>${user.username}</th>
        <th>${user.password}</th>
        <th>${user.bitStr}</th>
      </tr>
    </c:if>
    <c:if test="${s.count%2 != 0}">
      <tr bgcolor="#ffd700">
        <th>${s.count}</th>
        <th>${user.username}</th>
        <th>${user.password}</th>
        <th>${user.bitStr}</th>
      </tr>
    </c:if>
  </c:forEach>
  </table>
  </body>
</html>
