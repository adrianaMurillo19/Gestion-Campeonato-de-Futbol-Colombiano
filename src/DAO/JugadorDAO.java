package DAO;
import Modelo.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class JugadorDAO {
    
    Connection connection;
    Jugador jugador;
    
    
    public JugadorDAO (Connection connection){
        this.connection = connection;  
}
    
    /* Este metodo permite realizar un nuevo registro de un Jugador
    */
    public boolean registrarJugador(Object obj){
        
        jugador = (Jugador) obj;
        
        PreparedStatement stmt = null;
        boolean registrado = false;
        
        String sql = "INSERT INTO Jugador (nombreUno, nombreDos, apellidoUno, apellidoDos, cedula, fechaNacimiento, contrato, numCamiseta, Equipo_idEquipo)" +
                      "VALUES (?,?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, jugador.getNombreUno());
            stmt.setString(2, jugador.getNombreDos());
            stmt.setString(3, jugador.getApellidoUno());
            stmt.setString(4, jugador.getApellidoDos());
            stmt.setString(5, jugador.getCedula());
            stmt.setDate(6, new java.sql.Date(jugador.getFechaNacimiento().getTime())); //stmt.setDate(1, new java.sql.Date(tratamiento.getFecha_inicio().getTime()));
            stmt.setString(7, jugador.getContrato());
            stmt.setInt(8, jugador.getNumCamiseta());
            stmt.setInt(9, jugador.getEquipo_idEquipo());
            
            int rows = stmt.executeUpdate();
            if (rows >0){
                registrado = true;
            }
        
        } catch (Exception e) {
            System.out.println("Error al registrar jugador" + e.getMessage());
        }
        return registrado;
    }

    /* Este metodo permite listar todos los Jugadores
    */
    public List<Jugador> obtenerJugadores() {
        
        List<Jugador> lista = new ArrayList<>();
        String sql = "SELECT * FROM Jugador";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()){
            
            while (rs.next()){
                Jugador jugador = new Jugador();
                jugador.setId_jugador(rs.getInt("id_jugador"));
                jugador.setNombreUno(rs.getString("nombreUno"));
                jugador.setNombreDos(rs.getString("nombreDos"));
                jugador.setApellidoUno(rs.getString("apellidoUno"));
                jugador.setApellidoDos(rs.getString("apellidoDos"));
                jugador.setCedula(rs.getString("cedula"));
                jugador.setFechaNacimiento(rs.getDate("fechaNacimiento"));
                jugador.setContrato(rs.getString("contrato"));
                jugador.setNumCamiseta(rs.getInt("numCamiseta"));
                jugador.setEquipo_idEquipo(rs.getInt("Equipo_idEquipo"));
                lista.add(jugador);
            }
           
        } catch (Exception e) {
            e.printStackTrace();
        }
            return lista;
    }

    public boolean actualizarJugador(Jugador jugador) {
        boolean actualizado = false;
        PreparedStatement stmt = null;
        
        String sql = "UPDATE Jugador SET " + 
                    "nombreUno = ?, " +
                    "nombreDos = ?, " +
                    "apellidoUno = ?, " +
                    "apellidoDos = ?, " +
                    "cedula = ?, " +
                    "fechaNacimiento = ?, " +
                    "contrato = ?, " +
                    "numCamiseta = ?, " +
                    "Equipo_idEquipo = ? " +
                    "WHERE id_jugador = ?";
    
        try {
                stmt = connection.prepareStatement(sql);
                stmt.setString(1, jugador.getNombreUno());
                stmt.setString(2, jugador.getNombreDos());
                stmt.setString(3, jugador.getApellidoUno());
                stmt.setString(4, jugador.getApellidoDos());
                stmt.setString(5, jugador.getCedula());
                stmt.setDate(6, new java.sql.Date(jugador.getFechaNacimiento().getTime()));
                stmt.setString(7, jugador.getContrato());
                stmt.setInt(8, jugador.getNumCamiseta());
                stmt.setInt(9, jugador.getEquipo_idEquipo());
                stmt.setInt(10, jugador.getId_jugador());
                
                int filas = stmt.executeUpdate();
                
                if(filas >0){
                    actualizado = true;
                }
        } catch (Exception e) {
            System.out.println("Error al actualizar el jugador_*DAO: " + e.getMessage());
        }
            return actualizado;
    }
    
    public boolean eliminarJugador(int id_jugador){
        
        boolean eliminado = false;
        
        String sql = "DELETE FROM Jugador WHERE id_jugador = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id_jugador);
            int filas = stmt.executeUpdate();
            
            if(filas >0){
                eliminado = true;
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar jugador_DAO" + e.getMessage());
        }
        return eliminado;
    }
    
    /*Obtener el ID del jugador para consultar mediante ID
    */
    public Jugador obtenerJugadorporId(int id_jugador) throws SQLException{
        Jugador jugador = null;
        
        String sql = "SELECT id_jugador, nombreUno, nombreDos, apellidoUno, apellidoDos, cedula, fechaNacimiento, contrato, numCamiseta, Equipo_idEquipo FROM Jugador WHERE id_jugador = ?";
    
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id_jugador);
            try (ResultSet rs = ps.executeQuery()) {
                
                if(rs.next()){
                    jugador = new Jugador();
                    jugador.setId_jugador(rs.getInt("id_jugador"));
                    jugador.setNombreUno(rs.getString("nombreUno"));
                    jugador.setNombreDos(rs.getString("nombreDos"));
                    jugador.setApellidoUno(rs.getString("apellidoUno"));
                    jugador.setApellidoDos(rs.getString("apellidoDos"));
                    jugador.setCedula(rs.getString("cedula"));
                    jugador.setFechaNacimiento(rs.getDate("fechaNacimiento"));
                    jugador.setContrato(rs.getString("contrato"));
                    jugador.setNumCamiseta(rs.getInt("numCamiseta"));
                    jugador.setEquipo_idEquipo(rs.getInt("Equipo_idEquipo"));
                }
            } 
        } 
        return jugador;
    }
    
    
    /*Este método permite obtener los jugadores que sean mayores a cierta edad
    */
    public List<Jugador> obtenerJugadoresMayoresA(int edadMinima){
        List<Jugador> lista = new ArrayList<>();
        
        String sql = "SELECT j.nombreUno, j.apellidoUno, j.fechaNacimiento, " +
                     "TIMESTAMPDIFF(YEAR, j.fechaNacimiento, CURDATE()) AS edad " +
                     "FROM Jugador j " + 
                     "WHERE TIMESTAMPDIFF(YEAR, j.fechaNacimiento, CURDATE()) >= ? " +
                     "ORDER BY j.fechaNacimiento";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            
            stmt.setInt(1, edadMinima);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Jugador jugador = new Jugador();
                jugador.setNombreUno(rs.getString("nombreUno"));
                jugador.setApellidoUno(rs.getString("apellidoUno"));
                jugador.setFechaNacimiento(rs.getDate("fechaNacimiento"));
                jugador.setEdad(rs.getInt("edad"));//este campo es el que se calcula en la consulta
                lista.add(jugador);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
}
