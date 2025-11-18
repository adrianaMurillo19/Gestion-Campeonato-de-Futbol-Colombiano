

package Modelo;


public class Ciudad {
    
    private int idCiudad;
    private String nombreCiudad;

    private String nombre;

    public Ciudad() {
    }

    public Ciudad(int idCiudad, String nombreCiudad, String nombre) {
        this.idCiudad = idCiudad;
        this.nombreCiudad = nombreCiudad;
        this.nombre = nombre;
    }

    public Ciudad(String nombreCiudad, String nombre) {
        this.nombreCiudad = nombreCiudad;
        this.nombre = nombre;
    }

    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    

   @Override
    public String toString() {
        return "Ciudad{" + 
                "idCiudad=" + idCiudad + 
                ", nombre=" + nombre + 
                '}';
    }
  


    
}
