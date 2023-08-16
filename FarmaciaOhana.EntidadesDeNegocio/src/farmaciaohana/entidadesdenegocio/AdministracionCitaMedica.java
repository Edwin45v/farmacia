package farmaciaohana.entidadesdenegocio;

public class AdministracionCitaMedica {
    private int id;
    private int idCitaMedica;
    private int top_aux;
    private int estado;

    public AdministracionCitaMedica() {
    }

    public AdministracionCitaMedica(int id, int idCitaMedica, int top_aux, int estado) {
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    
}
