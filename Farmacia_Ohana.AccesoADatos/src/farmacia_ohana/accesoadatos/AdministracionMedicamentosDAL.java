
package farmacia_ohana.accesoadatos;

import java.util.*;
import java.sql.*;
import farmaciaohana.entidadesdenegocio.*;

public class AdministracionMedicamentosDAL {
    static String obtenerCampos() {
        return "c.Id, c.Nombre,c.FechaAdquisicion,c.Distribuidora,c.Existencias,c.FechaVencimiento,c.TipoMedicamento,c.Precio";
    }
    
    private static String obtenerSelect(AdministracionMedicamentos pAdministracionMedicamentos) {
        String sql;
        sql = "SELECT ";
        if (pAdministracionMedicamentos.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {            
            sql += "TOP " + pAdministracionMedicamentos.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " FROM Contacto c");
        return sql;
    }
    
    private static String agregarOrderBy(AdministracionMedicamentos pAdministracionMedicamentos) {
        String sql = " ORDER BY c.Id DESC";
        if (pAdministracionMedicamentos.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.MYSQL) {
            sql += " LIMIT " + pAdministracionMedicamentos.getTop_aux() + " ";
        }
        return sql;
    }
    
    public static int crear(AdministracionMedicamentos pAdministracionMedicamentos) throws Exception {
        int result;
        String sql;
        
        try ( Connection conn = ComunDB.obtenerConexion();) {
            sql = "INSERT INTO AdministracionMedicamentos(Nombre,FechaAdquisicion,Distribuidora,Existencias,FechaVencimiento,TipoMedicamento,Precio) VALUES(?,?,?,?,?)";
            try ( PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setString(1, pAdministracionMedicamentos.getNombre());
                ps.setString(2, pAdministracionMedicamentos.getDistribuidora());
                ps.setString(3, pAdministracionMedicamentos.getExistencias());
                ps.setString(4, pAdministracionMedicamentos.getTipoMedicamento());
                //ps.get(5, pAdministracionMedicamentos.getPrecio());
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
    
    public static int modificar(AdministracionMedicamentos pAdministracionMedicamentos) throws Exception {
        int result;
        String sql;
        try ( Connection conn = ComunDB.obtenerConexion();) {
            sql = "UPDATE Empresa SET , Nombre=?,FechaAdquisicion=?,Distribuidora=?,Existencias=?,FechaVencimiento=?,TipoMedicamento=?,Precio=? WHERE Id=?";
            try ( PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setString(1, pAdministracionMedicamentos.getNombre());
                ps.setString(2, pAdministracionMedicamentos.getDistribuidora());
                ps.setString(3, pAdministracionMedicamentos.getExistencias());
                ps.setString(4, pAdministracionMedicamentos.getTipoMedicamento());
                ps.setInt(5, pAdministracionMedicamentos.getPrecio());
                ps.setInt(6, pAdministracionMedicamentos.getId());
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
    
    public static int eliminar(AdministracionMedicamentos pAdministracionMedicamentos) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) { 
            sql = "DELETE FROM AdministracionMedicamentos WHERE Id=?"; 
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pAdministracionMedicamentos.getId());
                result = ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        }
        catch (SQLException ex) {
            throw ex;
        }
        return result;
    }
    
    static int asignarDatosResultSet(AdministracionMedicamentos pAdministracionMedicamentos, ResultSet pResultSet, int pIndex) throws Exception {
        pIndex++;
        pAdministracionMedicamentos.setId(pResultSet.getInt(pIndex)); 
        pIndex++;
        pAdministracionMedicamentos.setNombre(pResultSet.getString(pIndex)); 
        pIndex++;
        pAdministracionMedicamentos.setDistribuidora(pResultSet.getString(pIndex)); 
        pIndex++;
        pAdministracionMedicamentos.setExistencias(pResultSet.getString(pIndex)); 
        pIndex++;
        pAdministracionMedicamentos.setTipoMedicamento(pResultSet.getString(pIndex)); 
        pIndex++;
        pAdministracionMedicamentos.setExistencias(pResultSet.getString(pIndex)); 
        pIndex++;
        pAdministracionMedicamentos.setPrecio(pResultSet.getInt(pIndex)); 
        pIndex++; 
        return pIndex;
    }
    
    private static void obtenerDatos(PreparedStatement pPS, ArrayList<AdministracionMedicamentos> pAdministracionMedicamentos) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) { 
            while (resultSet.next()) {
                AdministracionMedicamentos administracionmedicamentos = new AdministracionMedicamentos();
                asignarDatosResultSet(administracionmedicamentos, resultSet, 0);
                pAdministracionMedicamentos.add(administracionmedicamentos);
            }
            resultSet.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }
    
    
    public static AdministracionMedicamentos obtenerPorId(AdministracionMedicamentos pAdministracionMedicamentos) throws Exception {
        AdministracionMedicamentos administracionmedicamento = new AdministracionMedicamentos();
        ArrayList<AdministracionMedicamentos> administracionmedicamentos = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) { 
            String sql = obtenerSelect(pAdministracionMedicamentos);
            sql += " WHERE c.Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pAdministracionMedicamentos.getId());
                obtenerDatos(ps, administracionmedicamentos);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        }
        catch (SQLException ex) {
            throw ex;
        }
        if (administracionmedicamentos.size() > 0) {
            administracionmedicamento = administracionmedicamentos.get(0);
        }
        return administracionmedicamento;
    }
    
    public static ArrayList<AdministracionMedicamentos> obtenerTodos() throws Exception {
        ArrayList<AdministracionMedicamentos> administracionmedicamentos = new ArrayList<>();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = obtenerSelect(new AdministracionMedicamentos());
            sql += agregarOrderBy(new AdministracionMedicamentos());
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                obtenerDatos(ps, administracionmedicamentos);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        } 
        catch (SQLException ex) {
            throw ex;
        }
        return administracionmedicamentos;
    }
    
    static void querySelect(AdministracionMedicamentos pAdministracionMedicamentos, ComunDB.utilQuery pUtilQuery) throws SQLException {
        PreparedStatement statement = pUtilQuery.getStatement();
        if (pAdministracionMedicamentos.getId() > 0) {
            pUtilQuery.AgregarNumWhere(" c.Id=? ");
            if (statement != null) { 
                statement.setInt(pUtilQuery.getNumWhere(), pAdministracionMedicamentos.getId()); 
            }
        }

        if (pAdministracionMedicamentos.getNombre() != null && pAdministracionMedicamentos.getNombre().trim().isEmpty() == false) {
            pUtilQuery.AgregarNumWhere(" c.Nombre LIKE ? "); 
            if (statement != null) {
                statement.setString(pUtilQuery.getNumWhere(), "%" + pAdministracionMedicamentos.getNombre() + "%"); 
            }
        }
        
        if (pAdministracionMedicamentos.getDistribuidora()!= null && pAdministracionMedicamentos.getDistribuidora().trim().isEmpty() == false) {
            pUtilQuery.AgregarNumWhere(" c.Distribuidora LIKE ? "); 
            if (statement != null) {
                statement.setString(pUtilQuery.getNumWhere(), "%" + pAdministracionMedicamentos.getDistribuidora()+ "%"); 
            }
        }
        
        if (pAdministracionMedicamentos.getExistencias()!= null && pAdministracionMedicamentos.getExistencias().trim().isEmpty() == false) {
            pUtilQuery.AgregarNumWhere(" c.Existencias LIKE ? "); 
            if (statement != null) {
                statement.setString(pUtilQuery.getNumWhere(), "%" + pAdministracionMedicamentos.getExistencias()+ "%"); 
            }
        }
        
        if (pAdministracionMedicamentos.getTipoMedicamento()!= null && pAdministracionMedicamentos.getTipoMedicamento().trim().isEmpty() == false) {
            pUtilQuery.AgregarNumWhere(" c.TipoMedicamento LIKE ? "); 
            if (statement != null) {
                statement.setString(pUtilQuery.getNumWhere(), "%" + pAdministracionMedicamentos.getTipoMedicamento()+ "%"); 
            }
        }
        
        if (pAdministracionMedicamentos.getPrecio() > 0) {
            pUtilQuery.AgregarNumWhere(" c.Precio LIKE ? ");
            if (statement != null) { 
                statement.setInt(pUtilQuery.getNumWhere(), pAdministracionMedicamentos.getPrecio()); 
            }
        }
    }
    
    public static ArrayList<AdministracionMedicamentos> buscar(AdministracionMedicamentos pAdministracionMedicamentos) throws Exception {
        ArrayList<AdministracionMedicamentos> administracionmedicamentos = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = obtenerSelect(pAdministracionMedicamentos);
            ComunDB comundb = new ComunDB();
            ComunDB.utilQuery utilQuery = comundb.new utilQuery(sql, null, 0); 
            querySelect(pAdministracionMedicamentos, utilQuery);
            sql = utilQuery.getSQL(); 
            sql += agregarOrderBy(pAdministracionMedicamentos);
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0); 
                querySelect(pAdministracionMedicamentos, utilQuery);
                obtenerDatos(ps, administracionmedicamentos);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        }
        catch (SQLException ex) {
            throw ex;
        }
        return administracionmedicamentos;
    }
}
