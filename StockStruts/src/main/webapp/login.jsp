<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>

        <style type="text/css">
            body
            {
                margin-right:200px;
            }

            .span-inputField {
                float: right;
                margin-left: 1em;
            }
        </style>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <center>
            <div style="color:red">
                <%--<html:errors />--%>
                <logic:messagesPresent>
                    <html:messages id="error">
                        <span><bean:write name="error"/></span><br/>
                    </html:messages>
                </logic:messagesPresent>
            </div>
            <html:form action="/login.do?method=execute" method="post">
                <table  bgcolor="green" align="center" >

                    <tr>
                        <td>User Name <span class="span-inputField"><html:text name="AppUser" property="name"/></span></td>
                    </tr>
                    <tr>
                        <td>Password <span class="span-inputField"><html:password name="AppUser" property="pass"/></span></td>
                    </tr>

                    <tr>
                        <td align="right"><html:submit value="Login" /></td>
                    </tr>
                </table>

            </html:form>
        </center>
    </body>

</html>