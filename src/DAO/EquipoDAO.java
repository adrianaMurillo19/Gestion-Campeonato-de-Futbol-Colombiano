package DAO;

import DTO.EquipoDirectorCiudadDTO;
import DTO.EquipoParticipacionDTO;
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
    
    // Reporte intermedio #6: Obtener del equipo datos de la tabla de posiciones + partidos en los que participa
    public List<EquipoParticipacionDTO> obtenerInfoEquipoConPartidos(int idEquipo) {

        List<EquipoParticipacionDTO> lista = new ArrayList<>();

        String sql = "SELECT e.idEquipo, e.nombre AS nombreEquipo, " +
                     "       tp.puntos, tp.diferenciaGoles, " +
                     "       p.fechaJuego " +
                     "FROM Equipo e " +
                     "LEFT JOIN TablaPosiciones tp ON tp.idTabla = e.TablaPosiciones_idTabla " +
                     "LEFT JOIN EquipoPartido dep ON dep.Equipo_idEquipo = e.idEquipo " +
                     "LEFT JOIN Partido p ON p.idPartido = dep.Partido_idPartido " +
                     "WHERE e.idEquipo = ? " +
                     "ORDER BY p.fechaJuego";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEquipo);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                EquipoParticipacionDTO dto = new EquipoParticipacionDTO();

                dto.setIdEquipo(rs.getInt("idEquipo"));
                dto.setNombreEquipo(rs.getString("nombreEquipo"));
                dto.setPuntos(rs.getInt("puntos"));
                dto.setDiferenciaGoles(rs.getInt("diferenciaGoles"));
                dto.setFechaJuego(rs.getDate("fechaJuego"));

                lista.add(dto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Reporte intermedio #7: Obtener el nombre y apellido del director técnico + la ciudad de un equipo
    public List<EquipoDirectorCiudadDTO> obtenerEquiposConDTyCiudad() {
        List<EquipoDirectorCiudadDTO> lista = new ArrayList<>();

        String sql = "SELECT e.nombre AS nombreEquipo, " +
                     "       CONCAT(dt.nombreUno, ' ', dt.apellidoUno) AS nombreDT, " +
                     "       c.nombre AS ciudad " +
                     "FROM Equipo e " +
                     "JOIN DirectorTecnico dt ON e.DirectorTecnico_idUsuario = dt.id_director_tecnico " +
                     "JOIN Ciudad c ON e.Ciudad_idCiudad = c.idCiudad " +
                     "ORDER BY e.nombre";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                EquipoDirectorCiudadDTO dto = new EquipoDirectorCiudadDTO(
                    rs.getString("nombreEquipo"),
                    rs.getString("nombreDT"),
                    rs.getString("ciudad")
                );

                lista.add(dto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    
    
    
}
