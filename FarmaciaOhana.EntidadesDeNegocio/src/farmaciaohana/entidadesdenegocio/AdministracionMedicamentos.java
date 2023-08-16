package farmaciaohana.entidadesdenegocio;

import java.time.LocalDate;

public class AdministracionMedicamentos {
    private int id;
    private String nombre;
    private LocalDate fechaAdquisicion;
    private String distribuidora;
    private String existencias;
    private LocalDate fechaVencimiento;
    private String tipoMedicamento;
    private int precio;
    

    public AdministracionMedicamentos() {
    }

    public AdministracionMedicamentos(int id, String nombre, LocalDate fechaAdquisicion, String distribuidora, String existencias, LocalDate fechaVencimiento, String tipoMedicamento, int precio) {
        this.id = id;
        this.nombre = nombre;
        this.fechaAdquisicion = fechaAdquisicion;
        this.distribuidora = distribuidora;
        this.existencias = existencias;
        this.fechaVencimiento = fechaVencimiento;
        this.tipoMedicamento = tipoMedicamento;
        this.precio = precio;
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

    public LocalDate getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(LocalDate fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public String getDistribuidora() {
        return distribuidora;
    }

    public void setDistribuidora(String distribuidora) {
        this.distribuidora = distribuidora;
    }

    public String getExistencias() {
        return existencias;
    }

    public void setExistencias(String existencias) {
        this.existencias = existencias;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getTipoMedicamento() {
        return tipoMedicamento;
    }

    public void setTipoMedicamento(String tipoMedicamento) {
        this.tipoMedicamento = tipoMedicamento;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
