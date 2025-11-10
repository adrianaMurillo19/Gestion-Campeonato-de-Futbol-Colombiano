
package Modelo;

import java.util.Date;
import java.time.LocalDateTime;


public class Partido {
    
    private int idPartido;
    private Date fechaJuego;
    private String horaInicio; //Time
    private String resultadoFinal;
    private int Estadio_idEstadio;
    private int Arbitro_idUsuario;
    
   
    

    public Partido() {
    }

    public Partido(int idPartido, Date fechaJuego, String horaInicio, String resultadoFinal, int Estadio_idEstadio, int Arbitro_idUsuario) {
        this.idPartido = idPartido;
        this.fechaJuego = fechaJuego;
        this.horaInicio = horaInicio;
        this.resultadoFinal = resultadoFinal;
        this.Estadio_idEstadio = Estadio_idEstadio;
        this.Arbitro_idUsuario = Arbitro_idUsuario;
    }

    public Partido(Date fechaJuego, String horaInicio, String resultadoFinal, int Estadio_idEstadio, int Arbitro_idUsuario) {
        this.fechaJuego = fechaJuego;
        this.horaInicio = horaInicio;
        this.resultadoFinal = resultadoFinal;
        this.Estadio_idEstadio = Estadio_idEstadio;
        this.Arbitro_idUsuario = Arbitro_idUsuario;
    }

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

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
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
