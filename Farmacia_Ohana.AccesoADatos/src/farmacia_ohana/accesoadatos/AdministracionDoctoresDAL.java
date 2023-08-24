
package farmacia_ohana.accesoadatos;

import java.util.*;
import java.sql.*;
import farmaciaohana.entidadesdenegocio.*;

public class AdministracionDoctoresDAL {
    static String obtenerCampos() {
        return "c.Id, c.Nombre, c.Apellido, c.Especialidad, c.Genero, c.Horario, c.Hora";
    }
    
    private static String obtenerSelect(AdministracionDoctores pAdministracionDoctores) {
        String sql;
        sql = "SELECT ";
        if (pAdministracionDoctores.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {            
            sql += "TOP " + pAdministracionDoctores.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " FROM Contacto c");
        return sql;
    }
    
    private static String agregarOrderBy(AdministracionDoctores pAdministracionDoctores) {
        String sql = " ORDER BY c.Id DESC";
        if (pAdministracionDoctores.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.MYSQL) {
            sql += " LIMIT " + pAdministracionDoctores.getTop_aux() + " ";
        }
        return sql;
    }
    
    public static int crear(AdministracionDoctores pAdministracionDoctores) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) { 
            sql = "INSERT INTO AdministracionDoctores(Nombre, Apellido, Especialidad, Genero, Horario, Hora) VALUES(?, ?, ?, ?)";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setString(1, pAdministracionDoctores.getNombre());
                ps.setString(2, pAdministracionDoctores.getApellido());
                ps.setString(3, pAdministracionDoctores.getEspecialidad());
                ps.setString(4, pAdministracionDoctores.getGenero());
                ps.setString(5, pAdministracionDoctores.getHorario());
                ps.setInt(6, pAdministracionDoctores.getHora());
                result = ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        } catch (SQLException ex) {
            throw ex;
        }
        return result;
    }
    
    public static int modificar(AdministracionDoctores pAdministracionDoctores) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "UPDATE Contacto SET Nombre=?, Apellido = ?, Especialidad = ?, Genero = ?, Horario, Hora WHERE Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setString(1, pAdministracionDoctores.getNombre());
                ps.setString(2, pAdministracionDoctores.getApellido());
                ps.setString(3, pAdministracionDoctores.getEspecialidad());
                ps.setString(4, pAdministracionDoctores.getGenero());
                ps.setString(5, pAdministracionDoctores.getHorario());
                ps.setInt(6, pAdministracionDoctores.getHora());
                ps.setInt(7, pAdministracionDoctores.getId());
                result = ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        } catch (SQLException ex) {
            throw ex;
        }
        return result;
    }
    
    public static int eliminar(AdministracionDoctores pAdministracionDoctores) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "DELETE FROM Contacto WHERE Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pAdministracionDoctores.getId());
                result = ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        } catch (SQLException ex) {
            throw ex;
        }
        return result;
    }
    
    static int asignarDatosResultSet(AdministracionDoctores pAdministracionDoctores, ResultSet pResultSet, int pIndex) throws Exception {
        pIndex++;
        pAdministracionDoctores.setId(pResultSet.getInt(pIndex));
        pIndex++;
        pAdministracionDoctores.setNombre(pResultSet.getString(pIndex));
        pIndex++;
        pAdministracionDoctores.setApellido(pResultSet.getString(pIndex));
        pIndex++;
        pAdministracionDoctores.setEspecialidad(pResultSet.getString(pIndex));
        pIndex++;
        pAdministracionDoctores.setGenero(pResultSet.getString(pIndex));
        pIndex++;
        pAdministracionDoctores.setHorario(pResultSet.getString(pIndex));
        pIndex++;
        //pAdministracionDoctores.setHora(pResultSet.getInt(pIndex));
        pIndex++;
        return pIndex;
    }
    
    private static void obtenerDatos(PreparedStatement pPS, ArrayList<AdministracionDoctores> pAdministracionDoctores) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) {
            while (resultSet.next()) {
                AdministracionDoctores administraciondoctores = new AdministracionDoctores(); 
                asignarDatosResultSet(administraciondoctores, resultSet, 0);
                pAdministracionDoctores.add(administraciondoctores);
            }
            resultSet.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }
    
    public static AdministracionDoctores obtenerPorId(AdministracionDoctores pAdministracionDoctores) throws Exception {
        AdministracionDoctores administraciondoctore = new AdministracionDoctores();
        ArrayList<AdministracionDoctores> administraciondoctores = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) { 
            String sql = obtenerSelect(pAdministracionDoctores);
            sql += " WHERE c.Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pAdministracionDoctores.getId());
                obtenerDatos(ps, administraciondoctores);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        }
        catch (SQLException ex) {
            throw ex;
        }
        if (administraciondoctores.size() > 0) {
            administraciondoctore = administraciondoctores.get(0);
        }
        return administraciondoctore;
    }
    
    public static ArrayList<AdministracionDoctores> obtenerTodos() throws Exception {
        ArrayList<AdministracionDoctores> administraciondoctores = new ArrayList<>();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = obtenerSelect(new AdministracionDoctores());
            sql += agregarOrderBy(new AdministracionDoctores());
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                obtenerDatos(ps, administraciondoctores);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        } 
        catch (SQLException ex) {
            throw ex;
        }
        return administraciondoctores;
    }
    
    static void querySelect(AdministracionDoctores pAdministracionDoctores, ComunDB.utilQuery pUtilQuery) throws SQLException {
        PreparedStatement statement = pUtilQuery.getStatement();
        if (pAdministracionDoctores.getId() > 0) {
            pUtilQuery.AgregarNumWhere(" c.Id=? ");
            if (statement != null) { 
                statement.setInt(pUtilQuery.getNumWhere(), pAdministracionDoctores.getId()); 
            }
        }

        if (pAdministracionDoctores.getNombre() != null && pAdministracionDoctores.getNombre().trim().isEmpty() == false) {
            pUtilQuery.AgregarNumWhere(" c.Nombre LIKE ? "); 
            if (statement != null) {
                statement.setString(pUtilQuery.getNumWhere(), "%" + pAdministracionDoctores.getNombre() + "%"); 
            }
        }
        
        if (pAdministracionDoctores.getApellido()!= null && pAdministracionDoctores.getApellido().trim().isEmpty() == false) {
            pUtilQuery.AgregarNumWhere(" c.Apellido LIKE ? "); 
            if (statement != null) {
                statement.setString(pUtilQuery.getNumWhere(), "%" + pAdministracionDoctores.getApellido()+ "%"); 
            }
        }
        
        if (pAdministracionDoctores.getEspecialidad()!= null && pAdministracionDoctores.getEspecialidad().trim().isEmpty() == false) {
            pUtilQuery.AgregarNumWhere(" c.Especialidad LIKE ? "); 
            if (statement != null) {
                statement.setString(pUtilQuery.getNumWhere(), "%" + pAdministracionDoctores.getEspecialidad()+ "%"); 
            }
        }
        
        if (pAdministracionDoctores.getGenero()!= null && pAdministracionDoctores.getGenero().trim().isEmpty() == false) {
            pUtilQuery.AgregarNumWhere(" c.Genero LIKE ? "); 
            if (statement != null) {
                statement.setString(pUtilQuery.getNumWhere(), "%" + pAdministracionDoctores.getGenero()+ "%"); 
            }
        }
        if (pAdministracionDoctores.getHorario()!= null && pAdministracionDoctores.getHorario().trim().isEmpty() == false) {
            pUtilQuery.AgregarNumWhere(" c.Horario LIKE ? "); 
            if (statement != null) {
                statement.setString(pUtilQuery.getNumWhere(), "%" + pAdministracionDoctores.getHorario()+ "%"); 
            }
        }
        if (pAdministracionDoctores.getHora() > 0) {
            pUtilQuery.AgregarNumWhere(" c.Hora LIKE ? ");
            if (statement != null) { 
                statement.setInt(pUtilQuery.getNumWhere(), pAdministracionDoctores.getHora()); 
            }
        }
        
    }
    
    public static ArrayList<AdministracionDoctores> buscar(AdministracionDoctores pAdministracionDoctores) throws Exception {
        ArrayList<AdministracionDoctores> administraciondoctores = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = obtenerSelect(pAdministracionDoctores);
            ComunDB comundb = new ComunDB();
            ComunDB.utilQuery utilQuery = comundb.new utilQuery(sql, null, 0); 
            querySelect(pAdministracionDoctores, utilQuery);
            sql = utilQuery.getSQL(); 
            sql += agregarOrderBy(pAdministracionDoctores);
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0); 
                querySelect(pAdministracionDoctores, utilQuery);
                obtenerDatos(ps, administraciondoctores);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        }
        catch (SQLException ex) {
            throw ex;
        }
        return administraciondoctores;
    }
}
