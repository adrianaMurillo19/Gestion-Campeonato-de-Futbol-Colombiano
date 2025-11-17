
package DAO;

import Modelo.Goleador;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoleadoresDAO {
    
    // usa tu clase de conexión existente
    public List<Goleador> obtenerTop10Goleadores(Connection conn) {
        List<Goleador> lista = new ArrayList<>();

        String sql = """
            SELECT
              j.id_jugador,
              TRIM(CONCAT(j.nombreUno, ' ', COALESCE(j.nombreDos, ''), ' ', j.apellidoUno, ' ', COALESCE(j.apellidoDos, ''))) AS nombre_completo,
              e.nombre AS equipo,
              COUNT(g.idGol) AS total_goles
            FROM gol g
            JOIN jugador j ON g.Jugador_idUsuario = j.id_jugador
            JOIN equipo e ON j.Equipo_idEquipo = e.idEquipo
            WHERE g.Jugador_idUsuario IS NOT NULL
            GROUP BY j.id_jugador, nombre_completo, e.nombre
            ORDER BY total_goles DESC
            LIMIT 10
            """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Goleador dto = new Goleador();
                dto.setIdJugador(rs.getInt("id_jugador"));
                dto.setNombreCompleto(rs.getString("nombre_completo"));
                dto.setEquipo(rs.getString("equipo"));
                dto.setTotalGoles(rs.getInt("total_goles"));
                lista.add(dto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }
}
