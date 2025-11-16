package DTO;

import java.util.Date;

public class EquipoParticipacionDTO {

    private int idEquipo;
    private String nombreEquipo;
    private int puntos;
    private int diferenciaGoles;
    private Date fechaJuego;
    
    public EquipoParticipacionDTO() {
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

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getDiferenciaGoles() {
        return diferenciaGoles;
    }

    public void setDiferenciaGoles(int diferenciaGoles) {
        this.diferenciaGoles = diferenciaGoles;
    }

    public Date getFechaJuego() {
        return fechaJuego;
    }

    public void setFechaJuego(Date fechaJuego) {
        this.fechaJuego = fechaJuego;
    }
    
}
