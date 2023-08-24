package farmaciaohana.entidadesdenegocio;

public class AdministracionCitaMedica {
    private int id;
    private int idCitaMedica;
    private int top_aux;
    private String estado;

    public AdministracionCitaMedica() {
    }

    public AdministracionCitaMedica(int id, int idCitaMedica, int top_aux, String estado) {
        this.id = id;
        this.idCitaMedica = idCitaMedica;
        this.top_aux = top_aux;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCitaMedica() {
        return idCitaMedica;
    }

    public void setIdCitaMedica(int idCitaMedica) {
        this.idCitaMedica = idCitaMedica;
    }

    public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
