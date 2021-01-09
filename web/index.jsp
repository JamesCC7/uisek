<%-- 
    Document   : index
    Created on : 08-ene-2021, 21:02:14
    Author     : Jaime
--%>



<%@page import="com.uisekapp.models.cEstudiante"%>
<%@page import="com.uisekapp.controllers.fEstudiante"%>
<%@page import="com.uisekapp.models.test"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
 
        <%
            fEstudiante objE = new fEstudiante();
            
            for (cEstudiante elem : objE.listarEstudiantes()) {
                out.println((elem.getIdEstudiante()+ " "+ elem.getCedula() + " " + elem.getNombres() + " "+ elem.getApellidos()));
                out.print("\n");
         }
        %>
        
    </body>
</html>
