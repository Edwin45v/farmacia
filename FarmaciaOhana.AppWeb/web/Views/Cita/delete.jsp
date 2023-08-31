
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="farmaciaohana.entidadesdenegocio.Farmacia"%>
<% Farmacia farmacia = (farmacia) request.getAttribute("farmacia");%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Eliminar Farmacia</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Eliminar Empresa</h5>
            <form action="Empresa" method="post">  
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>"> 
                <input type="hidden" name="id" value="<%=farmacia.getId()%>">  
                <div class="row">
                    <div class="input-field col l4 s12">
                        <input  id="txtNombre" type="text" value="<%=farmacia.getNombre()%>" disabled>
                        <label for="txtNombre">Nombre</label>
                    </div>                       
                    <div class="input-field col l4 s12">
                        <input  id="txtRubro" type="text" value="<%=farmacia.getRubro()%>" disabled>
                        <label for="txtRubro">Rubro</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input  id="txtCategoria" type="text" value="<%=farmacia.getCategoria()%>" disabled>
                        <label for="txtCategoria">Categoria</label>
                    </div>      
                    <div class="input-field col l4 s12">
                        <input  id="txtDepartamento" type="text" value="<%=farmacia.getDepartamento()%>" disabled>
                        <label for="txtDepartamento">Departamento</label>
                    </div>
                    <div class="input-field col l4 s12">
                        <input id="txtContacto" type="text" value="<%=farmacia.getContacto().getNombre()%>" disabled>
                        <label for="txtContacto">Contacto</label>
                    </div> 
                </div>
                <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">delete</i>Eliminar</button>
                        <a href="farmacia" class="waves-effect waves-light btn blue"><i class="material-icons right">cancel</i>Cancelar</a>                          
                    </div>
                </div>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />
    </body>
</html>