
package Modelo;

public class Equipo {
    
    private int idEquipo;
    private String nombre;
    private int TablaPosiciones_idTabla;
    private int DirectorTecnico_idUsuario;
    private int Ciudad_idCiudad;

    public Equipo() {
    }

    
    public Equipo(int idEquipo, String nombre, int TablaPosiciones_idTabla, int DirectorTecnico_idUsuario, int Ciudad_idCiudad) {
        this.idEquipo = idEquipo;
        this.nombre = nombre;
        this.TablaPosiciones_idTabla = TablaPosiciones_idTabla;
        this.DirectorTecnico_idUsuario = DirectorTecnico_idUsuario;
        this.Ciudad_idCiudad = Ciudad_idCiudad;
    }

    public Equipo(int idEquipo, String nombre) {
        this.idEquipo = idEquipo;
        this.nombre = nombre;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTablaPosiciones_idTabla() {
        return TablaPosiciones_idTabla;
    }

    public void setTablaPosiciones_idTabla(int TablaPosiciones_idTabla) {
        this.TablaPosiciones_idTabla = TablaPosiciones_idTabla;
    }

    public int getDirectorTecnico_idUsuario() {
        return DirectorTecnico_idUsuario;
    }

    public void setDirectorTecnico_idUsuario(int DirectorTecnico_idUsuario) {
        this.DirectorTecnico_idUsuario = DirectorTecnico_idUsuario;
    }

    public int getCiudad_idCiudad() {
        return Ciudad_idCiudad;
    }

    public void setCiudad_idCiudad(int Ciudad_idCiudad) {
        this.Ciudad_idCiudad = Ciudad_idCiudad;
    }

    @Override
    public String toString() {
        return "Equipo{" + "idEquipo=" + idEquipo + ", nombre=" + nombre + '}';
    }
    
    
    
    
}
