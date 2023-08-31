package farmacia_ohana.accesoadatos;

import farmaciaohana.entidadesdenegocio.Cita;
import java.util.ArrayList;
import java.sql.*;

public class CitaDAL {
    
    public static ArrayList<Cita> GetAll() throws SQLException, Exception {
        ArrayList<Cita> citas = new ArrayList<>();
        try (Connection conn = ComunDB.obtenerConexion()) {
            try (Statement statement = conn.createStatement(); ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM Cita"
            )) {
                while (resultSet.next()) {
                    Cita cita = new Cita();
                    asignarDatosResultSet(cita, resultSet, 0);
                    citas.add(cita);
                }
            }
        } catch (SQLException e) {
            throw e;
        }
        return citas;
    }
    
    static int asignarDatosResultSet(Cita pCita, ResultSet pResultSet, int pIndex) throws Exception {
        pIndex++;
        pCita.setId(pResultSet.getInt(pIndex));
        pIndex++;
        pCita.setIdAdministracionDoctores(pResultSet.getInt(pIndex));
        pIndex++;
        pCita.setFecha(pResultSet.getTimestamp(pIndex).toLocalDateTime());
        pIndex++;
        pCita.setHora(pResultSet.getDouble(pIndex));
        return pIndex;
    }
}
