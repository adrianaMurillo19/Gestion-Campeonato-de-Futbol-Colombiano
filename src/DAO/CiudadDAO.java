package DAO;

import Modelo.Ciudad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CiudadDAO {

    private Connection conn;

    public CiudadDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Devuelve todas las ciudades registradas.
     */
    public List<Ciudad> obtenerTodasLasCiudades() throws Exception {

        List<Ciudad> lista = new ArrayList<>();

        String sql = "SELECT idCiudad, nombre FROM Ciudad";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Ciudad c = new Ciudad();

                c.setIdCiudad(rs.getInt("idCiudad"));
                c.setNombre(rs.getString("nombre"));

                lista.add(c);
            }
        }

        return lista;
    }
}
