package farmaciaohana.appweb.controllers;

import farmacia_ohana.accesoadatos.CitaDAL;
import farmaciaohana.appweb.utils.Utilidad;
import farmaciaohana.entidadesdenegocio.Cita;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@WebServlet(name = "CitaServlet", urlPatterns = {"/Cita"})
public class CitaServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = Utilidad.getParameter(request, "accion", "index");

        try {
            if (accion == "create") {

            } else {
                ArrayList<Cita> citas = CitaDAL.GetAll();
                request.setAttribute("citas", citas);
                request.getRequestDispatcher("Views/Cita/index.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    // </editor-fold>

}
