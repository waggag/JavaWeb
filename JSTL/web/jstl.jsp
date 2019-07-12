<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>foreach标签</title>
  </head>
  <body>
  <c:forEach begin="0" end="10" var="i" step="1">
      ${i}
  </c:forEach>
<br>
<%
  ArrayList<String> list = new ArrayList<>();
  list.add("waggag");
  list.add("ms");
  list.add("gg");
  request.setAttribute("list",list);
%>
  <c:forEach items="${list}" var="name" varStatus="s">
    循环取值：${name}
    循环次数：${s.count}
    循环索引：${s.index} <br>
  </c:forEach>


  </body>
</html>
