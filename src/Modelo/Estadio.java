
package Modelo;


public class Estadio {
    
    private int idEstadio;
    private String nombre;
    private int capacidad;
    private int Ciudad_idCiudad;

    public Estadio() {
    }

    public Estadio(String nombre, int capacidad, int Ciudad_idCiudad) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.Ciudad_idCiudad = Ciudad_idCiudad;
    }

    
    public Estadio(int idEstadio, String nombre, int capacidad, int Ciudad_idCiudad) {
        this.idEstadio = idEstadio;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.Ciudad_idCiudad = Ciudad_idCiudad;
    }

    public int getIdEstadio() {
        return idEstadio;
    }

    public void setIdEstadio(int idEstadio) {
        this.idEstadio = idEstadio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getCiudad_idCiudad() {
        return Ciudad_idCiudad;
    }

    public void setCiudad_idCiudad(int Ciudad_idCiudad) {
        this.Ciudad_idCiudad = Ciudad_idCiudad;
    }

    @Override
    public String toString() {
        return "Estadio{" + "idEstadio=" + idEstadio + ", nombre=" + nombre + ", capacidad=" + capacidad + ", Ciudad_idCiudad=" + Ciudad_idCiudad + '}';
    }
    
    
    
    
}
