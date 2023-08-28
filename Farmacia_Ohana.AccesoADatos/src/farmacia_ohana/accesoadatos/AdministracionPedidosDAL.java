
package farmacia_ohana.accesoadatos;

import java.util.*;
import java.sql.*;
import farmaciaohana.entidadesdenegocio.*;


public class AdministracionPedidosDAL {
    static String obtenerCampos() {
        return "c.Id, c.IdPedidos, c.Estado";
    }
    
    private static String obtenerSelect(AdministracionPedidos pAdministracionPedidos) {
        String sql;
        sql = "SELECT ";
        if (pAdministracionPedidos.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {            
            sql += "TOP " + pAdministracionPedidos.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " FROM Contacto c");
        return sql;
    }
    
    private static String agregarOrderBy(AdministracionPedidos pAdministracionPedidos) {
        String sql = " ORDER BY c.Id DESC";
        if (pAdministracionPedidos.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.MYSQL) {
            sql += " LIMIT " + pAdministracionPedidos.getTop_aux() + " ";
        }
        return sql;
    }
    
    public static int crear(AdministracionPedidos pAdministracionPedidos) throws Exception {
        int result;
        String sql;
        
        try ( Connection conn = ComunDB.obtenerConexion();) {
            sql = "INSERT INTO Empresa(IdPedidos,Estado) VALUES(?,?,?,?,?)";
            try ( PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pAdministracionPedidos.getIdPedidos());
                ps.setString(2, pAdministracionPedidos.getEstado());
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
    
    public static int modificar(AdministracionPedidos pAdministracionPedidos) throws Exception {
        int result;
        String sql;
        try ( Connection conn = ComunDB.obtenerConexion();) {
            sql = "UPDATE Empresa SET IdPedido=?, Estado=? WHERE Id=?";
            try ( PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pAdministracionPedidos.getIdPedidos());
                ps.setString(2, pAdministracionPedidos.getEstado());
                ps.setInt(6, pAdministracionPedidos.getId());
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
    
    public static int eliminar(AdministracionPedidos pAdministracionPedidos) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) { 
            sql = "DELETE FROM Empresa WHERE Id=?"; 
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pAdministracionPedidos.getId());
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
    
    static int asignarDatosResultSet(AdministracionPedidos pAdministracionPedidos, ResultSet pResultSet, int pIndex) throws Exception {
        pIndex++;
        pAdministracionPedidos.setId(pResultSet.getInt(pIndex)); 
        pIndex++;
        pAdministracionPedidos.setIdPedidos(pResultSet.getInt(pIndex)); 
        pIndex++;
        pAdministracionPedidos.setEstado(pResultSet.getString(pIndex)); 
        pIndex++;
        return pIndex;
    }
    
    private static void obtenerDatos(PreparedStatement pPS, ArrayList<AdministracionPedidos> pAdministracionPedidos) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) { 
            while (resultSet.next()) {
                AdministracionPedidos administracionpedidos = new AdministracionPedidos();
                asignarDatosResultSet(administracionpedidos, resultSet, 0);
                pAdministracionPedidos.add(administracionpedidos);
            }
            resultSet.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }
    
    private static void obtenerDatosIncluirContacto(PreparedStatement pPS, ArrayList<AdministracionPedidos> pAdministracionPedidos) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) {
            //HashMap<Integer, Pedidos> contactoMap = new HashMap(); 
            while (resultSet.next()) {
                AdministracionPedidos administracionpedidos = new AdministracionPedidos();
                int index = asignarDatosResultSet(administracionpedidos, resultSet, 0);
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
    
   
    public static AdministracionPedidos obtenerPorId(AdministracionPedidos pAdministracionPedidos) throws Exception {
        AdministracionPedidos administracionpedido = new AdministracionPedidos();
        ArrayList<AdministracionPedidos> administracionpedidos = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = obtenerSelect(pAdministracionPedidos);
            sql += " WHERE e.Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pAdministracionPedidos.getId());
                obtenerDatos(ps, administracionpedidos);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        }
        catch (SQLException ex) {
            throw ex;
        }
        if (administracionpedidos.size() > 0) {
            administracionpedido = administracionpedidos.get(0);
        }
        return administracionpedido;
    }

    
    public static ArrayList<AdministracionPedidos> obtenerTodos() throws Exception {
        ArrayList<AdministracionPedidos> administracionpedidos = new ArrayList<>();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = obtenerSelect(new AdministracionPedidos()); 
            sql += agregarOrderBy(new AdministracionPedidos());
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                obtenerDatos(ps, administracionpedidos);
                ps.close();
            } catch (SQLException ex) {
                throw ex; 
            }
            conn.close();
        }
        catch (SQLException ex) {
            throw ex;
        }
        return administracionpedidos;
    }
    
    static void querySelect(AdministracionPedidos pAdministracionPedidos, ComunDB.utilQuery pUtilQuery) throws SQLException {
        PreparedStatement statement = pUtilQuery.getStatement();
        if (pAdministracionPedidos.getId() > 0) {
            pUtilQuery.AgregarNumWhere(" e.Id=? ");
            if (statement != null) {
                statement.setInt(pUtilQuery.getNumWhere(), pAdministracionPedidos.getId());
            }
        }

        if (pAdministracionPedidos.getIdPedidos()> 0) {
            pUtilQuery.AgregarNumWhere(" e.IdContacto=? ");
            if (statement != null) {
                statement.setInt(pUtilQuery.getNumWhere(), pAdministracionPedidos.getIdPedidos());
            }
        }
        
        if (pAdministracionPedidos.getEstado() != null && pAdministracionPedidos.getEstado().trim().isEmpty() == false) {
            pUtilQuery.AgregarNumWhere(" e.Nombre LIKE ? ");
            if (statement != null) {
                statement.setString(pUtilQuery.getNumWhere(), "%" + pAdministracionPedidos.getEstado() + "%");
            }
        }

    }
    
    public static ArrayList<AdministracionPedidos> buscar(AdministracionPedidos pAdministracionPedidos) throws Exception {
        ArrayList<AdministracionPedidos> administracionpedidos = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = obtenerSelect(pAdministracionPedidos);
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
                obtenerDatos(ps, administracionpedidos);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        } 
        catch (SQLException ex) {
            throw ex;
        }
        return administracionpedidos;
    }
    
    public static ArrayList<AdministracionPedidos> buscarIncluirContacto(AdministracionPedidos pAdministracionPedidos) throws Exception {
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
}
