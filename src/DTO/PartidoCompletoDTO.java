package DTO;

import Modelo.Arbitro;
import Modelo.Ciudad;
import Modelo.Equipo;
import Modelo.Estadio;
import Modelo.Gol;
import Modelo.Jugador;
import Modelo.Sustitucion;
import Modelo.Tarjeta;
import java.util.List;

import java.sql.*;

public class PartidoCompletoDTO {

    public PartidoCompletoDTO() {
    }
    
    

    // ========================
    // DATOS DEL PARTIDO
    // ========================
    private int idPartido;
    private Date fechaJuego;
    private Time horaInicio;
    private String resultadoFinal;

    // ========================
    // ESTADIO Y CIUDAD
    // ========================
    private Estadio estadio;
    private Ciudad ciudad;

    // ========================
    // ARBITRO PRINCIPAL
    // ========================
    private Arbitro arbitro;

    // ========================
    // EQUIPOS Y SUS JUGADORES
    // ========================
    private List<Equipo> equipos;
    private List<Jugador> jugadores;

    // ========================
    // EVENTOS DEL PARTIDO
    // ========================
    private List<Gol> goles;
    private List<Tarjeta> tarjetas;
    private List<Sustitucion> sustituciones;

   
    // ========================
    // GETTERS Y SETTERS
    // ========================
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

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getResultadoFinal() {
        return resultadoFinal;
    }

    public void setResultadoFinal(String resultadoFinal) {
        this.resultadoFinal = resultadoFinal;
    }

    public Estadio getEstadio() {
        return estadio;
    }

    public void setEstadio(Estadio estadio) {
        this.estadio = estadio;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Arbitro getArbitro() {
        return arbitro;
    }

    public void setArbitro(Arbitro arbitro) {
        this.arbitro = arbitro;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public List<Gol> getGoles() {
        return goles;
    }

    public void setGoles(List<Gol> goles) {
        this.goles = goles;
    }

    public List<Tarjeta> getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(List<Tarjeta> tarjetas) {
        this.tarjetas = tarjetas;
    }

    public List<Sustitucion> getSustituciones() {
        return sustituciones;
    }

    public void setSustituciones(List<Sustitucion> sustituciones) {
        this.sustituciones = sustituciones;
    }

    @Override
    public String toString() {
        return "PartidoCompletoDTO{" + "idPartido=" + idPartido + ", fechaJuego=" + fechaJuego + ", horaInicio=" + horaInicio + ", resultadoFinal=" + resultadoFinal + ", estadio=" + estadio + ", ciudad=" + ciudad + ", arbitro=" + arbitro + ", equipos=" + equipos + ", jugadores=" + jugadores + ", goles=" + goles + ", tarjetas=" + tarjetas + ", sustituciones=" + sustituciones + '}';
    }
    
    
}
