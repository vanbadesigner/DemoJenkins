<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <body>
    <style>
        .ul-menu{
            width: 100%;
            list-style: none;
            padding-left: 0em;
        }
        .ul-menu li{
            margin: 0.2em;
            background-color: #57af35;
            padding: 0.3em;
        }

        .ul-menu li a:hover {
            color: white;
        }
        
        .ul-menu li a{
            text-decoration: none;
            font-weight: bold;
            color: #432631;
            padding: 0.8em;
        }
        
    </style>
    <ul class="ul-menu">
        <li><a href="${pageContext.request.contextPath}/productsList.do" >Products</a></li>
        <li><a href="${pageContext.request.contextPath}/warehousesList.do" >Warehouses</a></li>
        <li><a href="" >Stock</a></li>
        <li><a href="#" >Goods receipt</a></li>
        <li><a href="#" >Goods Issue</a></li>
        <li><a href="${pageContext.request.contextPath}/logout.do" >Log out</a></li>
    </ul>
</body>
</html>
