<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title><tiles:getAsString name="title" ignore="true" /></title>
    </head>
    <body>
    	<style>
    		body{
                background-image: url('resources/images/gradiant.png');
                margin-right:200px;
            }
    	</style>
    
        <table width="60%" height="600px" cellpadding="10" cellspacing="10" align="center">
            <tr>
                <td height="10%" colspan="2">
                    <tiles:insert attribute="header" ignore="true" />
                </td>
            </tr>
            <tr>
                <td width="18%" >
                    <tiles:insert attribute="menu" />
                </td>
                <td>
                    <tiles:insert attribute="body" />
                </td>
            </tr>
            <tr>
                <td height="10%" colspan="2">
                    <tiles:insert attribute="footer" />
                </td>
            </tr>
        </table>
    </body>
</html>
