
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="farmaciaohana.entidadesdenegocio.Rol"%>
<%@page import="farmaciaohana.accesoadatos.RolDAL"%>
<%@page import="java.util.ArrayList"%>

<% ArrayList<Rol> citas = RolDAL.obtenerTodos();
    int id = Integer.parseInt(request.getParameter("id"));
%>
<select id="slCitas" name="idRol">
    <option <%=(id == 0) ? "selected" : ""%>  value="0">SELECCIONAR</option>
    <% for (Rol citas : citas) {%>
    <option <%=(id == Citas.getId()) ? "selected" : ""%>  value="<%=rol.getId()%>"><%= rol.getNombre()%></option>
    <%}%>
</select>
<label for="idCitas">Citas</label>