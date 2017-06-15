<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>

        <table class="table-data">
            <col class="col1"/>
            <col class="col2"/>
            <col class="col3"/>
            <col class="col4"/>
            <col class="col5"/>

            <tr class="tr-header">
                <th>Index</th>
                <th>Name</th>
                <th>Address</th>
                <th>Action</th>
            </tr>

            <logic:iterate name="listProducts" id="product" indexId="i">
                <tr class="tr-data">
                    <td>${i + 1}</td>
                    <td><bean:write name="product" property="name"/></td>
                    <td><bean:write name="product" property="unit"/></td>
                    <td><bean:write name="product" property="availableStock"/></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/toUpdateProductForm.do?productId=<bean:write name="product" property="id"/>">Edit</a> | 
                        <a href="">Delete</a>
                    </td>
                </tr>
            </logic:iterate>

        </table>
        
        <a href="${pageContext.request.contextPath}/toAddProductForm.do">Add A Product</a>
    </body>
</html>
