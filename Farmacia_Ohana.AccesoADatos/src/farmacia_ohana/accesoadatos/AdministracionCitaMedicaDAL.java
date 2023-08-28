
package farmacia_ohana.accesoadatos;

import java.util.*;
import java.sql.*;
import java.time.LocalDate;
import farmaciaohana.entidadesdenegocio.*;

public class AdministracionCitaMedicaDAL {
    static String obtenerCampos() {
        return "c.Id, c.IdCitaMedica, c.Estado";
    }
    
    private static String obtenerSelect(AdministracionCitaMedica pAdministracionCitaMedica) {
        String sql;
        sql = "SELECT ";
        if (pAdministracionCitaMedica.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {            
            sql += "TOP " + pAdministracionCitaMedica.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " FROM Contacto c");
        return sql;
    }
    
    private static String agregarOrderBy(AdministracionCitaMedica pAdministracionCitaMedica) {
        String sql = " ORDER BY c.Id DESC";
        if (pAdministracionCitaMedica.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.MYSQL) {
            sql += " LIMIT " + pAdministracionCitaMedica.getTop_aux() + " ";
        }
        return sql;
    }
    
    public static int crear(AdministracionCitaMedica pAdministracionCitaMedica) throws Exception {
        int result;
        String sql;
        
        try ( Connection conn = ComunDB.obtenerConexion();) {
            sql = "INSERT INTO AdministracionCitaMedica(IdPedidos,Estado) VALUES(?,?,?,?,?)";
            try ( PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pAdministracionCitaMedica.getIdCitaMedica());
                ps.setString(2, pAdministracionCitaMedica.getEstado());
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
    
    public static int modificar(AdministracionCitaMedica pAdministracionCitaMedica) throws Exception {
        int result;
        String sql;
        try ( Connection conn = ComunDB.obtenerConexion();) {
            sql = "UPDATE AdministracionCitaMedica SET IdCitaMedica=?, Estado=? WHERE Id=?";
            try ( PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pAdministracionCitaMedica.getIdCitaMedica());
                ps.setString(2, pAdministracionCitaMedica.getEstado());
                ps.setInt(3, pAdministracionCitaMedica.getId());
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
    
    public static int eliminar(AdministracionCitaMedica pAdministracionCitaMedica) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) { 
            sql = "DELETE FROM AdministracionCitaMedica WHERE Id=?"; 
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pAdministracionCitaMedica.getId());
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
    
    static int asignarDatosResultSet(AdministracionCitaMedica pAdministracionCitaMedica, ResultSet pResultSet, int pIndex) throws Exception {
        pIndex++;
        pAdministracionCitaMedica.setId(pResultSet.getInt(pIndex)); 
        pIndex++;
        pAdministracionCitaMedica.setIdCitaMedica(pResultSet.getInt(pIndex)); 
        pIndex++;
        pAdministracionCitaMedica.setEstado(pResultSet.getString(pIndex)); 
        pIndex++;
        return pIndex;
    }
    
    private static void obtenerDatos(PreparedStatement pPS, ArrayList<AdministracionCitaMedica> pAdministracionCitaMedica) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) { 
            while (resultSet.next()) {
                AdministracionCitaMedica administracioncitamedica = new AdministracionCitaMedica();
                asignarDatosResultSet(administracioncitamedica, resultSet, 0);
                pAdministracionCitaMedica.add(administracioncitamedica);
            }
            resultSet.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }
    
    private static void obtenerDatosIncluirContacto(PreparedStatement pPS, ArrayList<AdministracionCitaMedica> pAdministracionCitaMedica) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) {
            //HashMap<Integer, Pedidos> contactoMap = new HashMap(); 
            while (resultSet.next()) {
                AdministracionCitaMedica administracioncitamedica = new AdministracionCitaMedica();
                int index = asignarDatosResultSet(administracioncitamedica, resultSet, 0);
                /*if (//contactoMap.containsKey(empresa.getIdContacto()) == false) {
                    Contacto contacto = new Contacto();
                    ContactoDAL.asignarDatosResultSet(contacto, resultSet, index);
                    contactoMap.put(contacto.getId(), contacto); 
                    empresa.setContacto(contacto); 
                } else {
                    empresa.setContacto(contactoMap.get(empresa.getIdContacto())); 
                }
                pEmpresas.add(empresa); 
            }
*/
 /*           resultSet.close();
        } catch (SQLException ex) {
            throw ex; 
        }
*/}}
    }
    
   
    public static AdministracionCitaMedica obtenerPorId(AdministracionCitaMedica pAdministracionCitaMedica) throws Exception {
        AdministracionCitaMedica administracioncitamedica = new AdministracionCitaMedica();
        ArrayList<AdministracionCitaMedica> administracioncitamedicas = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = obtenerSelect(pAdministracionCitaMedica);
            sql += " WHERE e.Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pAdministracionCitaMedica.getId());
                obtenerDatos(ps, administracioncitamedicas);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        }
        catch (SQLException ex) {
            throw ex;
        }
        if (administracioncitamedicas.size() > 0) {
            administracioncitamedica = administracioncitamedicas.get(0);
        }
        return administracioncitamedica;
    }

    
    public static ArrayList<AdministracionCitaMedica> obtenerTodos() throws Exception {
        ArrayList<AdministracionCitaMedica> administracioncitamedica = new ArrayList<>();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = obtenerSelect(new AdministracionCitaMedica()); 
            sql += agregarOrderBy(new AdministracionCitaMedica());
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                obtenerDatos(ps, administracioncitamedica);
                ps.close();
            } catch (SQLException ex) {
                throw ex; 
            }
            conn.close();
        }
        catch (SQLException ex) {
            throw ex;
        }
        return administracioncitamedica;
    }
    
    static void querySelect(AdministracionCitaMedica pAdministracionCitaMedica, ComunDB.utilQuery pUtilQuery) throws SQLException {
        PreparedStatement statement = pUtilQuery.getStatement();
        if (pAdministracionCitaMedica.getId() > 0) {
            pUtilQuery.AgregarNumWhere(" e.Id=? ");
            if (statement != null) {
                statement.setInt(pUtilQuery.getNumWhere(), pAdministracionCitaMedica.getId());
            }
        }

        if (pAdministracionCitaMedica.getIdCitaMedica()> 0) {
            pUtilQuery.AgregarNumWhere(" e.IdCitaMedica=? ");
            if (statement != null) {
                statement.setInt(pUtilQuery.getNumWhere(), pAdministracionCitaMedica.getIdCitaMedica());
            }
        }
        
        if (pAdministracionCitaMedica.getEstado() != null && pAdministracionCitaMedica.getEstado().trim().isEmpty() == false) {
            pUtilQuery.AgregarNumWhere(" e.Estado LIKE ? ");
            if (statement != null) {
                statement.setString(pUtilQuery.getNumWhere(), "%" + pAdministracionCitaMedica.getEstado() + "%");
            }
        }

    }
    
    public static ArrayList<AdministracionCitaMedica> buscar(AdministracionCitaMedica pAdministracionCitaMedica) throws Exception {
        ArrayList<AdministracionCitaMedica> administracioncitamedica = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = obtenerSelect(pAdministracionCitaMedica);
            ComunDB comundb = new ComunDB();
            ComunDB.utilQuery utilQuery = comundb.new utilQuery(sql, null, 0);
            querySelect(pAdministracionCitaMedica, utilQuery);
            sql = utilQuery.getSQL();
            sql += agregarOrderBy(pAdministracionCitaMedica);
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pAdministracionCitaMedica, utilQuery);
                obtenerDatos(ps, administracioncitamedica);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        } 
        catch (SQLException ex) {
            throw ex;
        }
        return administracioncitamedica;
    }
    
    /*public static ArrayList<AdministracionCitaMedica> buscarIncluirContacto(AdministracionPedidos pAdministracionPedidos) throws Exception {
        ArrayList<AdministracionPedidos> administracionpedidos = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = "SELECT ";
            if (pAdministracionPedidos.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {
                sql += "TOP " + pAdministracionPedidos.getTop_aux() + " "; 
            }
            sql += obtenerCampos();
            sql += ",";
            sql += AdministracionPedidosDAL.obtenerCampos();
            sql += " FROM AdministracionPedidos e";
            sql += " JOIN Pedidos c on (e.IdPedidos=c.Id)";
            ComunDB comundb = new ComunDB();
            ComunDB.utilQuery utilQuery = comundb.new utilQuery(sql, null, 0);
            querySelect(pAdministracionPedidos, utilQuery);
            sql = utilQuery.getSQL();
            sql += agregarOrderBy(pAdministracionPedidos);
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pAdministracionPedidos, utilQuery);
                obtenerDatosIncluirContacto(ps, administracionpedidos);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        } catch (SQLException ex) {
            throw ex;
        }
        return administracionpedidos;
    }
*/
}
