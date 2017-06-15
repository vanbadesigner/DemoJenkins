<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <body>
    <style>
        .span-inputField {
            float: right;
            margin-left: 1em;
        }
    </style>
    <div style="color:red">
        <logic:messagesPresent>
            <html:messages id="error">
                <span><bean:write name="error"/></span><br/>
            </html:messages>
        </logic:messagesPresent>
    </div>
    <html:form action="/addProduct" method="post" >
        <table align="left" >
            <tr>
                <td>Product name <span class="span-inputField"> <html:text property="name" /> </span></td>
            </tr>
            <tr>
                <td>Product unit <span class="span-inputField"><html:text property="unit" /> </span></td>
            </tr>
            <td align="right"><html:submit value="Add" /></td>
        </tr>
    </table>
</html:form>
</body>

</html>