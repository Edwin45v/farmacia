<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="farmaciaohana.entidadesdenegocio.Doctor"%>
<%@page import="java.util.ArrayList"%>
<%ArrayList<Cita> doctores = (ArrayList<doctor>) request.getAttribute("doctores");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Doctores!</h1>
        <table class="m2 z-depth striped higlight centered responsive-table">
            <thead>
                <tr>
                    <th>IdAdministracionCitasMedicass</th>
                    <th>Fecha</th>
                    <th>Hora</th>
                </tr>
            </thead>
            <tbody>
                <%for(Doctor Doctor: Doctores){
                %>
                <tr>
                    <td><%=doctor.getIdAdministracionCitasMedicas() %><td>
                    <td><%=doctor.getFecha()%><td>
                    <td><%=doctor.getHora()%><td>
                </tr>
                <%}%>
            </tbody>
        </table>
    </body>
</html>
