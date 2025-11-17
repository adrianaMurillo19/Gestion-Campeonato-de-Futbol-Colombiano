
package DAO;



import Modelo.EquipoSanciones;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipoSancionesDAO {

    private Connection conn;

    public EquipoSancionesDAO() {
        conn = DBConnection.conectar();  // usa tu clase de conexión
    }

    public List<EquipoSanciones> obtenerEquiposConSancionesAltas() {

        List<EquipoSanciones> lista = new ArrayList<>();

        String sql = """
            SELECT 
                e.idEquipo,
                e.nombre AS nombreEquipo,
                COUNT(js.Sancion_idSancion) AS totalSanciones
            FROM equipo e
            JOIN jugador j ON j.Equipo_idEquipo = e.idEquipo
            LEFT JOIN jugadorsancion js ON js.Jugador_idUsuario = j.id_jugador
            GROUP BY e.idEquipo, e.nombre
            HAVING COUNT(js.Sancion_idSancion) >
            (
                SELECT AVG(sancionesPorEquipo.totalSanciones)
                FROM (
                    SELECT 
                        e2.idEquipo,
                        COUNT(js2.Sancion_idSancion) AS totalSanciones
                    FROM equipo e2
                    JOIN jugador j2 ON j2.Equipo_idEquipo = e2.idEquipo
                    LEFT JOIN jugadorsancion js2 ON js2.Jugador_idUsuario = j2.id_jugador
                    GROUP BY e2.idEquipo
                ) AS sancionesPorEquipo
            );
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                EquipoSanciones dto = new EquipoSanciones();
                dto.setIdEquipo(rs.getInt("idEquipo"));
                dto.setNombreEquipo(rs.getString("nombreEquipo"));
                dto.setTotalSanciones(rs.getInt("totalSanciones"));
                lista.add(dto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
