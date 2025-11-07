
package DAO;


import static DAO.DBConnection.conectar;
import java.sql.*;
import Modelo.*;
import java.util.HashSet;
import java.util.Set;

/*Esta clase permitirá hacer los crud de los usuarios
*/
public class UsuarioDAO {
    
    Connection connection;
    Usuario usuario;
    
    public UsuarioDAO(Connection connection){
        this.connection = connection;
    }
    
    /*Permite identificar los datos necesarios para validar las credenciales
    */
    public Usuario validarCredenciales(String nombre_usuario, String contrasenia){
        
        Usuario usuario = null;
        String sql = "SELECT * FROM usuario WHERE nombre_usuario = ? AND contrasenia = ?";
        
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, nombre_usuario);
            stmt.setString(2, contrasenia);
            
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                usuario = new Usuario();
                usuario.setId_usuario(rs.getInt("id_usuario"));
                usuario.setNombre_usuario(rs.getString("nombre_usuario"));
                usuario.setNombre_usuario(rs.getString("contrasenia"));
                usuario.setId_jugador(rs.getInt("id_jugador"));
                usuario.setId_arbitro(rs.getInt("id_arbitro"));
                usuario.setId_director_tecnico(rs.getInt("id_director_tecnico"));
                
                
            }
            
        } catch (SQLException e){
            e.printStackTrace();
        }
                
          return usuario;      
    }
    
    
}
