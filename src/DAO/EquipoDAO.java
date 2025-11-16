package DAO;

import Modelo.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EquipoDAO {

    Connection conn;
    
    public EquipoDAO(Connection conn) {
        this.conn = conn;
    }

    /*Lista todos los registros de Equipo
    */
    public List<Equipo> obtenerTodos() {
        
        List<Equipo> equipos = new ArrayList<>();
        String sql = "SELECT * FROM equipo";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()){
            while (rs.next()){
                Equipo equipo = new Equipo(
                        rs.getInt("idEquipo"),
                        rs.getString("nombre")
                );
                
                equipos.add(equipo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return equipos;
    }
    
    
    public Equipo obtenerPorId(int idEquipo) {

        String sql = "SELECT * FROM equipo WHERE idEquipo = ?";
        Equipo equipo = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEquipo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                equipo = new Equipo(
                    rs.getInt("idEquipo"),
                    rs.getString("nombre")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return equipo;
    }
    
    
    // Reporte intermedio #4: Obtener los jugadores de un equipo
    public List<Jugador> obtenerJugadoresPorEquipo(int idEquipo) {
        List<Jugador> lista = new ArrayList<>();

        String sql = "SELECT e.nombre AS nombreEquipo, " +
                     "       j.nombreUno, j.nombreDos, j.apellidoUno, j.apellidoDos, " +
                     "       j.numCamiseta " +
                     "FROM Equipo e " +
                     "JOIN Jugador j ON j.Equipo_idEquipo = e.idEquipo " +
                     "WHERE e.idEquipo = ? " +
                     "ORDER BY j.numCamiseta";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEquipo);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Jugador jugador = new Jugador();
                jugador.setNombreUno(rs.getString("nombreUno"));
                jugador.setNombreDos(rs.getString("nombreDos"));
                jugador.setApellidoUno(rs.getString("apellidoUno"));
                jugador.setApellidoDos(rs.getString("apellidoDos"));
                jugador.setNumCamiseta(rs.getInt("numCamiseta"));

                lista.add(jugador);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    
    
    
    
}
