
package DAO;

import Modelo.Arbitro;
import Modelo.Ciudad;
import Modelo.Equipo;
import Modelo.Estadio;
import Modelo.Gol;
import Modelo.Jugador;
import Modelo.Partido;
import Modelo.PartidoCompletoDTO;
import Modelo.Sustitucion;
import Modelo.Tarjeta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import javax.swing.JOptionPane;

public class PartidoCompletoDAO {
    
      private Connection conn;

    public PartidoCompletoDAO() {
        conn = DBConnection.conectar(); 
    }

   
    // =========================================================
    //  MÉTODO PRINCIPAL
    // =========================================================
    public PartidoCompletoDTO obtenerPartidoCompleto(int idPartido) throws SQLException{

        PartidoCompletoDTO dto = new PartidoCompletoDTO();

        try {
            // ============================================
            // 1. DATOS BÁSICOS DEL PARTIDO + ESTADIO + CIUDAD + ARBITRO
            // ============================================
            String sql = """
                SELECT p.idPartido, p.fechaJuego, p.horaInicio, p.resultadoFinal,
                       e.idEstadio, e.nombre AS estadioNombre, e.capacidad,
                       c.idCiudad, c.nombre AS ciudadNombre,
                       a.id_arbitro, a.nombreUno, a.nombreDos, a.apellidoUno, a.apellidoDos,
                       a.cedula, a.fechaNacimiento, a.tipo
                FROM partido p
                JOIN estadio e ON p.Estadio_idEstadio = e.idEstadio
                JOIN ciudad c ON e.Ciudad_idCiudad = c.idCiudad
                JOIN arbitro a ON p.Arbitro_idUsuario = a.id_arbitro
                WHERE p.idPartido = ?
            """;

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idPartido);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                dto.setIdPartido(rs.getInt("idPartido"));
                dto.setFechaJuego(rs.getDate("fechaJuego"));
                dto.setHoraInicio(rs.getTime("horaInicio"));
                dto.setResultadoFinal(rs.getString("resultadoFinal"));

                // Estadio
                Estadio estadio = new Estadio();
                estadio.setIdEstadio(rs.getInt("idEstadio"));
                estadio.setNombre(rs.getString("estadioNombre"));
                estadio.setCapacidad(rs.getInt("capacidad"));
                dto.setEstadio(estadio);

                // Ciudad
                Ciudad ciudad = new Ciudad();
                ciudad.setIdCiudad(rs.getInt("idCiudad"));
                ciudad.setNombreCiudad(rs.getString("ciudadNombre"));
                dto.setCiudad(ciudad);

                // Árbitro
                Arbitro arbitro = new Arbitro();
                arbitro.setId_arbitro(rs.getInt("id_arbitro"));
                arbitro.setNombreUno(rs.getString("nombreUno"));
                arbitro.setNombreDos(rs.getString("nombreDos"));
                arbitro.setApellidoUno(rs.getString("apellidoUno"));
                arbitro.setApellidoDos(rs.getString("apellidoDos"));
                arbitro.setCedula(rs.getString("cedula"));
                arbitro.setFechaNacimiento(rs.getDate("fechaNacimiento"));
                arbitro.setTipo(rs.getString("tipo"));
                dto.setArbitro(arbitro);
            }

            // ============================================
            // 2. EQUIPOS PARTICIPANTES
            // ============================================
            String sqlEquipos = """
                SELECT eq.idEquipo, eq.nombre
                FROM equipopartido ep
                JOIN equipo eq ON ep.Equipo_idEquipo = eq.idEquipo
                WHERE ep.Partido_idPartido = ?
            """;

            ps = conn.prepareStatement(sqlEquipos);
            ps.setInt(1, idPartido);
            rs = ps.executeQuery();

            List<Equipo> equipos = new ArrayList<>();

            while (rs.next()) {
                Equipo eq = new Equipo();
                eq.setIdEquipo(rs.getInt("idEquipo"));
                eq.setNombre(rs.getString("nombre"));
                equipos.add(eq);
            }

            dto.setEquipos(equipos);

            // ============================================
            // 3. JUGADORES PARTICIPANTES (jugadorpartido)
            // ============================================
            String sqlJugadores = """
                SELECT j.id_jugador, j.nombreUno, j.nombreDos,
                       j.apellidoUno, j.apellidoDos,
                       j.numCamiseta
                FROM jugadorpartido jp
                JOIN jugador j ON jp.Jugador_idUsuario = j.id_jugador
                WHERE jp.Partido_idPartido = ?
            """;

            ps = conn.prepareStatement(sqlJugadores);
            ps.setInt(1, idPartido);
            rs = ps.executeQuery();

            List<Jugador> jugadores = new ArrayList<>();

            while (rs.next()) {
                Jugador jug = new Jugador();
                jug.setId_jugador(rs.getInt("id_jugador"));
                jug.setNombreUno(rs.getString("nombreUno"));
                jug.setNombreDos(rs.getString("nombreDos"));
                jug.setApellidoUno(rs.getString("apellidoUno"));
                jug.setApellidoDos(rs.getString("apellidoDos"));
                jug.setNumCamiseta(rs.getInt("numCamiseta"));
                jugadores.add(jug);
            }

            dto.setJugadores(jugadores);

            // ============================================
            // 4. GOLES
            // ============================================
            List<Gol> goles = new ArrayList<>();

            String sqlGoles = "SELECT idGol, minuto FROM gol WHERE Partido_idPartido = ?";

            ps = conn.prepareStatement(sqlGoles);
            ps.setInt(1, idPartido);
            rs = ps.executeQuery();

            while (rs.next()) {
                Gol gol = new Gol();
                gol.setIdGol(rs.getInt("idGol"));
                gol.setMinuto(rs.getInt("minuto"));
                goles.add(gol);
            }

            dto.setGoles(goles);

            // ============================================
            // 5. TARJETAS
            // ============================================
            List<Tarjeta> tarjetas = new ArrayList<>();

            String sqlTarjetas = "SELECT idTarjeta, color FROM tarjeta WHERE Partido_idPartido = ?";

            ps = conn.prepareStatement(sqlTarjetas);
            ps.setInt(1, idPartido);
            rs = ps.executeQuery();

            while (rs.next()) {
                Tarjeta t = new Tarjeta();
                t.setIdTarjeta(rs.getInt("idTarjeta"));
                t.setColor(rs.getString("color"));
                tarjetas.add(t);
            }

            dto.setTarjetas(tarjetas);

            // ============================================
            // 6. SUSTITUCIONES
            // ============================================
            List<Sustitucion> sustituciones = new ArrayList<>();

            String sqlSust = """
                SELECT s.idSustitucion, s.descripcion
                FROM partidosustitucion psu
                JOIN sustitucion s ON psu.Sustitucion_idSustitucion = s.idSustitucion
                WHERE psu.Partido_idPartido = ?
            """;

            ps = conn.prepareStatement(sqlSust);
            ps.setInt(1, idPartido);
            rs = ps.executeQuery();

            while (rs.next()) {
                Sustitucion s = new Sustitucion();
                s.setIdSustitucion(rs.getInt("idSustitucion"));
                s.setDescripcion(rs.getString("descripcion"));
                sustituciones.add(s);
            }

            dto.setSustituciones(sustituciones);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dto;
    }
    
}
