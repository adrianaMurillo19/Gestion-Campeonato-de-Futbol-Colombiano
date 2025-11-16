package Modelo;


public class Usuario {
    
    private int id_usuario;
    private String nombre_usuario;
    private String contrasenia;
    private int id_jugador;//
    private int id_arbitro;
    private int id_director_tecnico;
    
    public Usuario(){
        
    }

    //constructor con id_jugador incluido
    public Usuario(int id_usuario, String nombre_usuario, String contrasenia, int id_jugador) {
        
        this.nombre_usuario = nombre_usuario;
        this.contrasenia = contrasenia;
        this.id_jugador = id_jugador;
    }
    
    //constructor con id_arbitro
    public Usuario( String nombre_usuario, String contrasenia, int id_arbitro) {
        
        this.nombre_usuario = nombre_usuario;
        this.contrasenia = contrasenia;
        this.id_arbitro = id_arbitro;
    }
    
    
    
     
    public Usuario(int id_usuario, String nombre_usuario, String contrasenia) {
        this.id_usuario = id_usuario;
        this.nombre_usuario = nombre_usuario;
        this.contrasenia = contrasenia;
    }
    
    
    public Usuario(String nombre_usuario, String contrasenia) {
        this.nombre_usuario = nombre_usuario;
        this.contrasenia = contrasenia;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public int getId_jugador() {
        return id_jugador;
    }

    public void setId_jugador(int id_jugador) {
        this.id_jugador = id_jugador;
    }

    public int getId_arbitro() {
        return id_arbitro;
    }

    public void setId_arbitro(int id_arbitro) {
        this.id_arbitro = id_arbitro;
    }

    public int getId_director_tecnico() {
        return id_director_tecnico;
    }

    public void setId_director_tecnico(int id_director_tecnico) {
        this.id_director_tecnico = id_director_tecnico;
    }
    
    
    public Usuario(int id_usuario){
        this.id_usuario = id_usuario;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id_usuario=" + id_usuario + ", nombre_usuario=" + nombre_usuario + ", contrasenia=" + contrasenia + '}';
    }
    
    
    
}
