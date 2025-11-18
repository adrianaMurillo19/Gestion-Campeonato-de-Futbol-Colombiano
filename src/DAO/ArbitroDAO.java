package DAO;

import Modelo.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArbitroDAO {
    
    Connection conn;
    
    public ArbitroDAO(Connection conn) {
        this.conn = conn;
    }

    /*Lista todos los registros de Arbitro
    */
    public List<Arbitro> obtenerTodos() {
        
        List<Arbitro> arbitros = new ArrayList<>();
        String sql = "SELECT * FROM arbitro";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()){
            while (rs.next()){
                Arbitro a = new Arbitro(
                        rs.getInt("id_arbitro"),
                        rs.getString("nombreUno"),
                        rs.getString("apellidoUno"),
                        rs.getString("tipo")
                );
                
                arbitros.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arbitros;
    }
    
}
