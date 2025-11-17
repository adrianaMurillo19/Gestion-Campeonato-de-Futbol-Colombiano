package DAO;

import Modelo.DirectorTecnico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DirectorTecnicoDAO {

    private Connection conn;

    public DirectorTecnicoDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Consulta todos los directores técnicos registrados en la BD.
     */
    public List<DirectorTecnico> obtenerTodosLosDirectoresTecnicos() throws Exception {

        List<DirectorTecnico> lista = new ArrayList<>();

        String sql = "SELECT id_director_tecnico, nombreUno, nombreDos, apellidoUno, apellidoDos, "
                   + "cedula, fechaNacimiento, experiencia "
                   + "FROM DirectorTecnico";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                DirectorTecnico dt = new DirectorTecnico();

                dt.setId_director_tecnico(rs.getInt("id_director_tecnico"));
                dt.setNombreUno(rs.getString("nombreUno"));
                dt.setNombreDos(rs.getString("nombreDos"));
                dt.setApellidoUno(rs.getString("apellidoUno"));
                dt.setApellidoDos(rs.getString("apellidoDos"));
                dt.setCedula(rs.getString("cedula"));

                // Conversión java.sql.Date ➝ java.util.Date
                java.sql.Date sqlFecha = rs.getDate("fechaNacimiento");
                if (sqlFecha != null) {
                    dt.setFechaNacimiento(new java.util.Date(sqlFecha.getTime()));
                }

                dt.setExperiencia(rs.getString("experiencia"));

                lista.add(dt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}
