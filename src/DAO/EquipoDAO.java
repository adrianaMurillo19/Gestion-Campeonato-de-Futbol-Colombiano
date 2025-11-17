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
    
    // Registrar nuevo equipo (con verificación de Director Técnico único)
    public boolean registrarEquipo(Object obj) {
        Equipo equipo = (Equipo) obj;
        boolean registrado = false;

        String sqlVerificarDT = "SELECT COUNT(*) FROM Equipo WHERE DirectorTecnico_idUsuario = ?";
        String sqlTabla = "INSERT INTO TablaPosiciones (puntos, diferenciaGoles) VALUES (0, 0)";
        String sqlEquipo = "INSERT INTO Equipo (nombre, DirectorTecnico_idUsuario, Ciudad_idCiudad, TablaPosiciones_idTabla) "
                         + "VALUES (?, ?, ?, ?)";

        try {
            conn.setAutoCommit(false); // INICIA TRANSACCIÓN

            // 0. Verificar si el DT ya tiene equipo
            try (PreparedStatement stmtVerif = conn.prepareStatement(sqlVerificarDT)) {
                stmtVerif.setInt(1, equipo.getDirectorTecnico_idUsuario());
                try (ResultSet rs = stmtVerif.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        conn.rollback();
                        System.out.println("El Director Técnico ya está asignado a otro equipo.");
                        return false;
                    }
                }
            }

            // 1. Crear tabla de posiciones
            int idTabla = -1;
            try (PreparedStatement stmt1 = conn.prepareStatement(sqlTabla, Statement.RETURN_GENERATED_KEYS)) {
                stmt1.executeUpdate();
                try (ResultSet rs = stmt1.getGeneratedKeys()) {
                    if (rs.next()) idTabla = rs.getInt(1);
                }
            }

            if (idTabla == -1) {
                conn.rollback();
                System.out.println("No se pudo generar la tabla de posiciones.");
                return false;
            }

            // 2. Crear el equipo con la tabla recién creada
            try (PreparedStatement stmt2 = conn.prepareStatement(sqlEquipo)) {

                stmt2.setString(1, equipo.getNombre());
                stmt2.setInt(2, equipo.getDirectorTecnico_idUsuario());
                stmt2.setInt(3, equipo.getCiudad_idCiudad());
                stmt2.setInt(4, idTabla);

                int rows = stmt2.executeUpdate();
                if (rows > 0) registrado = true;
            }

            conn.commit();

        } catch (Exception e) {

            try { conn.rollback(); } catch (SQLException ex) {}

            System.out.println("Error al registrar equipo: " + e.getMessage());
        } finally {
            try { conn.setAutoCommit(true); } catch (SQLException ex) {}
        }

        return registrado;
    }



    // Listar todos los equipos
    public List<Equipo> obtenerEquipos() {

        List<Equipo> lista = new ArrayList<>();

        String sql = "SELECT * FROM Equipo";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Equipo equipo = new Equipo();

                equipo.setIdEquipo(rs.getInt("idEquipo"));
                equipo.setNombre(rs.getString("nombre"));
                equipo.setDirectorTecnico_idUsuario(rs.getInt("DirectorTecnico_idUsuario"));
                equipo.setCiudad_idCiudad(rs.getInt("Ciudad_idCiudad"));

                lista.add(equipo);
            }

        } catch (Exception e) {
            System.out.println("Error al obtener equipos: " + e.getMessage());
        }

        return lista;
    }

    // Actualizar equipo (con verificación de Director técnico único)
    public boolean actualizarEquipo(Equipo equipo) {
        boolean actualizado = false;

        String sqlVerificarDT = "SELECT idEquipo FROM Equipo WHERE DirectorTecnico_idUsuario = ?";
        String sqlUpdate = "UPDATE Equipo SET nombre = ?, DirectorTecnico_idUsuario = ?, "
                         + "Ciudad_idCiudad = ? WHERE idEquipo = ?";

        try {
            // 1. Verificar si el DT ya está asignado a otro equipo
            try (PreparedStatement stmtVerif = conn.prepareStatement(sqlVerificarDT)) {
                stmtVerif.setInt(1, equipo.getDirectorTecnico_idUsuario());
                try (ResultSet rs = stmtVerif.executeQuery()) {
                    if (rs.next()) {
                        int idEquipoExistente = rs.getInt("idEquipo");

                        // El DT ya está en otro equipo → no se puede actualizar
                        if (idEquipoExistente != equipo.getIdEquipo()) {
                            System.out.println("El Director Técnico ya está asignado a otro equipo.");
                            return false;
                        }
                    }
                }
            }

            // 2. Realizar la actualización
            try (PreparedStatement stmt = conn.prepareStatement(sqlUpdate)) {

                stmt.setString(1, equipo.getNombre());
                stmt.setInt(2, equipo.getDirectorTecnico_idUsuario());
                stmt.setInt(3, equipo.getCiudad_idCiudad());
                stmt.setInt(4, equipo.getIdEquipo());

                int rows = stmt.executeUpdate();
                if (rows > 0) actualizado = true;
            }

        } catch (Exception e) {
            System.out.println("Error al actualizar equipo: " + e.getMessage());
        }

        return actualizado;
    }


    // Eliminar equipo
    public boolean eliminarEquipo(int idEquipo) {
        boolean eliminado = false;

        String sqlSelectTabla = "SELECT TablaPosiciones_idTabla FROM Equipo WHERE idEquipo = ?";
        String sqlDeleteEquipo = "DELETE FROM Equipo WHERE idEquipo = ?";
        String sqlDeleteTabla = "DELETE FROM TablaPosiciones WHERE idTabla = ?";

        try {
            conn.setAutoCommit(false); // transacción

            int idTabla = -1;

            // 1. Obtener idTabla
            try (PreparedStatement stmt1 = conn.prepareStatement(sqlSelectTabla)) {
                stmt1.setInt(1, idEquipo);
                try (ResultSet rs = stmt1.executeQuery()) {
                    if (rs.next()) {
                        idTabla = rs.getInt("TablaPosiciones_idTabla");
                    }
                }
            }

            if (idTabla == -1) {
                conn.rollback();
                return false;
            }

            // 2. Borrar equipo
            try (PreparedStatement stmt2 = conn.prepareStatement(sqlDeleteEquipo)) {
                stmt2.setInt(1, idEquipo);
                stmt2.executeUpdate();
            }

            // 3. Borrar tabla de posiciones asociada
            try (PreparedStatement stmt3 = conn.prepareStatement(sqlDeleteTabla)) {
                stmt3.setInt(1, idTabla);
                stmt3.executeUpdate();
            }

            conn.commit();
            eliminado = true;

        } catch (Exception e) {

            try { conn.rollback(); } catch (SQLException ex) {}

            System.out.println("Error al eliminar equipo: " + e.getMessage());

        } finally {
            try { conn.setAutoCommit(true); } catch (SQLException ex) {}
        }

        return eliminado;
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
