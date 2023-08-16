
package farmaciaohana.entidadesdenegocio;


public class AdministracionPedidos {
    private int id;
    private int idPedidos;
    private int top_aux;
    private String Estado;

    public AdministracionPedidos() {
    }

    public AdministracionPedidos(int id, int idPedidos, int top_aux, String Estado) {
        this.id = id;
        this.idPedidos = idPedidos;
        this.top_aux = top_aux;
        this.Estado = Estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPedidos() {
        return idPedidos;
    }

    public void setIdPedidos(int idPedidos) {
        this.idPedidos = idPedidos;
    }

    public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }
    
    
}
