
package farmaciaohana.entidadesdenegocio;


public class AdministracionDoctores {
    private int id;
    private String nombre;
    private String apellido;
    private String especialidad;
    private String genero;
    private String horario;
    private int top_aux;
    private int hora;

    public AdministracionDoctores() {
    }

    public AdministracionDoctores(int id, String nombre, String apellido, String especialidad, String genero, String horario, int top_aux, int hora) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
        this.genero = genero;
        this.horario = horario;
        this.top_aux = top_aux;
        this.hora = hora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }
    
            
    
}
