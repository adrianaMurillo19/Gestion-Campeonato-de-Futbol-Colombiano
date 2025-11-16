package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Modelo.Partido;

public class PartidoDAO {

    Connection connection;

    public PartidoDAO(Connection connection) {
        this.connection = connection;
    }
    
    // Registrar nuevo partido
    public boolean registrarPartido(Object obj){
        Partido partido = (Partido) obj;
        boolean registrado = false;
        
        String sql = "INSERT INTO Partido (fechaJuego, horaInicio, resultadoFinal, Estadio_idEstadio, Arbitro_idUsuario) "
                   + "VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {   
            stmt.setDate(1, new java.sql.Date(partido.getFechaJuego().getTime()));
            stmt.setTime(2, Time.valueOf(partido.getHoraInicio()));
            stmt.setString(3, partido.getResultadoFinal());
            stmt.setInt(4, partido.getEstadio_idEstadio());
            stmt.setInt(5, partido.getArbitro_idUsuario());
            
            int rows = stmt.executeUpdate();
            if (rows > 0) registrado = true;
            
        } catch (Exception e) {
            System.out.println("Error al registrar partido: " + e.getMessage());
        }
        
        return registrado;
    }
    
    
    // Listar todos los partidos
    public List<Partido> obtenerPartidos(){
        
        List<Partido> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM Partido";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Partido partido = new Partido();
                
                partido.setIdPartido(rs.getInt("idPartido"));
                partido.setFechaJuego(rs.getDate("fechaJuego"));
                partido.setHoraInicio(rs.getString("horaInicio"));
                partido.setResultadoFinal(rs.getString("resultadoFinal"));
                partido.setEstadio_idEstadio(rs.getInt("Estadio_idEstadio"));
                partido.setArbitro_idUsuario(rs.getInt("Arbitro_idUsuario"));
                
                lista.add(partido);
            }
            
        } catch (Exception e) {
            System.out.println("Error al obtener partidos: " + e.getMessage());
        }
        
        return lista;
    }
    
    // Actualizar partido
    public boolean actualizarPartido(Partido partido){
        boolean actualizado = false;
        
        String sql = "UPDATE Partido SET fechaJuego = ?, horaInicio = ?, resultadoFinal = ?, "
                   + "Estadio_idEstadio = ?, Arbitro_idUsuario = ? WHERE idPartido = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(partido.getFechaJuego().getTime()));
            stmt.setTime(2, Time.valueOf(partido.getHoraInicio()));
            stmt.setString(3, partido.getResultadoFinal());
            stmt.setInt(4, partido.getEstadio_idEstadio());
            stmt.setInt(5, partido.getArbitro_idUsuario());
            stmt.setInt(6, partido.getIdPartido());
            
            int rows = stmt.executeUpdate();
            if (rows > 0) actualizado = true;
            
        } catch (Exception e) {
            System.out.println("Error al actualizar partido: " + e.getMessage());
        }
        
        return actualizado;
    }
    
    // Eliminar partido
    public boolean eliminarPartido(int id){
        boolean eliminado = false;
        
        String sql = "DELETE FROM Partido WHERE idPartido = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            
            if (rows > 0) eliminado = true;
            
        } catch (Exception e) {
            System.out.println("Error al eliminar partido: " + e.getMessage());
        }
        
        return eliminado;
    }

    // Método de main: historial ordenado
    public List<Partido> obtenerTodosLosPartidos(){
        List<Partido> lista = new ArrayList<>();

        String sql = "SELECT idPartido, fechaJuego, horaInicio, resultadoFinal, Estadio_idEstadio, Arbitro_idUsuario " +
                     "FROM Partido ORDER BY fechaJuego, horaInicio";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Partido p = new Partido();
                p.setIdPartido(rs.getInt("idPartido"));
                p.setFechaJuego(rs.getDate("fechaJuego"));
                p.setHoraInicio(rs.getString("horaInicio"));
                p.setResultadoFinal(rs.getString("resultadoFinal"));
                p.setEstadio_idEstadio(rs.getInt("Estadio_idEstadio"));
                p.setArbitro_idUsuario(rs.getInt("Arbitro_idUsuario"));

                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Método de main
    public List<Partido> obtenerTodosPartidosConsulta() {
        List<Partido> partidos = new ArrayList<>();
        String sql = "SELECT * FROM Partido";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Partido partido = new Partido(
                    rs.getInt("idPartido"),
                    rs.getDate("fechaJuego")
                );

                partidos.add(partido);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return partidos;
    }
    
    /*Obtener el ID del partido para consultar mediante ID
    */
    public Partido obtenerPartidoporId(int id_partido) throws SQLException{
        Partido partido = null;
        
        String sql = "SELECT idPartido, fechaJuego, horaInicio, resultadoFinal, Estadio_idEstadio, Arbitro_idUsuario FROM Partido WHERE idPartido = ?";
    
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id_partido);
            try (ResultSet rs = ps.executeQuery()) {
                
                if(rs.next()){
                    partido = new Partido();
                    partido.setIdPartido(rs.getInt("idPartido"));
                    partido.setFechaJuego(rs.getDate("fechaJuego"));
                    partido.setHoraInicio(rs.getString("horaInicio"));
                    partido.setResultadoFinal(rs.getString("resultadoFinal"));
                    partido.setEstadio_idEstadio(rs.getInt("Estadio_idEstadio"));
                    partido.setArbitro_idUsuario(rs.getInt("Arbitro_idUsuario"));
                }
            } 
        } 
        return partido;
    }
}
