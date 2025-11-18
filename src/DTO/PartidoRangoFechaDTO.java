package DTO;

import java.util.Date;

public class PartidoRangoFechaDTO {

    private int idPartido;
    private Date fechaJuego;
    private String horaInicio;
    private String resultadoFinal;
    private String nombreEstadio;

    public PartidoRangoFechaDTO() {}

    public PartidoRangoFechaDTO(int idPartido, Date fechaJuego, String horaInicio,
                                String resultadoFinal, String nombreEstadio) {
        this.idPartido = idPartido;
        this.fechaJuego = fechaJuego;
        this.horaInicio = horaInicio;
        this.resultadoFinal = resultadoFinal;
        this.nombreEstadio = nombreEstadio;
    }

    // Getters y setters

    public int getIdPartido() { return idPartido; }
    public void setIdPartido(int idPartido) { this.idPartido = idPartido; }

    public Date getFechaJuego() { return fechaJuego; }
    public void setFechaJuego(Date fechaJuego) { this.fechaJuego = fechaJuego; }

    public String getHoraInicio() { return horaInicio; }
    public void setHoraInicio(String horaInicio) { this.horaInicio = horaInicio; }

    public String getResultadoFinal() { return resultadoFinal; }
    public void setResultadoFinal(String resultadoFinal) { this.resultadoFinal = resultadoFinal; }

    public String getNombreEstadio() { return nombreEstadio; }
    public void setNombreEstadio(String nombreEstadio) { this.nombreEstadio = nombreEstadio; }
}
