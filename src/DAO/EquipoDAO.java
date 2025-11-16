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
    
    
    
    
}
