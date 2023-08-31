<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="farmaciaohana.entidadesdenegocio.Cita"%>
<%@page import="java.util.ArrayList"%>
<%ArrayList<Cita> citas = (ArrayList<Cita>) request.getAttribute("citas");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Citas!</h1>
        <table class="m2 z-depth striped higlight centered responsive-table">
            <thead>
                <tr>
                    <th>IdAdministracionDoctores</th>
                    <th>Fecha</th>
                    <th>Hora</th>
                </tr>
            </thead>
            <tbody>
                <%for(Cita cita: citas){
                %>
                <tr>
                    <td><%=cita.getIdAdministracionDoctores() %><td>
                    <td><%=cita.getFecha()%><td>
                    <td><%=cita.getHora()%><td>
                </tr>
                <%}%>
            </tbody>
        </table>
    </body>
</html>
