
package DAO;

import Modelo.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GolDAO {

    Connection conn;

    public GolDAO(Connection conn) {
        this.conn = conn;
    }
    
    // En GolDAO.java

public List<Gol> obtenerGolesPorPartido(int idPartido){
    List<Gol> lista = new ArrayList<>();
    String sql = "SELECT idGol, minuto FROM gol WHERE Partido_idPartido = ? ORDER BY minuto";
    
    try (PreparedStatement stmt = conn.prepareStatement(sql)){
        
        stmt.setInt(1, idPartido); // Pasamos el ID del partido como parámetro
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()){
            Gol gol = new Gol();
            gol.setIdGol(rs.getInt("idGol"));
            gol.setMinuto(rs.getInt("minuto"));
            // No necesitamos el idPartido aquí si ya lo tenemos como contexto,
            // pero si la clase Gol lo requiere:
            // gol.setPartidoId(idPartido); 
            lista.add(gol);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return lista;
}
    
    
}
