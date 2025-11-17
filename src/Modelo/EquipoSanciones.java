
package Modelo;


public class EquipoSanciones {
    
    private int idEquipo;
    private String nombreEquipo;
    private int totalSanciones;

    public EquipoSanciones() {}

    public EquipoSanciones(int idEquipo, String nombreEquipo, int totalSanciones) {
        this.idEquipo = idEquipo;
        this.nombreEquipo = nombreEquipo;
        this.totalSanciones = totalSanciones;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public int getTotalSanciones() {
        return totalSanciones;
    }

    public void setTotalSanciones(int totalSanciones) {
        this.totalSanciones = totalSanciones;
    }
}
