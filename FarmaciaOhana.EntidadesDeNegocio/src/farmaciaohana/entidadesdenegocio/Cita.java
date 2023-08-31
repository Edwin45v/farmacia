package farmaciaohana.entidadesdenegocio;

import java.time.LocalDateTime;

public class Cita {
    private int Id;
    private int IdAdministracionDoctores;
    private LocalDateTime Fecha;
    private double Hora;

    public Cita() {
    }

    public Cita(int Id, int IdAdministracionDoctores, LocalDateTime Fecha, double Hora) {
        this.Id = Id;
        this.IdAdministracionDoctores = IdAdministracionDoctores;
        this.Fecha = Fecha;
        this.Hora = Hora;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getIdAdministracionDoctores() {
        return IdAdministracionDoctores;
    }

    public void setIdAdministracionDoctores(int IdAdministracionDoctores) {
        this.IdAdministracionDoctores = IdAdministracionDoctores;
    }

    public LocalDateTime getFecha() {
        return Fecha;
    }

    public void setFecha(LocalDateTime Fecha) {
        this.Fecha = Fecha;
    }

    public double getHora() {
        return Hora;
    }

    public void setHora(double Hora) {
        this.Hora = Hora;
    }
    
    
}
