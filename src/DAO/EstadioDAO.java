package DAO;

import Modelo.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstadioDAO {
    
    Connection conn;
    
    public EstadioDAO(Connection conn) {
        this.conn = conn;
    }

    /*Lista todos los registros de Estadio
    */
    public List<Estadio> obtenerTodos() {
        
        List<Estadio> estadios = new ArrayList<>();
        String sql = "SELECT * FROM estadio";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()){
            while (rs.next()){
                Estadio estadio = new Estadio(
                        rs.getInt("idEstadio"),
                        rs.getString("nombre"),
                        rs.getInt("capacidad")
                );
                
                estadios.add(estadio);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return estadios;
    }
}
