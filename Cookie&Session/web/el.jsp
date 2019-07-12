<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>el获取值</title>
</head>
<body>
    <%
        request.setAttribute("msg","request");
        session.setAttribute("msg","session");
        List list = new ArrayList<>();
        list.add("dsadsa");
        list.add("sdads");
        list.add("waggag");
        request.setAttribute("list",list);

        Map map = new HashMap<String,String>();
        map.put("name","waggag");
        map.put("gender","male");
        map.put("age","23");
        request.setAttribute("map",map);
    %>

    ${requestScope.msg}
    ${sessionScope.msg}
    ${list.get(2)}
    <br>
    ${map.name}
    ${map["name"]}
</body>
</html>
