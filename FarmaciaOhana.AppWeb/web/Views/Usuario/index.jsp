<%-- 
    Document   : index
<<<<<<< HEAD
    Created on : 26 ago 2023, 17:04:46
=======
    Created on : 28 ago 2023, 13:56:10
>>>>>>> 4567bf7 (Programacion de controladores)
    Author     : MINEDUCYT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<<<<<<< HEAD
=======
<%import java.util.ArrayList;%>
<%import farmaciaohana.entidadesdenegocio.Usuario;%>
<%
    ArrayList<Usuario> usuarios = (Usuario) request.setAttribute("usuarios");
%>
>>>>>>> 4567bf7 (Programacion de controladores)
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
<<<<<<< HEAD
        <h1>Hello World!</h1>
=======
        
        <h1>Hello World!</h1>
        <ul>
            <%
            for(Usuario usuario: usuarios ){
            
                %>
                <li><%=usuario.getNombre %></li>
            <%}%>
        </ul>
>>>>>>> 4567bf7 (Programacion de controladores)
    </body>
</html>
