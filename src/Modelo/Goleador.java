
package Modelo;


public class Goleador {
    
     private int idJugador;
    private String nombreCompleto;
    private String equipo;
    private int totalGoles;

    public Goleador() {}

    public Goleador(int idJugador, String nombreCompleto, String equipo, int totalGoles) {
        this.idJugador = idJugador;
        this.nombreCompleto = nombreCompleto;
        this.equipo = equipo;
        this.totalGoles = totalGoles;
    }

    public int getIdJugador() { return idJugador; }
    public void setIdJugador(int idJugador) { this.idJugador = idJugador; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getEquipo() { return equipo; }
    public void setEquipo(String equipo) { this.equipo = equipo; }

    public int getTotalGoles() { return totalGoles; }
    public void setTotalGoles(int totalGoles) { this.totalGoles = totalGoles; }
    
}
