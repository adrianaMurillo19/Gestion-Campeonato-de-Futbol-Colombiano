
package Modelo;

import java.util.Date;


public class Jugador {

    private int id_jugador;
    private String nombreUno;
    private String nombreDos;
    private String apellidoUno;
    private String apellidoDos;
    private String cedula;
    private Date fechaNacimiento;
    private String contrato;
    private int numCamiseta;
    private int Equipo_idEquipo;

    
    //constructor sin ID
    public Jugador(String nombreUno, String nombreDos, String apellidoUno, String apellidoDos, String cedula, Date fechaNacimiento, String contrato, int numCamiseta, int Equipo_idEquipo) {
        this.nombreUno = nombreUno;
        this.nombreDos = nombreDos;
        this.apellidoUno = apellidoUno;
        this.apellidoDos = apellidoDos;
        this.cedula = cedula;
        this.fechaNacimiento = fechaNacimiento;
        this.contrato = contrato;
        this.numCamiseta = numCamiseta;
        this.Equipo_idEquipo = Equipo_idEquipo;
    }

    //constructor con Id

    public Jugador(int id_jugador, String nombreUno, String nombreDos, String apellidoUno, String apellidoDos, String cedula, Date fechaNacimiento, String contrato, int numCamiseta, int Equipo_idEquipo) {
        this.id_jugador = id_jugador;
        this.nombreUno = nombreUno;
        this.nombreDos = nombreDos;
        this.apellidoUno = apellidoUno;
        this.apellidoDos = apellidoDos;
        this.cedula = cedula;
        this.fechaNacimiento = fechaNacimiento;
        this.contrato = contrato;
        this.numCamiseta = numCamiseta;
        this.Equipo_idEquipo = Equipo_idEquipo;
    }
    
    
    //constructor vacio
    public Jugador(){}
    
    public Jugador(int id_jugador){
        this.id_jugador = id_jugador;
    }

    public int getId_jugador() {
        return id_jugador;
    }

    public void setId_jugador(int id_jugador) {
        this.id_jugador = id_jugador;
    }

    public String getNombreUno() {
        return nombreUno;
    }

    public void setNombreUno(String nombreUno) {
        this.nombreUno = nombreUno;
    }

    public String getNombreDos() {
        return nombreDos;
    }

    public void setNombreDos(String nombreDos) {
        this.nombreDos = nombreDos;
    }

    public String getApellidoUno() {
        return apellidoUno;
    }

    public void setApellidoUno(String apellidoUno) {
        this.apellidoUno = apellidoUno;
    }

    public String getApellidoDos() {
        return apellidoDos;
    }

    public void setApellidoDos(String apellidoDos) {
        this.apellidoDos = apellidoDos;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public int getNumCamiseta() {
        return numCamiseta;
    }

    public void setNumCamiseta(int numCamiseta) {
        this.numCamiseta = numCamiseta;
    }

    public int getEquipo_idEquipo() {
        return Equipo_idEquipo;
    }

    public void setEquipo_idEquipo(int Equipo_idEquipo) {
        this.Equipo_idEquipo = Equipo_idEquipo;
    }
    
    

    @Override
    public String toString() {
        return "Jugador{" + "id_jugador=" + id_jugador + ", nombreUno=" + nombreUno + ", nombreDos=" + nombreDos + ", apellidoUno=" + apellidoUno + ", apellidoDos=" + apellidoDos + ", cedula=" + cedula + ", fechaNacimiento=" + fechaNacimiento + ", contrato=" + contrato + ", numCamiseta=" + numCamiseta + ", Equipo_idEquipo=" + Equipo_idEquipo + '}';
    }

    
    
    
}
