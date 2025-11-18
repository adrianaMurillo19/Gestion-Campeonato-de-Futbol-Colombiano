
package Modelo;

import java.util.Date;


public class DirectorTecnico {
    
     private int id_arbitro;

    private int id_director_tecnico;
    private String nombreUno;
    private String nombreDos;
    private String apellidoUno;
    private String apellidoDos;
    private String cedula;
    private Date fechaNacimiento;
    private String tipo;
    private String experiencia;


    public DirectorTecnico() {
    }

   

    public DirectorTecnico(int id_director_tecnico, String nombreUno, String nombreDos, String apellidoUno, String apellidoDos, String cedula, Date fechaNacimiento, String experiencia) {
        this.id_director_tecnico = id_director_tecnico;

        this.nombreUno = nombreUno;
        this.nombreDos = nombreDos;
        this.apellidoUno = apellidoUno;
        this.apellidoDos = apellidoDos;
        this.cedula = cedula;
        this.fechaNacimiento = fechaNacimiento;
        this.tipo = tipo;
    }

    public DirectorTecnico(int id_director_tecnico, String nombreUno, String nombreDos, String apellidoUno, String apellidoDos, String cedula, Date fechaNacimiento, String tipo, String experiencia) {
        this.id_director_tecnico = id_director_tecnico;
        this.nombreUno = nombreUno;
        this.nombreDos = nombreDos;
        this.apellidoUno = apellidoUno;
        this.apellidoDos = apellidoDos;
        this.cedula = cedula;
        this.fechaNacimiento = fechaNacimiento;
        this.tipo = tipo;
        this.experiencia = experiencia;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    

    

    public int getId_arbitro() {
        return id_arbitro;
    }

    public void setId_arbitro(int id_arbitro) {
        this.id_arbitro = id_arbitro;

        this.experiencia = experiencia;
    }

    public DirectorTecnico(int id_director_tecnico, String nombreUno,
                           String apellidoUno, String experiencia) {
        this.id_director_tecnico = id_director_tecnico;
        this.nombreUno = nombreUno;
        this.apellidoUno = apellidoUno;
        this.experiencia = experiencia;
    }

    public int getId_director_tecnico() {
        return id_director_tecnico;
    }

    public void setId_director_tecnico(int id_director_tecnico) {
        this.id_director_tecnico = id_director_tecnico;
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
        return "DirectorTecnico{" +
                "id_director_tecnico=" + id_director_tecnico +
                ", nombreUno=" + nombreUno +
                ", apellidoUno=" + apellidoUno +
                '}';
    } 

}
    
    
 


