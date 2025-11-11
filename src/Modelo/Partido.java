package Modelo;

import java.time.LocalTime;
import java.util.Date;

public class Partido {

    public int idPartido;
    public Date fechaJuego;
    public LocalTime horaInicio;
    public String resultadoFinal;
    public int Estadio_idEstadio;
    public int Arbitro_idUsuario;
    
    // Constructor con id
    public Partido(int idPartido, Date fechaJuego, LocalTime horaInicio, String resultadoFinal, int Estadio_idEstadio, int Arbitro_idUsuario) {
        this.idPartido = idPartido;
        this.fechaJuego = fechaJuego;
        this.horaInicio = horaInicio;
        this.resultadoFinal = resultadoFinal;
        this.Estadio_idEstadio = Estadio_idEstadio;
        this.Arbitro_idUsuario = Arbitro_idUsuario;
    }

    // Constructor sin id
    public Partido(Date fechaJuego, LocalTime horaInicio, String resultadoFinal, int Estadio_idEstadio, int Arbitro_idUsuario) {
        this.fechaJuego = fechaJuego;
        this.horaInicio = horaInicio;
        this.resultadoFinal = resultadoFinal;
        this.Estadio_idEstadio = Estadio_idEstadio;
        this.Arbitro_idUsuario = Arbitro_idUsuario;
    }
    
    // Constructor vacío
    public Partido() {
    }
    
    // Gets y sets
    public int getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }

    public Date getFechaJuego() {
        return fechaJuego;
    }

    public void setFechaJuego(Date fechaJuego) {
        this.fechaJuego = fechaJuego;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getResultadoFinal() {
        return resultadoFinal;
    }

    public void setResultadoFinal(String resultadoFinal) {
        this.resultadoFinal = resultadoFinal;
    }

    public int getEstadio_idEstadio() {
        return Estadio_idEstadio;
    }

    public void setEstadio_idEstadio(int Estadio_idEstadio) {
        this.Estadio_idEstadio = Estadio_idEstadio;
    }

    public int getArbitro_idUsuario() {
        return Arbitro_idUsuario;
    }

    public void setArbitro_idUsuario(int Arbitro_idUsuario) {
        this.Arbitro_idUsuario = Arbitro_idUsuario;
    }

    
    @Override
    public String toString() {
        return "Partido{" + "idPartido=" + idPartido + ", fechaJuego=" + fechaJuego + ", horaInicio=" + horaInicio + ", resultadoFinal=" + resultadoFinal + ", Estadio_idEstadio=" + Estadio_idEstadio + ", Arbitro_idUsuario=" + Arbitro_idUsuario + '}';
    }
}
