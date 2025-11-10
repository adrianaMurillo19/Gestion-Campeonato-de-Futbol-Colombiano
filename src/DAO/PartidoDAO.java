
package DAO;
import Modelo.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartidoDAO {
    
    Connection conn;

    public PartidoDAO(Connection conn) {
        this.conn = conn;
    }
    
    
    /*Este método permite obtener el historial de los partidos ordenados por Fecha y Hora
    */
    public List<Partido> obtenerTodosLosPartidos(){
    List<Partido> lista = new ArrayList<>();
    
    // Consulta sin la cláusula WHERE y ordenada
    String sql = "SELECT idPartido, fechaJuego, horaInicio, resultadoFinal, Estadio_idEstadio, Arbitro_idUsuario FROM partido ORDER BY fechaJuego, horaInicio";
    
    // Usamos Statement o PreparedStatement (sin parámetros)
    try (PreparedStatement stmt = conn.prepareStatement(sql)){
        
        // No se necesita stmt.setXxx()
        
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()){
            Partido p = new Partido();
            p.setIdPartido(rs.getInt("idPartido"));
            p.setFechaJuego(rs.getDate("fechaJuego"));
            p.setHoraInicio(rs.getString("horaInicio"));
            p.setResultadoFinal(rs.getString("resultadoFinal"));
            
            //  las claves foráneas si son parte del objeto Partido
            p.setEstadio_idEstadio(rs.getInt("Estadio_idEstadio")); 
            p.setArbitro_idUsuario(rs.getInt("Arbitro_idUsuario"));
            
            lista.add(p);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return lista;
}
    
}
