package DTO;


public class EquipoDirectorCiudadDTO {
    private String nombreEquipo;
    private String nombreDT;
    private String ciudad;

    public EquipoDirectorCiudadDTO(String nombreEquipo, String nombreDT, String ciudad) {
        this.nombreEquipo = nombreEquipo;
        this.nombreDT = nombreDT;
        this.ciudad = ciudad;
    }

    public String getNombreEquipo() { return nombreEquipo; }
    public String getNombreDT() { return nombreDT; }
    public String getCiudad() { return ciudad; }
}
