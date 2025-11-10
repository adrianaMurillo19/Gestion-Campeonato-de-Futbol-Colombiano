
package Modelo;

import java.util.Date;
import java.time.LocalDateTime;

public class Arbitro {
    private int id_arbitro;
    private String nombreUno;
    private String nombreDos;
    private String apellidoUno;
    private String apellidoDos;
    private String cedula;
    private Date fechaNacimiento;
    private String tipo;

    public Arbitro() {
    }

    public Arbitro(int id_arbitro, String nombreUno, String nombreDos, String apellidoUno, String apellidoDos, String cedula, Date fechaNacimiento, String tipo) {
        this.id_arbitro = id_arbitro;
        this.nombreUno = nombreUno;
        this.nombreDos = nombreDos;
        this.apellidoUno = apellidoUno;
        this.apellidoDos = apellidoDos;
        this.cedula = cedula;
        this.fechaNacimiento = fechaNacimiento;
        this.tipo = tipo;
    }

    public Arbitro(String nombreUno, String nombreDos, String apellidoUno, String apellidoDos, String cedula, Date fechaNacimiento, String tipo) {
        this.nombreUno = nombreUno;
        this.nombreDos = nombreDos;
        this.apellidoUno = apellidoUno;
        this.apellidoDos = apellidoDos;
        this.cedula = cedula;
        this.fechaNacimiento = fechaNacimiento;
        this.tipo = tipo;
    }

    public int getId_arbitro() {
        return id_arbitro;
    }

    public void setId_arbitro(int id_arbitro) {
        this.id_arbitro = id_arbitro;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Arbitro{" + "id_arbitro=" + id_arbitro + ", nombreUno=" + nombreUno + ", nombreDos=" + nombreDos + ", apellidoUno=" + apellidoUno + ", apellidoDos=" + apellidoDos + ", cedula=" + cedula + ", fechaNacimiento=" + fechaNacimiento + ", tipo=" + tipo + '}';
    }
    
    
    
    
}
