<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">用户信息列表</h3>

    <div style="float: left;">
        <form class="form-inline" method="post" action="${pageContext.request.contextPath}/findUserByPageServlet">
            <div class="form-group">
                <label for="username">姓名</label>
                <input type="text" class="form-control" id="username" name="name" value="${condition.name[0]}">
            </div>
            <div class="form-group">
                <label for="address">籍贯</label>
                <input type="text" class="form-control" id="address" name="address" value="${condition.address[0]}">
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" name="email" value="${condition.email[0]}">
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>
    </div>

    <div style="float: right;margin: 5px">
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/add.jsp">添加联系人</a>
        <a class="btn btn-primary" href="javascript:void(0);" id="delSelected">删除选中</a>
    </div>
    <form action="${pageContext.request.contextPath}/deleteSelectedServlet" method="post" id="form">
        <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <th ><input type="checkbox" id="firstCheckBox"></th>
            <th>编号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>籍贯</th>
            <th>QQ</th>
            <th>邮箱</th>
            <th>操作</th>
        </tr>

        <c:forEach items="${page.list}" var="user" varStatus="s">
            <tr>
                <th ><input type="checkbox" name="uid" value="${user.id}"></th>
                <td>${s.count}</td>
                <td>${user.name}</td>
                <td>${user.gender}</td>
                <td>${user.age}</td>
                <td>${user.address}</td>
                <td>${user.qq}</td>
                <td>${user.email}</td>
                <td>
                    <a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/findUserServlet?id=${user.id}&name=${condition.name[0]}&address=${condition.address[0]}">修改</a>&nbsp;
                    <a class="btn btn-default btn-sm" href="javascript:deleteUser(${user.id})">删除</a>
                </td>
            </tr>

        </c:forEach>
    </table>
    </form>

    <div>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:if test="${page.currentPage == 1}">
                    <li class="disabled">
                        <span>
                            <span aria-hidden="true">&laquo;</span>
                        </span>
                    </li>
                </c:if>
                <c:if test="${page.currentPage != 1}">
                    <li>
                        <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${page.currentPage-1}&rows=${page.rows}&name=${condition.name[0]}&address=${condition.address[0]}" aria-label="Next">
                            <span aria-hidden="true">&laquo</span>
                        </a>
                    </li>
                </c:if>

                <c:forEach begin="1" end="${page.totalPage}" var="i">
                    <c:if test="${page.currentPage == i}">
                        <li class="active"><a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${i}&rows=${page.rows}&name=${condition.name[0]}&address=${condition.address[0]}">${i}</a></li>
                    </c:if>
                    <c:if test="${page.currentPage != i}">
                        <li><a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${i}&rows=${page.rows}&name=${condition.name[0]}&address=${condition.address[0]}">${i}</a></li>
                    </c:if>
                </c:forEach>

                <c:if test="${page.currentPage == page.totalPage}">
                    <li class="disabled">
                        <span>
                            <span aria-hidden="true">&raquo;</span>
                        </span>
                    </li>
                </c:if>
                <c:if test="${page.currentPage != page.totalPage}">
                    <li>
                        <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${page.currentPage+1}&rows=${page.rows}&name=${condition.name[0]}&address=${condition.address[0]}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>

                <span style="font-size: 25px;margin-left: 25px">
                共${page.totalCount}条记录，共${page.totalPage}页
            </span>
            </ul>
        </nav>
    </div>
</div>
<script>
    function deleteUser(id) {
        if (confirm("你确定要删除吗？")) {
            location.href = "${pageContext.request.contextPath}/deleteUserServlet?id=" + id;
        }
    }

    window.onload = function () {
        //给删除选中按钮添加单击事件
        document.getElementById("delSelected").onclick = function () {
            if (confirm("你确定要删除吗？")) {
                //判断是否有选中条目
                var flag = false;
                //2.获取下边所有表中的cb
                var cbs = document.getElementsByName("uid");
                //3.遍历列表
                for (var i = 0; i < cbs.length; i++) {
                    if (cbs[i].checked) {
                        //有条目被选中
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    //表单提交
                    document.getElementById("form").submit();
                }
            }
        }

        //1.获取第一个checkBox
        document.getElementById("firstCheckBox").onclick = function () {
            //2.获取下边所有表中的cb
            var cbs = document.getElementsByName("uid");
            //3.遍历列表
            for (var i = 0; i < cbs.length; i++) {
                //设置其checked的状态
                cbs[i].checked = this.checked;
            }
        }
    }
</script>
</body>
</html>
