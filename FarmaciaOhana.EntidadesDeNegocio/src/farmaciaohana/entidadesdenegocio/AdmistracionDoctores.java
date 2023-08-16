
package farmaciaohana.entidadesdenegocio;


public class AdmistracionDoctores {
    private int id;
    private String nombre;
    private String apellido;
    private String especialidda;
    private String genero;
    private String horario;
    private int top_aux;
    private int hora;

    public AdmistracionDoctores() {
    }

    public AdmistracionDoctores(int id, String nombre, String apellido, String especialidda, String genero, String horario, int top_aux, int hora) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidda = especialidda;
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

    public String getEspecialidda() {
        return especialidda;
    }

    public void setEspecialidda(String especialidda) {
        this.especialidda = especialidda;
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
