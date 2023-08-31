<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="farmaciaohana.entidadesdenegocio.Cita"%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Crear Cita</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Crear Cita</h5>
            <form action="cita" method="post" onsubmit="return  validarFormulario()">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">                
                <div class="row">
                    <div class="input-field col l4 s12">
                        <input  id="txtNombre" type="text" name="nombre" required class="validate" maxlength="50">
                        <label for="txtNombre">Nombre</label>
                    </div>                       
                    <div class="input-field col l4 s12">
                        <input  id="txtRubro" type="text" name="rubro" required class="validate" maxlength="30">
                        <label for="txtRubro">Rubro</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input  id="txtCategoria" type="text" name="categoria" required class="validate" maxlength="25">
                        <label for="txtCategoria">Categoria</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input  id="txtDepartamento" type="text" name="departamento" required class="validate" minlength="5" maxlength="32">
                        <label for="txtDepartamento">Departamento</label>
                    </div>  
                    <div class="input-field col l4 s12">   
                        <jsp:include page="/Views/Contacto/select.jsp">                           
                            <jsp:param name="id" value="0" />  
                        </jsp:include>  
                        <span id="slContacto_error" style="color:red" class="helper-text"></span>
                    </div>
                </div>
                <div class="row">
                    <div class="col l12 s12">
                        <input type="submit" class="waves-effect waves-light btn blue"><i class="material-icons right">save</i>Guardar
                        <a href="" class="waves-effect waves-light btn blue"><i class="material-icons right">cancel</i>Cancelar</a>                          
                    </div>
                </div>
            </form>          
        </main>
                        
        <jsp:include page="/Views/Shared/footerBody.jsp" />   
        <script>
            function validarFormulario() {
                var result = true;
                var slContacto = document.getElementById("slContacto");
                var slContacto_error = document.getElementById("slContacto_error");
                if (slContacto.value == 0) {
                    slContacto_error.innerHTML = "El Contacto es obligatorio";
                    result = false;
                } else {
                    slContacto_error.innerHTML = "";
                }

                return result;
            }
        </script>
    </body>
</html>
