
package Modelo;


public class Sustitucion {
    
    private int idSustitucion;
    private String descripcion;

    public Sustitucion() {
    }

    public Sustitucion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Sustitucion(int idSustitucion, String descripcion) {
        this.idSustitucion = idSustitucion;
        this.descripcion = descripcion;
    }

    public int getIdSustitucion() {
        return idSustitucion;
    }

    public void setIdSustitucion(int idSustitucion) {
        this.idSustitucion = idSustitucion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Sustitucion{" + "idSustitucion=" + idSustitucion + ", descripcion=" + descripcion + '}';
    }
    
    
    
}
