<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>

    <body>

    <style>
        .table-data {
            border-collapse: collapse;
            margin: 0.5em;
        }

        .table-data th, .table-data td {
            border: 0.1em solid lightgray;
            padding: 0.3em;
        }

        .p-linkAdd {
            margin: 0.8em;
            padding: 0.5em;
        }

        .p-linkAdd a {
            color: white;
            text-decoration: none;
            font-weight: bold;
            border: 0.1em solid green;
            background: green;
            padding: 0.5em;
        }
        
        .p-linkAdd a:hover {
            background-color: #576551;
        }
    </style>

    <table class="table-data">
        <col class="col1"/>
        <col class="col2"/>
        <col class="col3"/>
        <col class="col4"/>
        <col class="col5"/>

        <tr class="tr-header">
            <th>Index</th>
            <th>Name</th>
            <th>Unit</th>
            <th>Available</th>
            <th>Action</th>
        </tr>

        <logic:iterate name="listProducts" id="product" indexId="i">
            <tr class="tr-data">
                <td>${i + 1}</td>
                <td><bean:write name="product" property="name"/></td>
                <td><bean:write name="product" property="unit"/></td>
                <td><bean:write name="product" property="availableStock"/></td>
                <td>
                    <a href="${pageContext.request.contextPath}/toUpdateProductForm.do?productId=${product.id}">Edit</a> | 
                    <a href="${pageContext.request.contextPath}/delProduct.do?productId=${product.id}">Delete</a>
                </td>
            </tr>
        </logic:iterate>

    </table>

    <p class="p-linkAdd"><a href="${pageContext.request.contextPath}/toAddProductForm.do">Add A Product</a></p>
</body>
</html>
